package org.example;

import com.google.common.base.Splitter;
import java.util.List;

public class TestSpliter {


    public static void main(String[] args) {
        String a= "suggestedSwap,e08ff58d-968d-4bd1-ad6f-6415b5cc5672,SWAP_PLACEHOLDER_LOAD,";

        List<String> ads= Splitter.on(",").splitToList(a);
        System.out.println(ads);
        System.out.println(ads.size());

        System.out.println(ads.get(3).equals(""));
    }
}
