package com.kaikeba.app.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kaikeba.app.entity.Node;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/22.
 */
@Controller
@Scope("prototype")
@RequestMapping("/testTreeAction")
public class TestTreeAction {

    @RequestMapping("/getJSON")
    @ResponseBody
    /**
     * 使用原始的servlet获取
     */
    public String getJSON(){
        JSONArray array = new JSONArray();
        JSONObject jsonObject = new JSONObject();

        JSONObject node1 = new JSONObject();
        //一级节点
        node1.put("name","test1");
        node1.put("open","true");
        //两个二级节点
        JSONArray node1Array = new JSONArray();
        JSONObject node1Child1 = new JSONObject();
        JSONObject node1Child2 = new JSONObject();
        node1Child1.put("name","test1_1");
        node1Child2.put("name","test1_2");
        node1Array.add(node1Child1);
        node1Array.add(node1Child2);
        //将二级节点放在一级节点下
        node1.put("children",node1Array);
        array.add(node1);

        JSONObject node2 = new JSONObject();
        node2.put("name","test2");
        node2.put("open","true");
        JSONArray node2Array = new JSONArray();
        JSONObject node2Child1 = new JSONObject();
        JSONObject node2Child2 = new JSONObject();
        node2Child1.put("name","test2_1");
        node2Child2.put("name","test2_2");
        node2Array.add(node2Child1);
        node2Array.add(node2Child2);
        node2.put("children",node2Array);
        array.add(node2);

        jsonObject.put("nodes",array);
        return array.toJSONString();
    }
    @RequestMapping("/nodeJSON")
    @ResponseBody
    /**
     * 使用mvc设计思想
     */
    public List<Node> nodeJSON(){
        List<Node> resultList = new ArrayList<Node>();
        Node node1 = new Node(true,"test1");
        List<Node> node1Childs = new ArrayList<Node>();
        Node node1child1 = new Node(true,"test1_1");
        Node node1child2 = new Node(true,"test1_2");
        node1Childs.add(node1child1);
        node1Childs.add(node1child2);
        node1.setChildren(node1Childs);

        Node node2 = new Node(true,"test2");
        List<Node> node2Childs = new ArrayList<Node>();
        Node node2child1 = new Node(true,"test2_1");
        Node node2child2 = new Node(true,"test2_2");
        node2Childs.add(node2child1);
        node2Childs.add(node2child2);
        node2.setChildren(node2Childs);

        resultList.add(node1);
        resultList.add(node2);
        return resultList;
    }
}
