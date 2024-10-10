package com.nhnacademy.day2.Chain_of_responsibility;

public interface Filter {
    void doFilter(Request request, FilterChain filterChain);
}