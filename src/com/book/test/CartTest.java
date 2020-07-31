package com.book.test;

import com.book.pojo.Cart;
import com.book.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "査令十字街84号", 1, new BigDecimal(45), new BigDecimal(45)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1, "査令十字街84号", 1, new BigDecimal(45), new BigDecimal(45)));
        cart.addItem(new CartItem(1, "査令十字街84号", 1, new BigDecimal(45), new BigDecimal(45)));
        cart.addItem(new CartItem(2, "撒哈拉的故事", 1, new BigDecimal(16), new BigDecimal(16)));

        cart.deleteItem(2);

        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1, "査令十字街84号", 1, new BigDecimal(45), new BigDecimal(45)));
        cart.addItem(new CartItem(1, "査令十字街84号", 1, new BigDecimal(45), new BigDecimal(45)));
        cart.addItem(new CartItem(2, "撒哈拉的故事", 1, new BigDecimal(16), new BigDecimal(16)));

        cart.updateCount(2,5);

        System.out.println(cart);

    }

    @Test
    public void clearCart() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "査令十字街84号", 1, new BigDecimal(45), new BigDecimal(45)));
        cart.addItem(new CartItem(1, "査令十字街84号", 1, new BigDecimal(45), new BigDecimal(45)));
        cart.addItem(new CartItem(2, "撒哈拉的故事", 1, new BigDecimal(16), new BigDecimal(16)));
        System.out.println(cart);

        cart.clearCart();

        System.out.println(cart);



    }

    @Test
    public void getItemTotalCount() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1, "査令十字街84号", 1, new BigDecimal(45), new BigDecimal(45)));
        cart.addItem(new CartItem(1, "査令十字街84号", 1, new BigDecimal(45), new BigDecimal(45)));
        cart.addItem(new CartItem(2, "撒哈拉的故事", 1, new BigDecimal(16), new BigDecimal(16)));

        cart.updateCount(2,5);

        System.out.println(cart.getItemTotalCount());


    }

    @Test
    public void getItemTotalPrice() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1, "査令十字街84号", 1, new BigDecimal(45), new BigDecimal(45)));
        cart.addItem(new CartItem(1, "査令十字街84号", 1, new BigDecimal(45), new BigDecimal(45)));
        cart.addItem(new CartItem(2, "撒哈拉的故事", 1, new BigDecimal(16), new BigDecimal(16)));

        cart.updateCount(2,5);

        System.out.println(cart.getItemTotalPrice());
    }

    @Test
    public void getItemMap() {
    }

    @Test
    public void setItemMap() {
    }
}