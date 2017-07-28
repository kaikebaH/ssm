package com.kaikeba.app.entity;

/**
 * 图书分类的实体类
 * @author dhc
 * @version V1.0
 * @date 17/7/22
 */
public class Category {

    private String cid;
    private String cname;
    private String pid;
    private String desc;
    private int orderBy;

//    一下三个用于zTree
    private boolean open;
    private String url;
    private String target;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                ", pid='" + pid + '\'' +
                ", desc='" + desc + '\'' +
                ", orderBy=" + orderBy +
                ", open=" + open +
                ", url='" + url + '\'' +
                ", target='" + target + '\'' +
                '}';
    }
}
