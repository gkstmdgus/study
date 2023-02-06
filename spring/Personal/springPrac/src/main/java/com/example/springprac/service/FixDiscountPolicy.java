package com.example.springprac.service;

import com.example.springprac.domain.Grade;
import com.example.springprac.domain.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFIxAmount = 1000; // 1000원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFIxAmount;
        }
        return 0;
    }
}
