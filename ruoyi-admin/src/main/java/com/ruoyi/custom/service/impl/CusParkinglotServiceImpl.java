package com.ruoyi.custom.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

import com.ruoyi.custom.mapper.CusModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class CusParkinglotServiceImpl implements ICusParkinglotService {

    static String PYTHON_EXP = "D:/app/Anaconda/envs/python364/python.exe";
    static String SCRIPT = "park_test.py";
    static String PROFILE_PRE = "/profile";

    @Autowired
    private CusParkinglotMapper cusParkinglotMapper;

    @Autowired
    private CusModelMapper cusModelMapper;

    @Value("${ruoyi.prefix}")
    private String prefix;

    @Value("${ruoyi.profile}")
    private String profile;

    /**
     * 查询停车场信息管理
     *
     * @param parkinglotId 停车场信息管理主键
     * @return 停车场信息管理
     */
    @Override
    public CusParkinglot selectCusParkinglotByParkinglotId(Long parkinglotId) {
        return cusParkinglotMapper.selectCusParkinglotByParkinglotId(parkinglotId);
    }

    /**
     * 查询停车场信息管理列表
     *
     * @param cusParkinglot 停车场信息管理
     * @return 停车场信息管理
     */
    @Override
    public List<CusParkinglot> selectCusParkinglotList(CusParkinglot cusParkinglot) {
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
    public int insertCusParkinglot(CusParkinglot cusParkinglot) {
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
    public int updateCusParkinglot(CusParkinglot cusParkinglot) {
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
    public int deleteCusParkinglotByParkinglotIds(String parkinglotIds) {
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
    public int deleteCusParkinglotByParkinglotId(Long parkinglotId) {
        cusParkinglotMapper.deleteCusModelByParkinglotId(parkinglotId);
        return cusParkinglotMapper.deleteCusParkinglotByParkinglotId(parkinglotId);
    }

    /**
     * 新增模型信息管理信息
     *
     * @param cusParkinglot 停车场信息管理对象
     */
    public void insertCusModel(CusParkinglot cusParkinglot) {
        List<CusModel> cusModelList = cusParkinglot.getCusModelList();
        Long parkinglotId = cusParkinglot.getParkinglotId();
        if (StringUtils.isNotNull(cusModelList)) {
            List<CusModel> list = new ArrayList<CusModel>();
            for (CusModel cusModel : cusModelList) {
                cusModel.setParkinglotId(parkinglotId);
                list.add(cusModel);
            }
            if (list.size() > 0) {
                cusParkinglotMapper.batchCusModel(list);
            }
        }
    }

    /**
     * 调用python脚本预测车位信息
     *
     * @param parkinglotId 停车场信息管理主键
     * @return 结果
     */
    @Override
    public CusParkinglot predictByParkinglotId(Long parkinglotId) {
        //读取停车场信息
        CusParkinglot cusParkinglot = cusParkinglotMapper.selectCusParkinglotByParkinglotId(parkinglotId);
        CusModel cusModel = new CusModel();
        cusModel.setParkinglotId(parkinglotId);
        List<CusModel> cusModels = cusModelMapper.selectCusModelList(cusModel);

        //生成渲染图名称
        StringBuilder sb = new StringBuilder(cusParkinglot.getParkinglotCamera());
        sb.insert(sb.length() - 4, "-show");
        String image = sb.toString();

        //脚本加载
        String script = prefix + SCRIPT;

        //路径转换
        String net = cusModels.get(0).getRegModel().replace(PROFILE_PRE, profile);
        String pickle = cusModels.get(0).getSizeModel().replace(PROFILE_PRE, profile);
        String camera = cusParkinglot.getParkinglotCamera().replace(PROFILE_PRE, profile);
        String show = image.replace(PROFILE_PRE, profile);

        ArrayList<String> result = new ArrayList<>();

        //组合控制台语句
        String cmd = PYTHON_EXP + " " +
                script + " predict " +
                net + " " +
                pickle + " " +
                camera + " " +
                show;

        //控制台执行流程
        Runtime run = Runtime.getRuntime();//返回与当前 Java 应用程序相关的运行时对象
        try {
            java.lang.Process p = run.exec(cmd);// 启动另一个进程来执行命令
            BufferedInputStream in = new BufferedInputStream(p.getInputStream());
            BufferedReader inBr = new BufferedReader(new InputStreamReader(in));

            String lineStr;
            while ((lineStr = inBr.readLine()) != null)
                //获得控制台结果
                result.add(lineStr);
            //检查命令是否执行失败。
            if (p.waitFor() != 0) {
                if (p.exitValue() == 1)//p.exitValue()==0表示正常结束，1：非正常结束
                    System.err.println("命令执行失败!");
            }
            inBr.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //分割结果字符串
        String[] arr = result.get(0).split(" ");

        //更新停车场
        cusParkinglot.setImage(image);
        cusParkinglot.setImageUpdate(new Date());
        cusParkinglot.setParkinglotSpare(Long.valueOf(arr[1]));
        cusParkinglotMapper.updateCusParkinglot(cusParkinglot);
        return cusParkinglot;
    }

    @Override
    public void initById(Long parkinglotId) {
        //更新容量
        CusParkinglot cusParkinglot = cusParkinglotMapper.selectCusParkinglotByParkinglotId(parkinglotId);
        cusParkinglot.setParkinglotCapacity(532L);
        cusParkinglotMapper.updateCusParkinglot(cusParkinglot);
        //更新模型地址
        CusModel cusModel = new CusModel();
        cusModel.setParkinglotId(parkinglotId);
        cusModel.setRegModel("/profile/model/net");
        cusModel.setSizeModel("/profile/model/spot_dict.pickle");
        cusModelMapper.insertCusModel(cusModel);
    }
}
