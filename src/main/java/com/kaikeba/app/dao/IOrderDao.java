package com.kaikeba.app.dao;

import com.kaikeba.app.entity.Order;
import com.kaikeba.app.entity.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/7/26.
 */
public interface IOrderDao {
    List<Order> listOrderByUid(@Param("uid")String uid,@Param("startPage")int startPage,@Param("pageSize")int pageSize);

    int getCountRowByUid(String uid);

    void add(@Param("order")Order order);

    /**
     * 保存订单明细信息
     * @param orderItem
     */
    void saveOrderItem(@Param("orderItem")OrderItem orderItem);

    Order getOrderByOid(String oid);

    List<OrderItem> getOrderItemByOid(String oid);

    void delOrderByOid(String oid);

    void delOrderItemByOid(String oid);

    void updateStatusByOid(String oid);

    void updateByOid(@Param("oid")String oid,@Param("status")int status);
}
