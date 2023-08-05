package org.example;

import com.google.common.collect.Sets;
import java.util.Set;

public class HADMain {

    public static void main(String[] args) {

        Set<String> strings = Sets.newHashSet(
            "7a514d4a-67cd-4e16-900c-ea50ca7fea62",
            "45e531d7-a372-4de7-92fe-f0c36f21a0f1",
            "1802de4e-7db3-482c-8eea-9e78973c52d6",
            "6c46def5-529b-42bd-81ac-0dddaff622c2",
            "718910f6-83e7-476c-ace8-80a2f0611aa7",
            "bb8acbfd-b06f-41e0-ae2a-a413e3d1494d",
            "bb8acbfd-b06f-41e0-ae2a-a413e3d1494d",
            "cf0e6363-6742-4a1d-a51b-9b7148161075",
            "8052bbfb-747d-4624-bce6-a6b1aa182724",
            "57213fcb-100c-4b6f-94b1-53e367306bb5"

        );

        System.out.println(strings.size());
    }

}
