package com.nhnacademy.student.day4.stopwatch;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTest implements PerformanceTestable{
    @StopWatch
    @Override
    public void test(){
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i<100000000 ; i++){
            list.add(i);
        }
    }
}
