package com.xinghui.ctrl.mgt;

import com.xinghui.dto.LoginInfoDTO;
import com.xinghui.dto.RegisterDTO;
import com.xinghui.entity.SysUserDO;
import com.xinghui.enums.TerminalTypeEnum;
import com.xinghui.service.AccountInfoService;
import com.xinghui.utils.RequestContextUtil;
import com.xinghui.utils.ResponseUtil;
import com.xinghui.utils.ResultDTO;
import com.xinghui.vo.LoginResVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/amg/auth")
@Slf4j
@Api(tags = {"管理端api-鉴权"})
public class AuthCtrl {

    @Resource
    private AccountInfoService accountService;

    @Resource
    private MapperFacade mapperFacade;

    @PostMapping("/v1/login")
    @ApiOperation(value = "登入")
    public ResultDTO login(@RequestBody LoginInfoDTO loginInfoDTO) {
        //获取用户信息
        SysUserDO sysUserDO = accountService.login(loginInfoDTO.getAccountName(), loginInfoDTO.getPassword());
        //生成token
        String token = accountService.getToken(sysUserDO.getId(), TerminalTypeEnum.PC);
        LoginResVO loginResVO = mapperFacade.map(sysUserDO, LoginResVO.class);
        loginResVO.setUserId(sysUserDO.getId());
        loginResVO.setAccessToken(token);
        return ResponseUtil.success(loginResVO);
    }

    @PostMapping("/v1/register")
    @ApiOperation(value = "注册")
    public ResultDTO register(@RequestBody RegisterDTO registerDTO) {
        //注册
        String passWord = accountService.register(registerDTO.getUsername(), registerDTO.getNameCn());
        //todo 发送邮箱(密码默认123456)
        return ResponseUtil.success(passWord);
    }

    @PostMapping("/v1/logout")
    @ApiOperation(value = "撤销")
    public ResultDTO logout() {
        accountService.removeToken(RequestContextUtil.userId(), RequestContextUtil.terminal());
        return ResponseUtil.success(true);
    }

}
