package com.xinghui.handler;

import com.xinghui.config.GlobalException;
import com.xinghui.config.UnAccessTokenAuth;
import com.xinghui.constants.ResultCode;
import com.xinghui.entity.AccountInfoDO;
import com.xinghui.entity.SysUserDO;
import com.xinghui.utils.RequestContextUtil;
import com.xinghui.utils.UserInfo;
import com.xinghui.service.AccountInfoService;
import com.xinghui.service.SysUserService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Value("#{'${edu-eval.filter.white-list}'.split(',')}")
    private List<String> filterWith;

    @Resource
    private AccountInfoService accountService;

    @Resource
    private SysUserService userService;

    @Resource
    private MapperFacade mapperFacade;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("accessToken");
        String terminal = httpServletRequest.getHeader("terminal");

        if (terminal == null) {
            terminal = httpServletRequest.getParameter("terminal");
        }

        String requestUri = httpServletRequest.getRequestURI();

        //不进行拦截的URI配置，常见如验证码、Login接口
        if (isStartWith(requestUri)) {
            return true;
        }

        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();

        //检查是否有UnAccessTokenAuth注释，有则跳过认证
        if (method.isAnnotationPresent(UnAccessTokenAuth.class)) {
            return true;
        }
        // 执行认证
        if (token == null) {
            token = httpServletRequest.getParameter("accessToken");
            if (token == null) {
                throw new GlobalException(ResultCode.NOT_TOKEN);
            }
        }

        // 验证 token 获取 token 中的 accountId
        Long userId = accountService.validationToken(token, terminal);
        //查询用户信息
        SysUserDO userDO = userService.getById(userId);
        if (userDO == null) {
            throw new GlobalException(ResultCode.USER_NOT_EXIST);
        }
        AccountInfoDO accountInfoDO = accountService.getById(userDO.getAccountId());
        if (accountInfoDO == null) {
            throw new GlobalException(ResultCode.USER_NOT_EXIST);
        }

        //保存用户信息
        UserInfo userInfo = mapperFacade.map(userDO, UserInfo.class);
        userInfo.setToken(token);
        userInfo.setTerminal(terminal);
        RequestContextUtil.setUserInfo(userInfo);
        return true;
    }

    /**
     * URI是否以什么打头
     *
     * @param requestUri
     * @return
     */
    private boolean isStartWith(String requestUri) {
        for (String s : filterWith) {
            if (requestUri.startsWith(s)) {
                return true;
            }
        }
        return false;
    }

}
