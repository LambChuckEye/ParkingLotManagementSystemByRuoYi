package com.ruoyi.custom.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.custom.mapper.CusModelMapper;
import com.ruoyi.custom.domain.CusModel;
import com.ruoyi.custom.service.ICusModelService;
import com.ruoyi.common.core.text.Convert;

/**
 * 模型信息管理Service业务层处理
 * 
 * @author kamiu
 * @date 2021-09-17
 */
@Service
public class CusModelServiceImpl implements ICusModelService 
{
    @Autowired
    private CusModelMapper cusModelMapper;

    /**
     * 查询模型信息管理
     * 
     * @param modelId 模型信息管理主键
     * @return 模型信息管理
     */
    @Override
    public CusModel selectCusModelByModelId(Long modelId)
    {
        return cusModelMapper.selectCusModelByModelId(modelId);
    }

    /**
     * 查询模型信息管理列表
     * 
     * @param cusModel 模型信息管理
     * @return 模型信息管理
     */
    @Override
    public List<CusModel> selectCusModelList(CusModel cusModel)
    {
        return cusModelMapper.selectCusModelList(cusModel);
    }

    /**
     * 新增模型信息管理
     * 
     * @param cusModel 模型信息管理
     * @return 结果
     */
    @Override
    public int insertCusModel(CusModel cusModel)
    {
        return cusModelMapper.insertCusModel(cusModel);
    }

    /**
     * 修改模型信息管理
     * 
     * @param cusModel 模型信息管理
     * @return 结果
     */
    @Override
    public int updateCusModel(CusModel cusModel)
    {
        return cusModelMapper.updateCusModel(cusModel);
    }

    /**
     * 批量删除模型信息管理
     * 
     * @param modelIds 需要删除的模型信息管理主键
     * @return 结果
     */
    @Override
    public int deleteCusModelByModelIds(String modelIds)
    {
        return cusModelMapper.deleteCusModelByModelIds(Convert.toStrArray(modelIds));
    }

    /**
     * 删除模型信息管理信息
     * 
     * @param modelId 模型信息管理主键
     * @return 结果
     */
    @Override
    public int deleteCusModelByModelId(Long modelId)
    {
        return cusModelMapper.deleteCusModelByModelId(modelId);
    }
}
