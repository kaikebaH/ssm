package com.kaikeba.app.dao;

import com.kaikeba.app.entity.CartItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/7/25.
 */
public interface ICartItemDao {
    /**
     * 通过uid获取购物车信息
     * @param uid
     * @return
     */
    List<CartItem> getCartByUid(@Param("uid")String uid);

    CartItem getCartItemByBidAndUid(@Param("bid")String bid,@Param("uid")String uid);

    void updateQuantityByCartItemId(String cartItemId);

    void addCart(@Param("cartItem")CartItem cartItem);

    void deleteCartItem(String cartitemId);

    void delCartItemByCartItemIds(@Param("params")List<String> params);

    void updateQuantityBycId(String cid);

    List<CartItem> loadCartItems(@Param("params")List<String> params);
}
