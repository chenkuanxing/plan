package com.xinghui.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xinghui.entity.AccountInfoDO;
import com.xinghui.entity.SysUserDO;
import com.xinghui.enums.TerminalTypeEnum;

/**
 * <p>
 * 登入信息表 服务类
 * </p>
 *
 * @author ckx
 * @since 2021-09-13
 */
public interface AccountInfoService extends IService<AccountInfoDO> {

    /**
     * 登入获取用户信息
     *
     * @param accountName 登入名称
     * @param password 密码（MD5加密）
     * @return 用户信息
     */
    SysUserDO login(String accountName, String password);

    /**
     * 注册
     *
     * @param username 账号
     * @param nameCn   用户名称
     * @return 登入密码
     */
    String register(String username, String nameCn);

    /**
     * 获取token
     *
     * @param userId           户用id
     * @param terminalTypeEnum 终端：pc,mobile
     * @return 授权token
     */
    String getToken(Long userId, TerminalTypeEnum terminalTypeEnum);

    /**
     * 验证token
     *
     * @param token    授权token
     * @param terminal 终端: pc,mobile
     * @return 用户id
     */
    Long validationToken(String token, String terminal);

    /**
     * 刷新token
     *
     * @param token 授权token
     */
    void refreshToken(String token);

    /**
     * 删除token
     *
     * @param userId   用户id
     * @param terminal 终端: pc,mobile
     */
    void removeToken(Long userId, String terminal);

}
