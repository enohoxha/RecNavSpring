package com.recnav.parser.Helpers;

import org.springframework.stereotype.Component;

@Component
public class StopWatch {

    private double startTime;
    private double endTime;

    public void start(){
        startTime = System.currentTimeMillis();
    }

    public void stop(){
        endTime = System.currentTimeMillis();
    }

    public double getTime(){
        return endTime - startTime;
    }


    public void clean(){
        startTime = 0;
        endTime = 0;
    }
}
