package com.ruoyi.custom.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.custom.domain.CusModel;
import com.ruoyi.custom.mapper.CusParkinglotMapper;
import com.ruoyi.custom.domain.CusParkinglot;
import com.ruoyi.custom.service.ICusParkinglotService;
import com.ruoyi.common.core.text.Convert;

/**
 * 停车场信息管理Service业务层处理
 * 
 * @author kamiu
 * @date 2021-09-17
 */
@Service
public class CusParkinglotServiceImpl implements ICusParkinglotService 
{
    @Autowired
    private CusParkinglotMapper cusParkinglotMapper;

    /**
     * 查询停车场信息管理
     * 
     * @param parkinglotId 停车场信息管理主键
     * @return 停车场信息管理
     */
    @Override
    public CusParkinglot selectCusParkinglotByParkinglotId(Long parkinglotId)
    {
        return cusParkinglotMapper.selectCusParkinglotByParkinglotId(parkinglotId);
    }

    /**
     * 查询停车场信息管理列表
     * 
     * @param cusParkinglot 停车场信息管理
     * @return 停车场信息管理
     */
    @Override
    public List<CusParkinglot> selectCusParkinglotList(CusParkinglot cusParkinglot)
    {
        return cusParkinglotMapper.selectCusParkinglotList(cusParkinglot);
    }

    /**
     * 新增停车场信息管理
     * 
     * @param cusParkinglot 停车场信息管理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertCusParkinglot(CusParkinglot cusParkinglot)
    {
        int rows = cusParkinglotMapper.insertCusParkinglot(cusParkinglot);
        insertCusModel(cusParkinglot);
        return rows;
    }

    /**
     * 修改停车场信息管理
     * 
     * @param cusParkinglot 停车场信息管理
     * @return 结果
     */
    @Transactional
    @Override
    public int updateCusParkinglot(CusParkinglot cusParkinglot)
    {
        cusParkinglotMapper.deleteCusModelByParkinglotId(cusParkinglot.getParkinglotId());
        insertCusModel(cusParkinglot);
        return cusParkinglotMapper.updateCusParkinglot(cusParkinglot);
    }

    /**
     * 批量删除停车场信息管理
     * 
     * @param parkinglotIds 需要删除的停车场信息管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteCusParkinglotByParkinglotIds(String parkinglotIds)
    {
        cusParkinglotMapper.deleteCusModelByParkinglotIds(Convert.toStrArray(parkinglotIds));
        return cusParkinglotMapper.deleteCusParkinglotByParkinglotIds(Convert.toStrArray(parkinglotIds));
    }

    /**
     * 删除停车场信息管理信息
     * 
     * @param parkinglotId 停车场信息管理主键
     * @return 结果
     */
    @Override
    public int deleteCusParkinglotByParkinglotId(Long parkinglotId)
    {
        cusParkinglotMapper.deleteCusModelByParkinglotId(parkinglotId);
        return cusParkinglotMapper.deleteCusParkinglotByParkinglotId(parkinglotId);
    }

    /**
     * 新增模型信息管理信息
     * 
     * @param cusParkinglot 停车场信息管理对象
     */
    public void insertCusModel(CusParkinglot cusParkinglot)
    {
        List<CusModel> cusModelList = cusParkinglot.getCusModelList();
        Long parkinglotId = cusParkinglot.getParkinglotId();
        if (StringUtils.isNotNull(cusModelList))
        {
            List<CusModel> list = new ArrayList<CusModel>();
            for (CusModel cusModel : cusModelList)
            {
                cusModel.setParkinglotId(parkinglotId);
                list.add(cusModel);
            }
            if (list.size() > 0)
            {
                cusParkinglotMapper.batchCusModel(list);
            }
        }
    }
}
