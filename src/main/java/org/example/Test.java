package org.example;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.AtomicDouble;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

public class Test {


    public static void main(String[] args) throws IOException {

        List<Double> doubles = Lists.newArrayList();

        List<String> ad= Lists.newArrayList("123.747","84.256","81.998","72.208","68.583","61.708","48.246","46.515","43.737","43.259");

        List<Double> doublesFive = Lists.newArrayList();
        AtomicDouble atomicDouble = new AtomicDouble(0.0);
        AtomicDouble atomicDoubleFive = new AtomicDouble(0.0);
        List<String> lines = FileUtils.readLines(new File("cci.log"), StandardCharsets.UTF_8);
        lines.stream().forEach(line->{
            if(line.contains("***")){
                return;
            }
            String value = Splitter.on("s - ").splitToList(Splitter.on(", ").splitToList(line).get(4)).get(0).replace("Time elapsed: ","");

            if(ad.contains(value.trim())){
                System.out.println(value+"---"+Splitter.on("s - ").splitToList(Splitter.on(", ").splitToList(line).get(4)).get(1));

            }
            double time = Double.parseDouble(value);
            doubles.add(time);
            atomicDouble.addAndGet(time);

            if(time>5.0){
                doublesFive.add(time);
                atomicDoubleFive.addAndGet(time);
            }
        });

        Collections.sort(doubles);
        Collections.reverse(doubles);
        System.out.println(doubles);
        System.out.println(atomicDouble);
        System.out.println(atomicDouble.get()/doubles.size());


        System.out.println(atomicDoubleFive);
        System.out.println(atomicDoubleFive.get()/doublesFive.size());
    }

}
