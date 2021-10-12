package com.xinghui.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinghui.config.GlobalException;
import com.xinghui.constants.ResultCode;
import com.xinghui.dao.AccountInfoMapper;
import com.xinghui.entity.AccountInfoDO;
import com.xinghui.entity.SysUserDO;
import com.xinghui.enums.TerminalTypeEnum;
import com.xinghui.service.AccountInfoService;
import com.xinghui.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.MessageFormat;

/**
 * <p>
 * 登入信息表 服务实现类
 * </p>
 *
 * @author ckx
 * @since 2021-09-13
 */
@Service
public class AccountInfoServiceImpl extends ServiceImpl<AccountInfoMapper, AccountInfoDO> implements AccountInfoService {

    @Resource
    private SysUserService userService;

    @Resource
    private BCryptPasswordEncoder encoder;

    @Resource
    private AccountInfoService accountInfoService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Value("${jwt.overdue-time}")
    private Long overdueTime;

    @Override
    public SysUserDO login(String accountName, String password) {
        AccountInfoDO account = getOne(new LambdaQueryWrapper<AccountInfoDO>().eq(AccountInfoDO::getAccountName, accountName));

        if (account == null) {
            throw new RuntimeException("用户不存在");
        }

        // 被禁用
        if (!account.getEnabled()) {
            throw new RuntimeException("用户被禁用");
        }

        //密码错误
        if (!encoder.matches(password, account.getPassword())) {
            throw new RuntimeException("密码不正确");
        }

        Long userId = account.getId();
        return userService.getOne(new LambdaQueryWrapper<SysUserDO>()
                .eq(SysUserDO::getAccountId, userId));
    }

    @Override
    public String register(String username, String nameCn) {
        AccountInfoDO accountInfoDO = new AccountInfoDO();
        accountInfoDO.setAccountName(username);
        accountInfoDO.setPassword(encoder.encode("123456"));
        accountInfoService.save(accountInfoDO);

        SysUserDO sysUserDO = new SysUserDO();
        sysUserDO.setNameCn(nameCn);
        sysUserDO.setMobile(username);
        sysUserDO.setAccountId(accountInfoDO.getId());
        userService.save(sysUserDO);
        return "123456";
    }

    @Override
    public String getToken(Long userId, TerminalTypeEnum terminalTypeEnum) {
        String tokenValue = IdUtil.fastSimpleUUID();
        String userKey = MessageFormat.format("{0}_{1}", userId.toString(), terminalTypeEnum.getCode());
        String tokenKey = MessageFormat.format("{0}_{1}", "token", tokenValue);
        String overdueTimeKey = MessageFormat.format("{0}_{1}", "overdueTime", tokenValue);

        //删除token
        removeToken(userId, terminalTypeEnum.getCode());

        JSONObject userInfo = new JSONObject();
        userInfo.put("userId", userId);
        userInfo.put("terminal", terminalTypeEnum.getCode());
        stringRedisTemplate.opsForValue().set(userKey, tokenValue);
        stringRedisTemplate.opsForValue().set(tokenKey, JSONObject.toJSONString(userInfo));
        stringRedisTemplate.opsForValue().set(overdueTimeKey, (System.currentTimeMillis() + overdueTime) + "");
        return tokenValue;
    }

    @Override
    public Long validationToken(String token, String terminal) {
        String tokenKey = MessageFormat.format("{0}_{1}", "token", token);
        String overdueTimeKey = MessageFormat.format("{0}_{1}", "overdueTime", token);

        String userJson = stringRedisTemplate.opsForValue().get(tokenKey);
        //无效的token
        if (StringUtils.isEmpty(userJson)) {
            throw new GlobalException(ResultCode.INVALID_TOKEN);
        }
        JSONObject jsonObject = JSONObject.parseObject(userJson);
        if (!terminal.equals(jsonObject.get("terminal"))) {
            throw new GlobalException(ResultCode.INVALID_TOKEN);
        }

        String overdueTime = stringRedisTemplate.opsForValue().get(overdueTimeKey);
        //过期token
        if (StringUtils.isEmpty(overdueTime)) {
            throw new GlobalException(ResultCode.OVERDUE_TOKEN);
        }
        //获取用户id
        Long userId = Long.valueOf(jsonObject.get("userId").toString());
        if (Long.valueOf(overdueTime) <= System.currentTimeMillis()) {
            //清除token信息
            removeToken(userId, terminal);
            throw new GlobalException(ResultCode.OVERDUE_TOKEN);
        }

        //刷新token
        refreshToken(token);
        return userId;
    }

    @Override
    public void refreshToken(String token) {
        String overdueTimeKey = MessageFormat.format("{0}_{1}", "overdueTime", token);
        stringRedisTemplate.opsForValue().set(overdueTimeKey, (System.currentTimeMillis() + overdueTime) + "");
    }

    @Override
    public void removeToken(Long userId, String terminal) {
        String userKey = MessageFormat.format("{0}_{1}", userId.toString(), terminal);
        String tokenValue = stringRedisTemplate.opsForValue().get(userKey);
        if (StringUtils.isNotBlank(tokenValue)) {
            String oldTokenKey = MessageFormat.format("{0}_{1}", "token", tokenValue);
            String oldOverdueTimeKey = MessageFormat.format("{0}_{1}", "overdueTime", tokenValue);
            stringRedisTemplate.delete(userKey);
            stringRedisTemplate.delete(oldTokenKey);
            stringRedisTemplate.delete(oldOverdueTimeKey);
        }
    }

}
