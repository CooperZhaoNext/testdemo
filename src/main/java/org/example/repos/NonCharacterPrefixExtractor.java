package org.example.repos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NonCharacterPrefixExtractor {
    public static void main(String[] args) {
        String input = "              docker create -v /home/circleci --name nextdata alpine:3.4 /bin/true";

        // 定义正则表达式匹配非字符部分
        Pattern pattern = Pattern.compile("^\\s*"); // 匹配开头的空白字符

        // 进行匹配
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String nonCharacterPrefix = matcher.group();
            System.out.println("非字符前缀: \"" + nonCharacterPrefix + "\"");
        } else {
            System.out.println("没有找到非字符前缀");
        }
    }
}