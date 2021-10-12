package com.xinghui.ctrl.mgt;


import com.xinghui.dto.UserInfoDTO;
import com.xinghui.entity.SysUserDO;
import com.xinghui.service.SysUserService;
import com.xinghui.utils.RequestContextUtil;
import com.xinghui.utils.ResponseUtil;
import com.xinghui.utils.ResultDTO;
import com.xinghui.vo.UserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author ckx
 * @since 2021-09-13
 */
@Controller
@RequestMapping("/amg/user")
@Api(tags = {"管理端api-用户中心"})
public class SysUserMgtCtrl {

    @Resource
    private MapperFacade mapperFacade;

    @Resource
    private SysUserService sysUserService;

    @GetMapping("/v1/query")
    @ApiOperation(value = "查询用户信息")
    @ResponseBody
    public ResultDTO query() {
        return ResponseUtil.success(mapperFacade.map(RequestContextUtil.userInfo(), UserInfoVO.class));
    }

    @GetMapping("/v1/list")
    @ApiOperation(value = "用户信息列表")
    @ResponseBody
    public ResultDTO list() {
        return ResponseUtil.success(sysUserService.lists());
    }

    @GetMapping("/v1/user-info")
    @ApiOperation(value = "查询用户详细信息")
    public ModelAndView userInfo() {
        ModelAndView modelAndView = new ModelAndView("userInfo");
        modelAndView.addObject("userInfo", mapperFacade.map(sysUserService.getById(RequestContextUtil.userId()), UserInfoVO.class));
        return modelAndView;
    }

    @PutMapping("/v1")
    @ApiOperation(value = "修改用户信息")
    @ResponseBody
    public ResultDTO update(@RequestBody UserInfoDTO userInfoDTO) {
        SysUserDO sysUserDO = mapperFacade.map(userInfoDTO, SysUserDO.class);
        sysUserDO.setId(RequestContextUtil.userId());
        return ResponseUtil.success(sysUserService.updateById(sysUserDO));
    }

}
