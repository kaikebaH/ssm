package com.kaikeba.app.service.impl;

import com.kaikeba.app.dao.ICartItemDao;
import com.kaikeba.app.entity.CartItem;
import com.kaikeba.app.service.ICartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/25.
 */
@Service
public class CartItemServiceImpl implements ICartItemService {
    @Autowired
    private ICartItemDao cartItemDao;

    @Override
    public List<CartItem> getCartByUid(String uid) {
        return cartItemDao.getCartByUid(uid);
    }

    @Override
    public CartItem getCartItemByBidAndUid(String bid,String uid) {
        return cartItemDao.getCartItemByBidAndUid(bid,uid);
    }

    @Override
    public void updateQuantityByCartItemId(CartItem cartItem) {
        cartItemDao.updateQuantityByCartItemId(cartItem.getCartItemId());
    }

    @Override
    public void addCart(CartItem cartItem) {
        cartItemDao.addCart(cartItem);
    }

    @Transactional
    @Override
    public void deleteCartItem(String cartitemId) {
        cartItemDao.deleteCartItem(cartitemId);
    }

    @Override
    public void delCartItemByCartItemIds(String[] cidsArray) {
        List<String> params = new ArrayList<String>();
        for(String cid : cidsArray){
            params.add(cid);
        }
        cartItemDao.delCartItemByCartItemIds(params);
    }

    @Transactional
    @Override
    public void updateQuantityByCartItemId(String cartItemId) {
        cartItemDao.updateQuantityBycId(cartItemId);
    }

    @Override
    public List<CartItem> loadCartItems(String[] cartItemIdsStrings) {
        List<String> params = new ArrayList<String>();
        for(String cartItemId : cartItemIdsStrings){
            params.add(cartItemId);
        }
        List<CartItem> list = cartItemDao.loadCartItems(params);
        return  list;
    }
}
