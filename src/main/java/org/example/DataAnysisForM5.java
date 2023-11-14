package org.example;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.Icon;
import org.apache.commons.io.FileUtils;
import org.springframework.util.CollectionUtils;

public class DataAnysisForM5 {


    public static void main(String[] args) throws Exception {


        List<String> lines = FileUtils.readLines(new File("/Users/cooper.zhao/Desktop/m5/1114.txt"), "UTF-8");

        Map<String,List<String>> results = Maps.newHashMap();
        Map<String,Integer> retryCountMap = Maps.newHashMap();


        Set<String> loadIds = Sets.newHashSet();

        for(String line : lines){
            List<String> stringList = Splitter.on(",").splitToList(line);

            String reason =line.replace(stringList.get(0),"").replace(stringList.get(stringList.size()-1),"");
            reason = reason.replace(stringList.get(1),"").replace(",, detail result ","").replace(",,","");
            String loadId = stringList.get(stringList.size()-1).replace(" for load ","");
            if(reason.contains("not match based conditions")
                || reason.contains("the load without dismount instruction") || reason.contains("the load has further appointments")){
                continue;
            }



            List<String> reasons = results.get(loadId);

            Integer retryCount = retryCountMap.get(loadId);

            if(retryCount == null){
                retryCountMap.put(loadId,1);
            }else {
                retryCount++;
                retryCountMap.put(loadId,retryCount);
            }

            if(CollectionUtils.isEmpty(reasons)){
                results.put(loadId, Lists.newArrayList(reason));
            }else {
                if(!reasons.get(reasons.size()-1).equals(reason)){
                    reasons.add(reason);
                }
            }
        }

        AtomicInteger yardNotSupport = new AtomicInteger();
        AtomicInteger notInventoryReport = new AtomicInteger();
        AtomicInteger notMatchedYardLocation = new AtomicInteger();
        AtomicInteger noTimeSlotTerminal = new AtomicInteger();
        AtomicInteger terminalHasNoMatchedLoad = new AtomicInteger();
        AtomicInteger illegalAlreadyBundle = new AtomicInteger();
        AtomicInteger scrapingBundleFailed = new AtomicInteger();
        AtomicInteger attemptScrapingBundleDualTransaction = new AtomicInteger();
        AtomicInteger skipScrapingBundleDualTransaction = new AtomicInteger();

        AtomicInteger otherFailed = new AtomicInteger();

        AtomicInteger terminalNotFoundAtlas = new AtomicInteger();
        AtomicInteger terminalIsNotSupport = new AtomicInteger();
        AtomicInteger terminalHasNoMoves = new AtomicInteger();
        AtomicInteger EmptyloadpopNotMatched = new AtomicInteger();
        AtomicInteger allUsePopChassisPool = new AtomicInteger();
        AtomicInteger noneUseAIMZChassisNumber = new AtomicInteger();
        AtomicInteger noChassisAndContainerMatched = new AtomicInteger();
        AtomicInteger noAppointmentMatched = new AtomicInteger();






        for(Map.Entry<String,List<String>> entry: results.entrySet()){

            System.out.println(entry.getKey()+"\t"+retryCountMap.get(entry.getKey())+"\t"+Joiner.on("||").join(entry.getValue()));

            boolean isNotYard = entry.getValue().stream().anyMatch(s->s.contains("the yard") && s.contains("not support for container"));

            if(!isNotYard){
                loadIds.add(entry.getKey());
            }

            entry.getValue().stream().forEach(s->{
                if(s.contains("the yard") && s.contains("not support for container")){
                    yardNotSupport.getAndIncrement();
                    return;
                }

                if(s.contains("not found inventory report for container")){
                    notInventoryReport.getAndIncrement();
                    return;
                }

                if(s.contains("not matched with the yard location")){
                    notMatchedYardLocation.getAndIncrement();
                    return;
                }

                if(s.contains("there are no available terminal")){
                    noTimeSlotTerminal.getAndIncrement();
                    return;
                }

                if(s.contains("there has no eligible outgating load, each terminal reasons")){
                    terminalHasNoMatchedLoad.getAndIncrement();

                    if(s.contains("is not found in atlas")){
                        terminalNotFoundAtlas.getAndIncrement();
                    }

                    if(s.contains("is not support dual transaction")){
                        terminalIsNotSupport.getAndIncrement();
                    }

                    if(s.contains("has no moves")){
                        terminalHasNoMoves.getAndIncrement();
                    }

                    if(s.contains("Empty load pop is")){
                        EmptyloadpopNotMatched.getAndIncrement();
                    }

                    if(s.contains("is all use POP chassis pool")){
                        allUsePopChassisPool.getAndIncrement();
                    }

                    if(s.contains("has none use AIMZ chassis number")){
                        noneUseAIMZChassisNumber.getAndIncrement();
                    }

                    if(s.contains("has no chassis and container matched outgating")){
                        noChassisAndContainerMatched.getAndIncrement();
                    }

                    if(s.contains("has no appointment matched outgating load")){
                        noAppointmentMatched.getAndIncrement();
                    }


                    return;
                }

                if(s.contains("Find illegal association when bundle")){
                    illegalAlreadyBundle.getAndIncrement();
                    return;
                }

                if(s.contains("Create bundle scraping job failed")){
                    scrapingBundleFailed.getAndIncrement();
                    return;
                }

                if(s.contains("attemptScrapingBundleDualTransaction")){
                    attemptScrapingBundleDualTransaction.getAndIncrement();
                    return;
                }

                if(s.contains("skipScrapingBundleDualTransaction")){
                    skipScrapingBundleDualTransaction.getAndIncrement();
                    return;
                }

                otherFailed.getAndIncrement();

            });
        }

        System.out.println("yardNotSupport\t"+yardNotSupport);
        System.out.println("notInventoryReport\t"+notInventoryReport);
        System.out.println("notMatchedYardLocation\t"+notMatchedYardLocation);
        System.out.println("noTimeSlotTerminal\t"+noTimeSlotTerminal);
        System.out.println("terminalHasNoMatchedLoad\t"+terminalHasNoMatchedLoad);
        System.out.println("illegalAlreadyBundle\t"+illegalAlreadyBundle);
        System.out.println("scrapingBundleFailed\t"+scrapingBundleFailed);
        System.out.println("attemptScrapingBundleDualTransaction\t"+attemptScrapingBundleDualTransaction);
        System.out.println("skipScrapingBundleDualTransaction\t"+skipScrapingBundleDualTransaction);
        System.out.println("otherFailed\t"+otherFailed);


        System.out.println("terminalNotFoundAtlas\t"+terminalNotFoundAtlas);
        System.out.println("terminalIsNotSupport\t"+terminalIsNotSupport);
        System.out.println("terminalHasNoMoves\t"+terminalHasNoMoves);
        System.out.println("EmptyloadpopNotMatched\t"+EmptyloadpopNotMatched);
        System.out.println("allUsePopChassisPool\t"+allUsePopChassisPool);
        System.out.println("noneUseAIMZChassisNumber\t"+noneUseAIMZChassisNumber);
        System.out.println("noChassisAndContainerMatched\t"+noChassisAndContainerMatched);
        System.out.println("noAppointmentMatched\t"+noAppointmentMatched);


        FileUtils.writeLines(new File("/Users/cooper.zhao/Desktop/m5/1028_loadIds.txt"),loadIds);
    }

}
