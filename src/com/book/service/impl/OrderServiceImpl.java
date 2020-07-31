package com.book.service.impl;

import com.book.dao.OrderDao;
import com.book.dao.OrderItemDao;
import com.book.dao.impl.OrderDaoImpl;
import com.book.dao.impl.OrderItemDaoImpl;
import com.book.pojo.Cart;
import com.book.pojo.CartItem;
import com.book.pojo.Order;
import com.book.pojo.OrderItem;
import com.book.service.OrderService;

import java.util.Date;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
        OrderDao orderDao = new OrderDaoImpl();
        OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        // 生成订单号
        String orderId = System.currentTimeMillis() + userId.toString();
        // 生成新订单
        Order order = new Order(orderId,new Date(),cart.getItemTotalPrice(),0,userId);
        // 保存订单
        orderDao.saveOrder(order);

        // 获取购物车中所有商品转换为OrderItem
        for (Map.Entry<Integer, CartItem> entry: cart.getItemMap().entrySet()){
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(cartItem.getId(), cartItem.getName(), cartItem.getCount(), cartItem.getSinglePrice(),
                    cartItem.getTotalPrice(), orderId);
            // 将商品项添加到数据库
            orderItemDao.saveOrderItem(orderItem);
        }

        // 清空购物车
        cart.clearCart();

        return orderId;
    }



}
