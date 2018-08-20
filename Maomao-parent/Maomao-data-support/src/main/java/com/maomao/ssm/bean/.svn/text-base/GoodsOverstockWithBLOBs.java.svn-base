package com.maomao.ssm.bean;

import java.io.Serializable;

public class GoodsOverstockWithBLOBs extends GoodsOverstock implements Serializable {
    private String detail;

    private String imgs;

    private String param;

    private static final long serialVersionUID = 1L;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs == null ? null : imgs.trim();
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param == null ? null : param.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", detail=").append(detail);
        sb.append(", imgs=").append(imgs);
        sb.append(", param=").append(param);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}