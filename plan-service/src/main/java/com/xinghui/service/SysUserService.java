package com.xinghui.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xinghui.entity.SysUserDO;
import com.xinghui.vo.SelectVO;

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

}
