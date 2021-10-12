package com.xinghui.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinghui.dao.SysUserMapper;
import com.xinghui.entity.SysUserDO;
import com.xinghui.vo.SelectVO;
import com.xinghui.service.SysUserService;
import org.springframework.stereotype.Service;

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

    @Override
    public List<SelectVO> lists() {
        return toSelectVo(list());
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

}
