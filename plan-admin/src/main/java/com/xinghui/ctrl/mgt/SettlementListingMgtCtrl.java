package com.xinghui.ctrl.mgt;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinghui.config.OperationLog;
import com.xinghui.dto.SettlementListingDTO;
import com.xinghui.entity.SettlementListingDO;
import com.xinghui.enums.OperationLogTypeEnum;
import com.xinghui.enums.SettlementTypeEnum;
import com.xinghui.service.SettlementListingService;
import com.xinghui.service.SysUserService;
import com.xinghui.utils.ResponseUtil;
import com.xinghui.utils.ResultDTO;
import com.xinghui.vo.SelectVO;
import com.xinghui.vo.SettlementExportVO;
import com.xinghui.vo.SettlementListingVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ma.glasnost.orika.MapperFacade;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 结算清单 前端控制器
 * </p>
 *
 * @author ckx
 * @since 2021-10-08
 */
@Controller
@RequestMapping("/amg/settlement")
@Api(tags = {"管理端api-结算清单"})
public class SettlementListingMgtCtrl {

    @Resource
    private SettlementListingService settlementListingService;

    @Resource
    private MapperFacade mapperFacade;

    @Resource
    private SysUserService sysUserService;

    @GetMapping("/v1")
    @ApiOperation(value = "分页")
    @ResponseBody
    public ResultDTO page(@RequestParam(defaultValue = "1") int pageNum,
                          @RequestParam(defaultValue = "10") int pageSize,
                          @RequestParam(required = false) Integer settlement,
                          @RequestParam(required = false) String settlementByName,
                          @RequestParam(required = false) String gainByName) {
        return ResponseUtil.success(settlementListingService.page(pageNum, pageSize, settlement, settlementByName, gainByName));
    }

    @DeleteMapping("/v1/{id}")
    @ApiOperation(value = "删除")
    @ResponseBody
    @OperationLog(content = "删除", type = OperationLogTypeEnum.PLAN)
    public ResultDTO delete(@PathVariable Long id) {
        return ResponseUtil.success(settlementListingService.removeById(id));
    }

    @PutMapping("/v1/{id}")
    @ApiOperation(value = "编辑")
    @ResponseBody
    @OperationLog(content = "编辑", type = OperationLogTypeEnum.PLAN)
    public ResultDTO update(@PathVariable Long id, @RequestBody SettlementListingDTO settlementListingDTO) {
        settlementListingDTO.setId(id);
        return ResponseUtil.success(settlementListingService.update(settlementListingDTO));
    }

    @PostMapping("/v1")
    @ApiOperation(value = "新增")
    @ResponseBody
    @OperationLog(content = "新增", type = OperationLogTypeEnum.PLAN)
    public ResultDTO save(@RequestBody SettlementListingDTO settlementListingDTO) {
        return ResponseUtil.success(settlementListingService.save(settlementListingDTO));
    }

    @GetMapping("/v1/query")
    @ApiOperation(value = "查询")
    public ModelAndView query(@RequestParam(required = false) Long id) {
        ModelAndView modelAndView;
        List<SelectVO> userList = sysUserService.lists();
        if (id == null) {
            modelAndView = new ModelAndView("savePlan");
        } else {
            modelAndView = new ModelAndView("updatePlan");
            SettlementListingDO settlementListingDO = settlementListingService.getById(id);
            SettlementListingVO settlementListingVO = mapperFacade.map(settlementListingDO, SettlementListingVO.class);
            userList.forEach(selectVO -> {
                if (selectVO.getId().equals(settlementListingVO.getGainBy())) {
                    settlementListingVO.setGainByName(selectVO.getName());
                }
                if (selectVO.getId().equals(settlementListingVO.getSettlementBy())) {
                    settlementListingVO.setSettlementByName(selectVO.getName());
                }
            });
            settlementListingVO.setSettlementName("未结清");
            if (settlementListingVO.getSettlement()) {
                settlementListingVO.setSettlementName("已结清");
            }
            modelAndView.addObject("settlementInfo", settlementListingVO);
        }
        modelAndView.addObject("userList", userList);
        modelAndView.addObject("isSettlements", SettlementTypeEnum.getList());
        return modelAndView;
    }

    @GetMapping("/v1/export")
    @ApiOperation(value = "导出excel")
    @ResponseBody
    @OperationLog(content = "导出任务清单", type = OperationLogTypeEnum.PLAN)
    public void export(HttpServletResponse response,
                       @RequestParam(required = false) Integer settlement,
                       @RequestParam(required = false) String settlementByName,
                       @RequestParam(required = false) String gainByName) {
        Page<SettlementListingVO> page = settlementListingService.page(1, Integer.MAX_VALUE, settlement, settlementByName, gainByName);
        List<SettlementExportVO> list = new ArrayList<>();
        if (page != null && CollUtil.isNotEmpty(page.getRecords())) {
            List<SettlementListingVO> settlementListingVOList = page.getRecords();
            list = mapperFacade.mapAsList(settlementListingVOList, SettlementExportVO.class);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("任务清单" + DateUtil.format(new Date(), "yyyy-MM-dd"), "信息"), SettlementExportVO.class, list);
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("任务清单" + DateUtil.format(new Date(), "yyyy-MM-dd"), "UTF-8")+".xls");
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
