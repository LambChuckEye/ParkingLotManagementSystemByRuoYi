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
import com.ruoyi.custom.domain.CusModel;
import com.ruoyi.custom.service.ICusModelService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 模型信息管理Controller
 *
 * @author kamiu
 * @date 2021-09-17
 */
@Controller
@RequestMapping("/custom/model")
public class CusModelController extends BaseController {
    private String prefix = "custom/model";

    @Autowired
    private ICusModelService cusModelService;

    @RequiresPermissions("custom:model:view")
    @GetMapping()
    public String model() {
        return prefix + "/model";
    }

    /**
     * 查询模型信息管理列表
     */
    @RequiresPermissions("custom:model:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CusModel cusModel) {
        startPage();
        List<CusModel> list = cusModelService.selectCusModelList(cusModel);
        return getDataTable(list);
    }

    /**
     * 导出模型信息管理列表
     */
    @RequiresPermissions("custom:model:export")
    @Log(title = "模型信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CusModel cusModel) {
        List<CusModel> list = cusModelService.selectCusModelList(cusModel);
        ExcelUtil<CusModel> util = new ExcelUtil<CusModel>(CusModel.class);
        return util.exportExcel(list, "模型信息管理数据");
    }

    /**
     * 新增模型信息管理
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存模型信息管理
     */
    @RequiresPermissions("custom:model:add")
    @Log(title = "模型信息管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CusModel cusModel) {
        return toAjax(cusModelService.insertCusModel(cusModel));
    }

    /**
     * 修改模型信息管理
     */
    @GetMapping("/edit/{modelId}")
    public String edit(@PathVariable("modelId") Long modelId, ModelMap mmap) {
        CusModel cusModel = cusModelService.selectCusModelByModelId(modelId);
        mmap.put("cusModel", cusModel);
        return prefix + "/edit";
    }

    /**
     * 修改保存模型信息管理
     */
    @RequiresPermissions("custom:model:edit")
    @Log(title = "模型信息管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CusModel cusModel) {
        return toAjax(cusModelService.updateCusModel(cusModel));
    }

    /**
     * 删除模型信息管理
     */
    @RequiresPermissions("custom:model:remove")
    @Log(title = "模型信息管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(cusModelService.deleteCusModelByModelIds(ids));
    }
}
