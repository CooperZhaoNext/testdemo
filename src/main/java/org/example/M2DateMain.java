package org.example;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Data;
import org.apache.commons.io.FileUtils;

public class M2DateMain {




    public static void dealLine(String line,boolean isStart){


        List<String> stringList = Splitter.on(",").splitToList(line);



        ZonedDateTime startDateTime = ZonedDateTime.parse(stringList.get(3).replace("startDateTime:",""));



        ZonedDateTime endDateTime = ZonedDateTime.parse(stringList.get(4).replace("endDateTime:",""));



        ZonedDateTime instructionZoneDate = ZonedDateTime.parse(stringList.get(5).replace("instructionZoneDate:",""));

        boolean isAppointmentTimeOfInstructionBetween =
            instructionZoneDate.compareTo(startDateTime) >= 0 && instructionZoneDate.compareTo(endDateTime) <= 0;

        if(isStart && !isAppointmentTimeOfInstructionBetween && startDateTime.compareTo(instructionZoneDate)>0){
            Duration duration = Duration.between(instructionZoneDate, startDateTime);
            long hours = duration.toHours();
            System.out.println(duration.toHours()+"\t"+line);
        }

        if(!isStart &&!isAppointmentTimeOfInstructionBetween && instructionZoneDate.compareTo(endDateTime)>0){
            Duration duration = Duration.between(endDateTime, instructionZoneDate);
            long hours = duration.toHours();
            System.out.println(hours);
        }
    }


    public static void main(String[] args) throws IOException {


        List<String> lines = FileUtils.readLines(new File("/Users/cooper.zhao/Desktop/pdf/0621Date.txt"), "UTF-8");


        for(String line:lines){
            dealLine(line,false);
        }









    }

}
