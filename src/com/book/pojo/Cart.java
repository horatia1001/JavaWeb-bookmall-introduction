package com.book.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车
 *
 * @param
 * @return
 */
public class Cart {
    // 总商品数
    private Integer itemTotalCount;
    // 总商品金额
    private BigDecimal itemTotalPrice;
    // 商品项
    private Map<Integer, CartItem> itemMap = new LinkedHashMap<>();

    /**
     * 添加商品
     *
     * @param addItem
     */
    public void addItem(CartItem addItem) {
        CartItem cartItem = itemMap.get(addItem.getId());

        // 判断购物车中原先是否有此书
        if (cartItem == null) {
            itemMap.put(addItem.getId(), addItem);
        }else {
            // 商品总数
            cartItem.setCount( cartItem.getCount() + 1);
            // 商品总价： 单价*总数量
            cartItem.setTotalPrice(cartItem.getSinglePrice().multiply(new BigDecimal(cartItem.getCount())));
        }


    }

    /**
     * 根据id删除商品
     *
     * @param id
     */
    public void deleteItem(Integer id) {
        itemMap.remove(id);
    }


    /**
     * 修改某件商品的数量
     * @param id
     * @param updateCount
     */
    public void updateCount(Integer id, Integer updateCount){
        CartItem cartItem = itemMap.get(id);
        if(cartItem != null){
            cartItem.setCount(updateCount);
            cartItem.setTotalPrice(cartItem.getSinglePrice().multiply(new BigDecimal(updateCount)));
        }
    }

    /**
     * 清空购物车
     */
    public void clearCart() {
        itemMap.clear();
    }


    public Integer getItemTotalCount() {
        Integer itemTotalCount = 0;
        for(Map.Entry<Integer,CartItem> itemEntry:itemMap.entrySet()){
            itemTotalCount += itemEntry.getValue().getCount();
        }
        return itemTotalCount;
    }

//    public void setItemTotalCount(Integer itemTotalCount) {
//        this.itemTotalCount = itemTotalCount;
//    }

    public BigDecimal getItemTotalPrice() {
        itemTotalPrice = new BigDecimal(0);
        for(Map.Entry<Integer,CartItem> itemEntry:itemMap.entrySet()){
            itemTotalPrice = itemTotalPrice.add(itemEntry.getValue().getTotalPrice());
        }
        return itemTotalPrice;
    }

//    public void setItemTotalPrice(BigDecimal itemTotalPrice) {
//        this.itemTotalPrice = itemTotalPrice;
//    }

    public Map<Integer, CartItem> getItemMap() {
        return itemMap;
    }

    public void setItemMap(Map<Integer, CartItem> itemMap) {
        this.itemMap = itemMap;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "itemTotalCount=" + getItemTotalCount() +
                ", itemTotalPrice=" + getItemTotalPrice() +
                ", itemMap=" + itemMap +
                '}';
    }
}
