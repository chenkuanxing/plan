package com.xinghui.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinghui.config.GlobalException;
import com.xinghui.constants.ResultCode;
import com.xinghui.dao.SysUserMapper;
import com.xinghui.dto.UserInfoDTO;
import com.xinghui.entity.AccountInfoDO;
import com.xinghui.entity.SysUserDO;
import com.xinghui.service.AccountInfoService;
import com.xinghui.service.SysUserService;
import com.xinghui.vo.SelectVO;
import com.xinghui.vo.UserInfoVO;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author ckx
 * @since 2021-09-13
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserDO> implements SysUserService {

    @Resource
    private BCryptPasswordEncoder encoder;

    @Resource
    private AccountInfoService accountInfoService;

    @Resource
    private MapperFacade mapperFacade;

    @Override
    public List<SelectVO> lists() {
        return toSelectVo(list());
    }

    @Override
    public Page<UserInfoVO> page(int pageNum, int pageSize, String name, String mobile) {
        Page<UserInfoVO> page = new Page<>(pageNum, pageSize);
        List<UserInfoVO> list = baseMapper.page(page, name, mobile);
        page.setRecords(list);
        return page;
    }

    @Override
    public void save(UserInfoDTO userInfoDTO) {
        validation(userInfoDTO);
        AccountInfoDO accountInfoDO = new AccountInfoDO();
        accountInfoDO.setAccountName(userInfoDTO.getMobile());
        accountInfoDO.setPassword(encoder.encode("123456"));
        accountInfoService.save(accountInfoDO);

        SysUserDO sysUserDO = mapperFacade.map(userInfoDTO, SysUserDO.class);
        sysUserDO.setAccountId(accountInfoDO.getId());
        save(sysUserDO);
    }

    @Override
    public void remove(Long id) {
        //查询用户信息
        SysUserDO sysUserDO = getById(id);
        //删除用户信息
        removeById(id);
        //删除登入信息
        accountInfoService.removeById(sysUserDO.getAccountId());
    }

    private List<SelectVO> toSelectVo(List<SysUserDO> sysUserDOList) {
        List<SelectVO> selectVOList = new ArrayList<>();
        sysUserDOList.forEach(sysUserDO -> {
            SelectVO selectVO = new SelectVO();
            selectVO.setId(sysUserDO.getId());
            selectVO.setName(sysUserDO.getNameCn());
            selectVOList.add(selectVO);
        });
        return selectVOList;
    }

    private void validation(UserInfoDTO userInfoDTO) {

        if (StringUtils.isBlank(userInfoDTO.getMobile())) {
            throw new GlobalException(ResultCode.MOBILE_NOT_NULL);
        }

        if (StringUtils.isBlank(userInfoDTO.getNameCn())) {
            throw new GlobalException(ResultCode.NAMECN_NOT_NULL);
        }
    }

}
