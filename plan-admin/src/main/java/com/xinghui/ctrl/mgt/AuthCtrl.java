package com.xinghui.ctrl.mgt;

import com.xinghui.config.GlobalException;
import com.xinghui.config.OperationLog;
import com.xinghui.constants.ResultCode;
import com.xinghui.dto.LoginInfoDTO;
import com.xinghui.dto.RegisterDTO;
import com.xinghui.dto.UpdatePassWordDTO;
import com.xinghui.entity.AccountInfoDO;
import com.xinghui.entity.SysUserDO;
import com.xinghui.enums.OperationLogTypeEnum;
import com.xinghui.enums.TerminalTypeEnum;
import com.xinghui.service.AccountInfoService;
import com.xinghui.service.SysUserService;
import com.xinghui.utils.RequestContextUtil;
import com.xinghui.utils.ResponseUtil;
import com.xinghui.utils.ResultDTO;
import com.xinghui.vo.LoginResVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

    @Resource
    private SysUserService sysUserService;

    @Resource
    private BCryptPasswordEncoder encoder;

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
    @OperationLog(content = "撤销登入",type = OperationLogTypeEnum.LOGIN)
    public ResultDTO logout() {
        accountService.removeToken(RequestContextUtil.userId(), RequestContextUtil.terminal());
        return ResponseUtil.success(true);
    }

    @PostMapping("/v1/update-password")
    @ApiOperation(value = "修改密码")
    @OperationLog(content = "修改密码",type = OperationLogTypeEnum.LOGIN)
    public ResultDTO updatePassWord(@RequestBody UpdatePassWordDTO updatePassWordDTO) {
        if (StringUtils.isBlank(updatePassWordDTO.getNewPassword())) {
            throw new GlobalException(ResultCode.NEW_PASSWORD_NOT_NULL);
        }
        if (StringUtils.isBlank(updatePassWordDTO.getConfirmNewPassword())) {
            throw new GlobalException(ResultCode.CONFIRM_NEW_PASSWORD_NOT_NULL);
        }
        if (!updatePassWordDTO.getNewPassword().equals(updatePassWordDTO.getConfirmNewPassword())) {
            throw new GlobalException(ResultCode.NOT_CONFIRM_NEW_PASSWORD);
        }

        //获取人员信息
        SysUserDO sysUserDO = sysUserService.getById(RequestContextUtil.userId());
        //获取登入信息
        AccountInfoDO accountInfoDO = accountService.getById(sysUserDO.getAccountId());
        accountInfoDO.setPassword(encoder.encode(updatePassWordDTO.getConfirmNewPassword()));
        //更新密码
        accountService.updateById(accountInfoDO);
        //撤销token
        accountService.removeToken(RequestContextUtil.userId(), RequestContextUtil.terminal());
        return ResponseUtil.success(true);
    }

    @PostMapping("/v1/reset-password/{userId}")
    @ApiOperation(value = "重置密码")
    @OperationLog(content = "重置密码",type = OperationLogTypeEnum.USER)
    public ResultDTO resetPassWord(@PathVariable Long userId) {
        //获取人员信息
        SysUserDO sysUserDO = sysUserService.getById(userId);
        //获取登入信息
        AccountInfoDO accountInfoDO = accountService.getById(sysUserDO.getAccountId());
        accountInfoDO.setPassword(encoder.encode("123456"));
        //更新密码
        accountService.updateById(accountInfoDO);
        //撤销token
        accountService.removeToken(userId, RequestContextUtil.terminal());
        return ResponseUtil.success(true);
    }

}
