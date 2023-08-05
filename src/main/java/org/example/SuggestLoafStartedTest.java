package org.example;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import org.example.SuggestAnalyzeTest.SuggestionResult;

public class SuggestLoafStartedTest {

    static class SuggestionResult {

        private String realSugLoadId;

        private boolean loadStartedSugLoadId;

        private boolean swapLoadSugLoadId;

        public SuggestionResult(String realSugLoadId,boolean loadStartedSugLoadId,boolean swapLoadSugLoadId){
            this.realSugLoadId = realSugLoadId;
            this.loadStartedSugLoadId = loadStartedSugLoadId;
            this.swapLoadSugLoadId = swapLoadSugLoadId;
        }

        public String getRealSugLoadId() {
            return realSugLoadId;
        }

        public void setRealSugLoadId(String realSugLoadId) {
            this.realSugLoadId = realSugLoadId;
        }

        public boolean isLoadStartedSugLoadId() {
            return loadStartedSugLoadId;
        }

        public void setLoadStartedSugLoadId(boolean loadStartedSugLoadId) {
            this.loadStartedSugLoadId = loadStartedSugLoadId;
        }

        public boolean isSwapLoadSugLoadId() {
            return swapLoadSugLoadId;
        }

        public void setSwapLoadSugLoadId(boolean swapLoadSugLoadId) {
            this.swapLoadSugLoadId = swapLoadSugLoadId;
        }

        @Override
        public String toString() {
            return "SuggestionResult{" +
                "realSugLoadId='" + realSugLoadId + '\'' +
                '}';
        }
    }


    public static void main(String[] args) throws IOException {

        Map<String, String> realSugMap = Maps.newHashMap();

        Map<String, String> realSugMapDate = Maps.newHashMap();

        Map<String, String> loadStartedDate = Maps.newHashMap();

        Map<String, List<String>> loadStartedSugMap = Maps.newHashMap();

        Map<String, List<String>> swapLoadSugMap = Maps.newHashMap();





        List<String> lines = FileUtils.readLines(new File("/Users/cooper.zhao/Desktop/pdf/suggest2.txt"), "UTF-8");


        Set<String> noSuggestResult = Sets.newHashSet();

        String start = "2023-06-01T02:26:44.012Z";


        for (String line : lines) {
            List<String> tabeSpliter = Splitter.on("\t")
                .splitToList(line)
                .stream()
                .filter(u -> !u.equals(""))
                .collect(Collectors.toList());


            String date = tabeSpliter.get(0);

            if(start.compareTo(date)>0){
                continue;
            }

            List<String> units = Splitter.on(",")
                .splitToList(tabeSpliter.get(1))
                .stream()
                .filter(u -> !u.equals(""))
                .collect(Collectors.toList());

            String first = units.get(0);

            if (first.equals("suggestedSwap")) {
                String third = units.get(2);
                if (third.equals("LOAD_STARTED")) {
                    if (units.size() == 3) {
                        loadStartedSugMap.put(units.get(1), Lists.newArrayList());
                    }
                    loadStartedSugMap.put(units.get(1), Lists.newArrayList(units.subList(3, units.size())));
                    loadStartedDate.put(units.get(1),date);
                }

                if (third.equals("SWAP_PLACEHOLDER_LOAD")) {
                    if (units.size() == 3) {
                        swapLoadSugMap.put(units.get(1), Lists.newArrayList());
                    }
                    swapLoadSugMap.put(units.get(1), Lists.newArrayList(units.subList(3, units.size())));
                }
            }
            if (first.equals("RealSwap")) {
                realSugMap.put(units.get(1), units.get(2));
                realSugMapDate.put(units.get(1),date);
            }
        }

        List<SuggestionResult> suggestionResults = Lists.newArrayList();

        for(Map.Entry<String,String> entry:realSugMap.entrySet()){
            String ploadId = entry.getKey();

            String realLoadId = entry.getValue();

            List<String> sugStartedIds = loadStartedSugMap.get(ploadId);

            List<String> sugSwapIds = swapLoadSugMap.get(ploadId);

//            if(sugStartedIds == null || sugSwapIds == null){
//                continue;
//            }

            if(sugSwapIds == null || sugSwapIds.isEmpty()){
                noSuggestResult.add(ploadId);
            }

            SuggestionResult suggestionResult = new SuggestionResult(ploadId,sugStartedIds!=null && sugStartedIds.contains(realLoadId),sugSwapIds != null && sugSwapIds.contains(realLoadId));

            suggestionResults.add(suggestionResult);

            System.out.println(ploadId+"\t"+suggestionResult.loadStartedSugLoadId+"\t"+suggestionResult.swapLoadSugLoadId);

        }

        int bigDateStarted = 0;
        int bigRealDate= 0;
        int noLoadStarted= 0;

        Set<String> realBigger = Sets.newHashSet();


        for(Map.Entry<String,String> entry:realSugMapDate.entrySet()){

           String dateReal = entry.getValue();
           String dateStarted = loadStartedDate.get(entry.getKey());

           if(dateStarted == null){
               System.out.println("None Started");
               noLoadStarted++;
               continue;
           }

           if(dateReal.compareTo(dateStarted)<=0){

               bigDateStarted++;
               System.out.println("bigDateStarted id:"+entry.getKey()+",dateReal:"+dateReal+",dateStarted:"+dateStarted);
           }else {
               bigRealDate++;
               System.out.println("bigRealDate id:"+entry.getKey());
               realBigger.add(entry.getKey());
           }
        }

        System.out.println("bigDateStarted:"+bigDateStarted);
        System.out.println("bigRealDate:"+bigRealDate);
        System.out.println("noLoadStarted:"+noLoadStarted);

        System.out.println(suggestionResults.stream().filter(s->realBigger.contains(s.realSugLoadId)).filter(s->s.isLoadStartedSugLoadId()).count());

        System.out.println(suggestionResults.stream().filter(s->s.isLoadStartedSugLoadId()).count());

        System.out.println("noSuggestResult:"+noSuggestResult.size());

        System.out.println("noSuggestResult:"+noSuggestResult.size());


        System.out.println(suggestionResults.stream().filter(s->s.isSwapLoadSugLoadId()).count());



        System.out.println(suggestionResults.size());

        System.out.println(noSuggestResult);

        System.out.println(suggestionResults.stream().filter(s->!s.isSwapLoadSugLoadId() && !noSuggestResult.contains(s.realSugLoadId)).collect(
            Collectors.toList()));













    }

}
