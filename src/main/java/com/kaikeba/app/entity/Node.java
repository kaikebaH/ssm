package com.kaikeba.app.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/22.
 */
public class Node {
    private String name;
    private boolean open;
    private List<Node> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public Node() {
    }

    public Node(boolean open, String name) {
        this.open = open;
        this.name = name;
    }
}
