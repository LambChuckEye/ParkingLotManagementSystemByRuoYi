package com.ruoyi.custom.service;

import java.util.List;
import com.ruoyi.custom.domain.CusParkinglot;

/**
 * 停车场信息管理Service接口
 * 
 * @author kamiu
 * @date 2021-09-17
 */
public interface ICusParkinglotService 
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
     * 批量删除停车场信息管理
     * 
     * @param parkinglotIds 需要删除的停车场信息管理主键集合
     * @return 结果
     */
    public int deleteCusParkinglotByParkinglotIds(String parkinglotIds);

    /**
     * 删除停车场信息管理信息
     * 
     * @param parkinglotId 停车场信息管理主键
     * @return 结果
     */
    public int deleteCusParkinglotByParkinglotId(Long parkinglotId);

    /**
     * 调用python脚本进行python预测
     *
     * @param parkinglotId 停车场信息管理主键
     * @return 结果
     */
    public CusParkinglot predictByParkinglotId(Long parkinglotId);

    /**
     * 模型数据初始化
     * @param parkinglotId
     */
    void initById(Long parkinglotId);
}
