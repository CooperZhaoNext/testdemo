package org.example;

import com.google.common.collect.Maps;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.TreeMap;

public class MapMain {


    public static void main(String[] args) {

        TreeMap<ZonedDateTime,Integer> treeMap = Maps.newTreeMap();


        treeMap.put(ZonedDateTime.now().plus(1, ChronoUnit.HOURS),5);
        treeMap.put(ZonedDateTime.now().plus(0, ChronoUnit.HOURS),4);
        treeMap.put(ZonedDateTime.now().plus(-1, ChronoUnit.HOURS),3);
        treeMap.put(ZonedDateTime.now().plus(-2, ChronoUnit.HOURS),2);
        treeMap.put(ZonedDateTime.now().plus(-3, ChronoUnit.HOURS),1);

        for(Map.Entry<ZonedDateTime,Integer> entry:treeMap.entrySet()){

            System.out.println(entry.getKey()+":"+entry.getValue());


        }



    }

}
