package com.ruoyi.custom.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 模型信息管理对象 cus_model
 * 
 * @author kamiu
 * @date 2021-09-17
 */
public class CusModel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 模型id */
    private Long modelId;

    /** 停车场id */
    @Excel(name = "停车场id")
    private Long parkinglotId;

    /** 规模模型位置 */
    @Excel(name = "规模模型位置")
    private String sizeModel;

    /** 识别模型位置 */
    @Excel(name = "识别模型位置")
    private String regModel;

    public void setModelId(Long modelId) 
    {
        this.modelId = modelId;
    }

    public Long getModelId() 
    {
        return modelId;
    }
    public void setParkinglotId(Long parkinglotId) 
    {
        this.parkinglotId = parkinglotId;
    }

    public Long getParkinglotId() 
    {
        return parkinglotId;
    }
    public void setSizeModel(String sizeModel) 
    {
        this.sizeModel = sizeModel;
    }

    public String getSizeModel() 
    {
        return sizeModel;
    }
    public void setRegModel(String regModel) 
    {
        this.regModel = regModel;
    }

    public String getRegModel() 
    {
        return regModel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("modelId", getModelId())
            .append("parkinglotId", getParkinglotId())
            .append("sizeModel", getSizeModel())
            .append("regModel", getRegModel())
            .toString();
    }
}
