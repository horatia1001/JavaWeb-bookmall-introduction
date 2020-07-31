package com.book.service;

import com.book.pojo.Cart;

public interface OrderService {
    String createOrder(Cart cart, Integer userId);

}
