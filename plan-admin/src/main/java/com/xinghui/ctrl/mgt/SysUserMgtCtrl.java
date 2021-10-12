package com.xinghui.ctrl.mgt;


import com.xinghui.service.SysUserService;
import com.xinghui.utils.RequestContextUtil;
import com.xinghui.utils.ResponseUtil;
import com.xinghui.utils.ResultDTO;
import com.xinghui.vo.UserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ma.glasnost.orika.MapperFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author ckx
 * @since 2021-09-13
 */
@RestController
@RequestMapping("/amg/user")
@Api(tags = {"管理端api-用户中心"})
public class SysUserMgtCtrl {

    @Resource
    private MapperFacade mapperFacade;

    @Resource
    private SysUserService sysUserService;

    @GetMapping("/v1/query")
    @ApiOperation(value = "查询用户信息")
    public ResultDTO query() {
        return ResponseUtil.success(mapperFacade.map(RequestContextUtil.userInfo(), UserInfoVO.class));
    }

    @GetMapping("/v1/list")
    @ApiOperation(value = "用户信息列表")
    public ResultDTO list() {
        return ResponseUtil.success(sysUserService.lists());
    }

}
