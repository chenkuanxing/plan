package com.xinghui.ctrl.mgt;


import com.xinghui.dto.SettlementListingDTO;
import com.xinghui.entity.SettlementListingDO;
import com.xinghui.enums.SettlementTypeEnum;
import com.xinghui.service.SettlementListingService;
import com.xinghui.service.SysUserService;
import com.xinghui.utils.ResponseUtil;
import com.xinghui.utils.ResultDTO;
import com.xinghui.vo.SelectVO;
import com.xinghui.vo.SettlementListingVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
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
    public ResultDTO delete(@PathVariable Long id) {
        return ResponseUtil.success(settlementListingService.removeById(id));
    }

    @PutMapping("/v1/{id}")
    @ApiOperation(value = "编辑")
    @ResponseBody
    public ResultDTO update(@PathVariable Long id, @RequestBody SettlementListingDTO settlementListingDTO) {
        settlementListingDTO.setId(id);
        return ResponseUtil.success(settlementListingService.update(settlementListingDTO));
    }

    @PostMapping("/v1")
    @ApiOperation(value = "新增")
    @ResponseBody
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

}
