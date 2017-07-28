package com.kaikeba.app.service.impl;

import com.kaikeba.app.dao.ICategoryDao;
import com.kaikeba.app.entity.Category;
import com.kaikeba.app.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/22.
 */
@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private ICategoryDao categoryDao;
    @Override
    public List<Category> showAllCategory() {
        return categoryDao.listCategorys();
    }
}
