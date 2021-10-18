package com.ruoyi.custom.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.custom.domain.CusParkinglot;
import com.ruoyi.custom.service.ICusModelService;
import com.ruoyi.custom.service.ICusParkinglotService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 停车场信息管理Controller
 *
 * @author kamiu
 * @date 2021-09-17
 */
@Controller
@RequestMapping("/custom/pinit")
public class CusPinitController extends BaseController {
    private String prefix = "custom/pinit";

    @Autowired
    private ICusParkinglotService cusParkinglotService;

    @Autowired
    private ICusModelService cusModelService;

    /**
     * 初始化页面跳转
     *
     * @return
     */
    @GetMapping()
    public String init() {
        return prefix + "/pinit";
    }


    /**
     * 车位查询页面跳转
     *
     * @return
     */
    @GetMapping("/predict")
    public String detail() {
        return prefix + "/predict";
    }


    /**
     * 查询停车场信息管理列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CusParkinglot cusParkinglot) {
        startPage();
        List<CusParkinglot> list = cusParkinglotService.selectCusParkinglotList(cusParkinglot);
        return getDataTable(list);
    }


    /**
     * 停车场信息初始化
     *
     * @param parkinglotId
     */
    @GetMapping("/initModel/{parkinglotId}")
    public String initModel(@PathVariable("parkinglotId") Long parkinglotId, ModelMap mmap) {
        cusParkinglotService.initById(parkinglotId);
        return prefix + "/pinit";
    }


    /**
     * 查询停车场车位信息，回显渲染图
     *
     * @param parkinglotId
     * @param mmap
     * @return
     */
    @GetMapping("/detail/{parkinglotId}")
    public String predict(@PathVariable("parkinglotId") Long parkinglotId, ModelMap mmap) {
        CusParkinglot cusParkinglot = cusParkinglotService.predictByParkinglotId(parkinglotId);
        mmap.put("cusParkinglot", cusParkinglot);
        return prefix + "/detail";
    }


}
