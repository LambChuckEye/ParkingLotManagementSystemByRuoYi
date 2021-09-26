package com.ruoyi.custom.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 停车场信息管理对象 cus_parkinglot
 * 
 * @author kamiu
 * @date 2021-09-17
 */
public class CusParkinglot extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 停车场总车位 */
    @Excel(name = "停车场总车位")
    private Long parkinglotCapacity;

    /** 停车场id */
    private Long parkinglotId;

    /** 停车场所属管理员id */
    @Excel(name = "停车场所属管理员id")
    private Long userId;

    /** 停车场名称 */
    @Excel(name = "停车场名称")
    private String parkinglotName;

    /** 停车场位置 */
    @Excel(name = "停车场位置")
    private String parkinglotLoc;

    /** 摄像头图片文件位置 */
    @Excel(name = "摄像头图片文件位置")
    private String parkinglotCamera;

    /** 停车场空闲车位 */
    @Excel(name = "停车场空闲车位")
    private Long parkinglotSpare;

    /** 渲染图位置 */
    @Excel(name = "渲染图位置")
    private String image;

    /** 渲染日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "渲染日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date imageUpdate;

    /** 模型信息管理信息 */
    private List<CusModel> cusModelList;

    public void setParkinglotCapacity(Long parkinglotCapacity) 
    {
        this.parkinglotCapacity = parkinglotCapacity;
    }

    public Long getParkinglotCapacity() 
    {
        return parkinglotCapacity;
    }
    public void setParkinglotId(Long parkinglotId) 
    {
        this.parkinglotId = parkinglotId;
    }

    public Long getParkinglotId() 
    {
        return parkinglotId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setParkinglotName(String parkinglotName) 
    {
        this.parkinglotName = parkinglotName;
    }

    public String getParkinglotName() 
    {
        return parkinglotName;
    }
    public void setParkinglotLoc(String parkinglotLoc) 
    {
        this.parkinglotLoc = parkinglotLoc;
    }

    public String getParkinglotLoc() 
    {
        return parkinglotLoc;
    }
    public void setParkinglotCamera(String parkinglotCamera) 
    {
        this.parkinglotCamera = parkinglotCamera;
    }

    public String getParkinglotCamera() 
    {
        return parkinglotCamera;
    }
    public void setParkinglotSpare(Long parkinglotSpare) 
    {
        this.parkinglotSpare = parkinglotSpare;
    }

    public Long getParkinglotSpare() 
    {
        return parkinglotSpare;
    }
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
    }
    public void setImageUpdate(Date imageUpdate) 
    {
        this.imageUpdate = imageUpdate;
    }

    public Date getImageUpdate() 
    {
        return imageUpdate;
    }

    public List<CusModel> getCusModelList()
    {
        return cusModelList;
    }

    public void setCusModelList(List<CusModel> cusModelList)
    {
        this.cusModelList = cusModelList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("parkinglotCapacity", getParkinglotCapacity())
            .append("parkinglotId", getParkinglotId())
            .append("userId", getUserId())
            .append("parkinglotName", getParkinglotName())
            .append("parkinglotLoc", getParkinglotLoc())
            .append("parkinglotCamera", getParkinglotCamera())
            .append("parkinglotSpare", getParkinglotSpare())
            .append("image", getImage())
            .append("imageUpdate", getImageUpdate())
            .append("remark", getRemark())
            .append("cusModelList", getCusModelList())
            .toString();
    }
}
