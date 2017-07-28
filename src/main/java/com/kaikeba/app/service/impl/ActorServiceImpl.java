package com.kaikeba.app.service.impl;

import com.kaikeba.app.dao.IActorDao;
import com.kaikeba.app.entity.Actor;
import com.kaikeba.app.service.IActorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */
@Service
public class ActorServiceImpl implements IActorService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IActorDao actorDao;
    @Override
    public List<Actor> allActor() {
        try {
            return actorDao.allActor();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new RuntimeException(e.getMessage(),e);
        }
    }
}
