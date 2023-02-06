package com.example.springprac.service;


import com.example.springprac.domain.Order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
