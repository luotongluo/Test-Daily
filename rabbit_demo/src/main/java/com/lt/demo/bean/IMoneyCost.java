package com.lt.demo.bean;

import java.util.Date;
import java.io.Serializable;

/**
 * (IMoneyCost)实体类
 *
 * @author tong.luo
 * @since 2021-01-22 16:44:06
 */
public class IMoneyCost implements Serializable {
    private static final long serialVersionUID = 353759486653630543L;
    /**
     * id
     */
    private Integer id;
    /**
     * 1.招商信用卡，2中信信用卡.3美团，4.京东支付。5花呗
     */
    private String type;
    /**
     * mon
     */
    private Double costMon;
    /**
     * back_time
     */
    private Date backTime;
    /**
     * create_time
     */
    private Date createTime;
    /**
     * 1 有效 0 无效
     */
    private Integer yn;
    /**
     * 人员id
     */
    private Integer persionId;

    public Integer getPersionId() {
        return persionId;
    }

    public void setPersionId(Integer persionId) {
        this.persionId = persionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getCostMon() {
        return costMon;
    }

    public void setCostMon(Double costMon) {
        this.costMon = costMon;
    }

    public Date getBackTime() {
        return backTime;
    }

    public void setBackTime(Date backTime) {
        this.backTime = backTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }

}