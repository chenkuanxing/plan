package com.xinghui.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinghui.entity.SysUserDO;
import com.xinghui.vo.UserInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author ckx
 * @since 2021-09-13
 */
public interface SysUserMapper extends BaseMapper<SysUserDO> {

    /**
     * 获取用户分页列表
     *
     * @param page  分页
     * @param name     姓名
     * @param mobile   手机号
     * @return
     */
    List<UserInfoVO> page(Page page, @Param("name") String name, @Param("mobile") String mobile);

}
