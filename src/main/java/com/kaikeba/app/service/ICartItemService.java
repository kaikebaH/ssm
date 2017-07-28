package com.kaikeba.app.service;

import com.kaikeba.app.entity.CartItem;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/7/25.
 */
public interface ICartItemService {
    /**
     * 通过uid获取购物车信息
     * @param uid
     * @return
     */
    List<CartItem> getCartByUid(String uid);

    /**
     * 通过bid和uid获取购物车信息
     * @param
     * @return
     */
    CartItem getCartItemByBidAndUid(String bid,String uid);

    /**
     * 通过cid修改购买数量
     * @param cartItem
     */
    void updateQuantityByCartItemId(CartItem cartItem);

    /**
     * 添加购物车
     * @param cartItem
     */
    void addCart(CartItem cartItem);

    /**
     * 删除购物车信息
     * @param cartitemId
     */
    void deleteCartItem(String cartitemId);

    /**
     * 删除多个购物车信息
     * @param cidsArray
     */
    void delCartItemByCartItemIds(String[] cidsArray);

    /**
     * ajax修改：根据cartItemId修改购买数量-1
     * @param cartItemId
     */
    @Transactional
    void updateQuantityByCartItemId(String cartItemId);

    /**
     * 结算
     * @param cartItemIdsStrings
     * @return
     */
    List<CartItem> loadCartItems(String[] cartItemIdsStrings);
}
