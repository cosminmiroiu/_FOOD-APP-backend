package com.finalproject.springbootfoodapp.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class DateUtil {

    public Date getDayStart(Date date) {
        LocalDateTime day = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime startOfDay = day.with(LocalTime.MIN);

        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public Date getDayEnd(Date date) {
        LocalDateTime day = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime endOfDay = day.with(LocalTime.MAX);

        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }
}
