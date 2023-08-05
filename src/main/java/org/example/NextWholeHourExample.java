package org.example;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class NextWholeHourExample {
    public static void main(String[] args) {
        // 获取当前时间
        Instant now = Instant.now();

        // 计算下一个整点小时的时间
        Instant nextWholeHour = now.truncatedTo(ChronoUnit.HOURS).plus(1, ChronoUnit.HOURS);

        // 输出结果
        System.out.println("当前时间: " + now);
        System.out.println("下一个整点小时的时间: " + nextWholeHour);


        ZonedDateTime nowLocalDateTime = ZonedDateTime.now();

        // 获取当天下午6点的时间
        ZonedDateTime afternoonSix = nowLocalDateTime.withHour(0).withMinute(0).withSecond(0).withNano(0);

        System.out.println("当前时间: " + nowLocalDateTime);
        System.out.println("当天下午6点的时间: " + afternoonSix);


        ZonedDateTime nextWholeHour2 = nowLocalDateTime.withMinute(0).withSecond(0).withNano(0).plusHours(1);

        System.out.println(nextWholeHour2);

    }
}