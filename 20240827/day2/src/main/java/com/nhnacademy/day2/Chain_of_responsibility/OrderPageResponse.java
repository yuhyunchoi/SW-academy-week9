package com.nhnacademy.day2.Chain_of_responsibility;

public class OrderPageResponse implements Response {

    @Override
    public void doResponse(Request request){
        System.out.println("###### response: OrderPageResponse ######");
        Member member = (Member) request.get("member");
        System.out.println("아이디:" + member.getId());
        System.out.println("이름:" + member.getName());

        if(request.getPath().equals("/admin")){
            System.out.println("등급:" + Member.Role.ADMIN);
        }
        else if(request.getPath().equals("/mypage")){
            System.out.println("등급" + Member.Role.USER);
        }else{
            System.out.println("등급" + Member.Role.MANAGER);
        }

        System.out.println();

    }
}
