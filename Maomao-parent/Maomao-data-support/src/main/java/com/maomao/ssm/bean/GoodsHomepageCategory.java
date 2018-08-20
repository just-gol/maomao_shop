package com.maomao.ssm.bean;

import java.io.Serializable;
import java.util.Date;

public class GoodsHomepageCategory implements Serializable {
    private Integer id;

    private Integer goodsCategoryid;

    private String name;

    private Integer sort;

    private String img;

    /**
     * 0禁用 1启用 -1删除
     */
    private Byte status;

    private Date createTime;

    private Date modifiedTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsCategoryid() {
        return goodsCategoryid;
    }

    public void setGoodsCategoryid(Integer goodsCategoryid) {
        this.goodsCategoryid = goodsCategoryid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", goodsCategoryid=").append(goodsCategoryid);
        sb.append(", name=").append(name);
        sb.append(", sort=").append(sort);
        sb.append(", img=").append(img);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifiedTime=").append(modifiedTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}