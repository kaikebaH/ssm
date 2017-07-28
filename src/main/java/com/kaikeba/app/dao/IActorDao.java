package com.kaikeba.app.dao;

import com.kaikeba.app.entity.Actor;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */
public interface IActorDao {
    /**
     * 查询所有
     * @return
     */
    List<Actor> allActor();
}
