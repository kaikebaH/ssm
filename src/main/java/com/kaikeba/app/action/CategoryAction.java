package com.kaikeba.app.action;

import com.kaikeba.app.entity.Category;
import com.kaikeba.app.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/22.
 */
@Controller
@Scope("prototype")
@RequestMapping("/categoryAction")
public class CategoryAction {
    @Autowired
    private ICategoryService categoryService;

    @ResponseBody
    @RequestMapping("/listCategories")
    public List<Category> listCategories(){
        List<Category> newCategory = new ArrayList<Category>();
        List<Category> oldCategory = categoryService.showAllCategory();
        for(Category category : oldCategory){
            if(category.getPid() != null){  //不是一级节点
                category.setUrl("/ssm/bookAction/getBooksByCategoryId.do?cid=" + category.getCid());
                category.setTarget("body");
            }
            newCategory.add(category);
        }
        return newCategory;
    }
}
