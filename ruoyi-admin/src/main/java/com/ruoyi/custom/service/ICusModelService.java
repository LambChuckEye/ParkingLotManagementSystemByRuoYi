package com.ruoyi.custom.service;

import java.util.List;
import com.ruoyi.custom.domain.CusModel;

/**
 * 模型信息管理Service接口
 * 
 * @author kamiu
 * @date 2021-09-17
 */
public interface ICusModelService 
{
    /**
     * 查询模型信息管理
     * 
     * @param modelId 模型信息管理主键
     * @return 模型信息管理
     */
    public CusModel selectCusModelByModelId(Long modelId);

    /**
     * 查询模型信息管理列表
     * 
     * @param cusModel 模型信息管理
     * @return 模型信息管理集合
     */
    public List<CusModel> selectCusModelList(CusModel cusModel);

    /**
     * 新增模型信息管理
     * 
     * @param cusModel 模型信息管理
     * @return 结果
     */
    public int insertCusModel(CusModel cusModel);

    /**
     * 修改模型信息管理
     * 
     * @param cusModel 模型信息管理
     * @return 结果
     */
    public int updateCusModel(CusModel cusModel);

    /**
     * 批量删除模型信息管理
     * 
     * @param modelIds 需要删除的模型信息管理主键集合
     * @return 结果
     */
    public int deleteCusModelByModelIds(String modelIds);

    /**
     * 删除模型信息管理信息
     * 
     * @param modelId 模型信息管理主键
     * @return 结果
     */
    public int deleteCusModelByModelId(Long modelId);
}
