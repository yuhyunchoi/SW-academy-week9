package com.nhnacademy.day2.Chain_of_responsibility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FilterChain  {
    private List<Filter> filters = new ArrayList<>();
    private Iterator iterator;

    public void addFilter(Filter filter){
        this.filters.add(filter);
        iterator = filters.iterator();
    }

    public void doFilter(Request request){
        if(iterator.hasNext()){
            Filter nextFilter = (Filter) iterator.next();
            nextFilter.doFilter(request, this);
        }else{
            if(request.getPath().equals("/mypage")){
                new MyPageResponse().doResponse(request);
            }else if(request.getPath().equals("/admin")){
                new AdminPageResponse().doResponse(request);
            }else if(request.getPath().equals("/order")){
                new OrderPageResponse().doResponse(request);
            }
        }
    }
}
