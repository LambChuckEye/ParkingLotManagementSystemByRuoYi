package com.ruoyi.custom.mapper;

import java.util.List;
import com.ruoyi.custom.domain.CusParkinglot;
import com.ruoyi.custom.domain.CusModel;

/**
 * 停车场信息管理Mapper接口
 * 
 * @author kamiu
 * @date 2021-09-17
 */
public interface CusParkinglotMapper 
{
    /**
     * 查询停车场信息管理
     * 
     * @param parkinglotId 停车场信息管理主键
     * @return 停车场信息管理
     */
    public CusParkinglot selectCusParkinglotByParkinglotId(Long parkinglotId);

    /**
     * 查询停车场信息管理列表
     * 
     * @param cusParkinglot 停车场信息管理
     * @return 停车场信息管理集合
     */
    public List<CusParkinglot> selectCusParkinglotList(CusParkinglot cusParkinglot);

    /**
     * 新增停车场信息管理
     * 
     * @param cusParkinglot 停车场信息管理
     * @return 结果
     */
    public int insertCusParkinglot(CusParkinglot cusParkinglot);

    /**
     * 修改停车场信息管理
     * 
     * @param cusParkinglot 停车场信息管理
     * @return 结果
     */
    public int updateCusParkinglot(CusParkinglot cusParkinglot);

    /**
     * 删除停车场信息管理
     * 
     * @param parkinglotId 停车场信息管理主键
     * @return 结果
     */
    public int deleteCusParkinglotByParkinglotId(Long parkinglotId);

    /**
     * 批量删除停车场信息管理
     * 
     * @param parkinglotIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCusParkinglotByParkinglotIds(String[] parkinglotIds);

    /**
     * 批量删除模型信息管理
     * 
     * @param parkinglotIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCusModelByParkinglotIds(String[] parkinglotIds);
    
    /**
     * 批量新增模型信息管理
     * 
     * @param cusModelList 模型信息管理列表
     * @return 结果
     */
    public int batchCusModel(List<CusModel> cusModelList);
    

    /**
     * 通过停车场信息管理主键删除模型信息管理信息
     * 
     * @param parkinglotId 停车场信息管理ID
     * @return 结果
     */
    public int deleteCusModelByParkinglotId(Long parkinglotId);
}
