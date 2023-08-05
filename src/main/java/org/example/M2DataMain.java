package org.example;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Data;
import org.apache.commons.io.FileUtils;

public class M2DataMain {


    @Data
    static class Condition {

        private boolean isLoaded;

        private boolean noFlexibleAppointment;

        private boolean startLocationTypeIsEligible;

        private boolean endLocationTypeIsEligible;

        private boolean isInStartPlaceWhiteList;

        private boolean isInEndPlaceWhiteList;

        private boolean autoJobbingWhenRemoved;


        public boolean allCondition(){

            return isLoaded && noFlexibleAppointment
                && startLocationTypeIsEligible
                && endLocationTypeIsEligible && autoJobbingWhenRemoved;
        }


        public boolean filter1(){

            return isLoaded
                && startLocationTypeIsEligible
                && endLocationTypeIsEligible && noFlexibleAppointment;
        }

        public boolean filter2(){

            return isLoaded
                && startLocationTypeIsEligible
                && endLocationTypeIsEligible && autoJobbingWhenRemoved && noFlexibleAppointment;
        }

    }



    public static void main(String[] args) throws IOException {
        List<String> lines = FileUtils.readLines(new File("/Users/cooper.zhao/Desktop/pdf/0708.txt"), "UTF-8");

        Map<String, Condition> conditionMap = Maps.newHashMap();

        List<Condition> conditionList = Lists.newArrayList();

        Collections.reverse(lines);

        for (String line : lines) {

            line = line.replace(" jobbingStrategyType:YARD_TO_CONSIGNEE","");

            line = line.replace(" jobbingStrategyType:YARD_TO_YARD","");

            line = line.replace("[AUTO JOBBING] eligibleToJob loadId ","");


            List<String> units2 = Splitter.on(",").splitToList(line);

            String loadId = units2.get(0);
            Condition condition = new Condition();


            List<String> units3 = Splitter.on(":").splitToList(units2.get(1));
            condition.setLoaded(Boolean.valueOf(units3.get(1)));
            units3 = Splitter.on(":").splitToList(units2.get(2));
            condition.setNoFlexibleAppointment(Boolean.parseBoolean(units3.get(1)));
            units3 = Splitter.on(":").splitToList(units2.get(3));
            condition.setStartLocationTypeIsEligible(Boolean.parseBoolean(units3.get(1)));
            units3 = Splitter.on(":").splitToList(units2.get(4));
            condition.setEndLocationTypeIsEligible(Boolean.parseBoolean(units3.get(1)));
            units3 = Splitter.on(":").splitToList(units2.get(5));
            condition.setAutoJobbingWhenRemoved(Boolean.parseBoolean(units3.get(1)));

            conditionMap.put(loadId,condition);
            conditionList.add(condition);
        }


        System.out.println("isNoFlexibleAppointment:");
        System.out.println(conditionMap.values().stream().filter(condition -> condition.isNoFlexibleAppointment()).count());
        System.out.println(conditionMap.values().stream().filter(condition -> !condition.isNoFlexibleAppointment()).count());


        System.out.println("isAutoJobbingWhenRemoved:");
        System.out.println(conditionMap.values().stream().filter(condition -> condition.isAutoJobbingWhenRemoved()).count());
        System.out.println(conditionMap.values().stream().filter(condition -> !condition.isAutoJobbingWhenRemoved()).count());

        System.out.println("allCondition:");
        System.out.println(conditionMap.values().stream().filter(condition -> condition.allCondition()).count());
        System.out.println(conditionMap.values().stream().filter(condition -> !condition.allCondition()).count());

        System.out.println(conditionMap.values().size());

        System.out.println("loudou:");

        System.out.println(conditionMap.values().stream().filter(condition -> condition.filter1()).count());

        System.out.println(conditionMap.values().stream().filter(condition -> condition.filter2()).count());



        System.out.println("isNoFlexibleAppointment:");
        System.out.println(conditionList.stream().filter(condition -> condition.isNoFlexibleAppointment()).count());
        System.out.println(conditionList.stream().filter(condition -> !condition.isNoFlexibleAppointment()).count());


        System.out.println("isAutoJobbingWhenRemoved:");
        System.out.println(conditionList.stream().filter(condition -> condition.isAutoJobbingWhenRemoved()).count());
        System.out.println(conditionList.stream().filter(condition -> !condition.isAutoJobbingWhenRemoved()).count());

        System.out.println("allCondition:");
        System.out.println(conditionList.stream().filter(condition -> condition.allCondition()).count());
        System.out.println(conditionList.stream().filter(condition -> !condition.allCondition()).count());




        System.out.println(conditionList.size());

    }

}
