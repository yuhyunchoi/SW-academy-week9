package com.nhnacademy.day2.Chain_of_responsibility;

import java.util.Objects;

public class OrderPageFilter implements Filter{

    @Override
    public void doFilter(Request request, FilterChain filterChain){
        if(request.getPath().equals("/order")){
            Member member = (Member) request.get("member");
            if(Objects.nonNull(member)){
                if(member.hasRole(Member.Role.NONE)){
                    System.out.println("path:" + request.getPath() + " member role has USER : false");
                }else{
                    System.out.println("path:" + request.getPath() + " member role has USER : true");
                    filterChain.doFilter(request);
                }
            }else{
                System.out.println("OrderPageFilter : 다음 필터로 넘김 !");
                filterChain.doFilter(request);
            }
        }
    }
}
