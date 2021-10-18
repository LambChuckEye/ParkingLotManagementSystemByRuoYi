package com.ruoyi.custom.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.custom.domain.CusParkinglot;
import com.ruoyi.custom.service.ICusParkinglotService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 停车场信息管理Controller
 *
 * @author kamiu
 * @date 2021-09-17
 */
@Controller
@RequestMapping("/custom/parkinglot")
public class CusParkinglotController extends BaseController {
    private String prefix = "custom/parkinglot";

    @Autowired
    private ICusParkinglotService cusParkinglotService;

    @RequiresPermissions("custom:parkinglot:view")
    @GetMapping()
    public String parkinglot() {
        return prefix + "/parkinglot";
    }

    /**
     * 初始化测试接口
     * @return
     */
    @GetMapping("/init")
    public String parkinglotInit() {
        return prefix + "/init";
    }
    @GetMapping("/init/{Id}")
    public String parkinglotInitId(@PathVariable("Id") Long Id) {
        System.out.println(Id);
        return prefix + "/init";
    }


    /**
     * 查询停车场信息管理列表
     */
    @RequiresPermissions("custom:parkinglot:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CusParkinglot cusParkinglot) {
        startPage();
        List<CusParkinglot> list = cusParkinglotService.selectCusParkinglotList(cusParkinglot);
        return getDataTable(list);
    }

    /**
     * 导出停车场信息管理列表
     */
    @RequiresPermissions("custom:parkinglot:export")
    @Log(title = "停车场信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CusParkinglot cusParkinglot) {
        List<CusParkinglot> list = cusParkinglotService.selectCusParkinglotList(cusParkinglot);
        ExcelUtil<CusParkinglot> util = new ExcelUtil<CusParkinglot>(CusParkinglot.class);
        return util.exportExcel(list, "停车场信息管理数据");
    }

    /**
     * 新增停车场信息管理
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存停车场信息管理
     */
    @RequiresPermissions("custom:parkinglot:add")
    @Log(title = "停车场信息管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CusParkinglot cusParkinglot) {
        return toAjax(cusParkinglotService.insertCusParkinglot(cusParkinglot));
    }

    /**
     * 修改停车场信息管理
     */
    @GetMapping("/edit/{parkinglotId}")
    public String edit(@PathVariable("parkinglotId") Long parkinglotId, ModelMap mmap) {
        CusParkinglot cusParkinglot = cusParkinglotService.selectCusParkinglotByParkinglotId(parkinglotId);
        mmap.put("cusParkinglot", cusParkinglot);
        return prefix + "/edit";
    }

    /**
     * 修改保存停车场信息管理
     */
    @RequiresPermissions("custom:parkinglot:edit")
    @Log(title = "停车场信息管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CusParkinglot cusParkinglot) {
        return toAjax(cusParkinglotService.updateCusParkinglot(cusParkinglot));
    }

    /**
     * 删除停车场信息管理
     */
    @RequiresPermissions("custom:parkinglot:remove")
    @Log(title = "停车场信息管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(cusParkinglotService.deleteCusParkinglotByParkinglotIds(ids));
    }
}
