package com.nhnacademy.student.day4.stopwatch;

public class ArrayListTestMain {
    public static void main(String[] args) {
        PerformanceTestable arrayListTest = new ArrayListTest();
        PerformanceTestable arrayListTestProxy = new ArrayListTestProxy(arrayListTest);
        arrayListTestProxy.test();
    }
}
