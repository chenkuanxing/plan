package com.xinghui.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinghui.dto.UserInfoDTO;
import com.xinghui.entity.SysUserDO;
import com.xinghui.vo.SelectVO;
import com.xinghui.vo.UserInfoVO;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author ckx
 * @since 2021-09-13
 */
public interface SysUserService extends IService<SysUserDO> {

    /**
     * 获取用户列表
     *
     * @return
     */
    List<SelectVO> lists();

    /**
     * 获取用户分页列表
     *
     * @param pageNum  第几页
     * @param pageSize 每页大小
     * @param name     姓名
     * @param mobile   手机号
     * @return
     */
    Page<UserInfoVO> page(int pageNum, int pageSize, String name, String mobile);

    /**
     * 新增用户
     *
     * @param userInfoDTO
     */
    void save(UserInfoDTO userInfoDTO);

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    void remove(Long id);
}
