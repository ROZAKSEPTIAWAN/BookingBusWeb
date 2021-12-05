package com.websitebus.utils;

public class GetRandom {

    public static long getRandom(long min,long max){
        long number = min+(long) (Math.random()*max-min);
        return number;
      }
    
}
