package com.zxj.inaction.web.test;

import com.alibaba.fastjson.JSON;

import java.util.Calendar;
import java.util.Date;


public class Zxjtest {
    /**
     * 当天+next天的0点0分
     */
    public static Date getDayStart(Date date, int next) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, next);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        Date today = getDayStart(new Date(), -1);
        System.out.println(JSON.toJSONString(today));
    }
}
