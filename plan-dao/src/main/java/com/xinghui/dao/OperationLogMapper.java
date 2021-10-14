package com.xinghui.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinghui.entity.OperationLogDO;
import com.xinghui.vo.OperationLogVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 操作日志 Mapper 接口
 * </p>
 *
 * @author ckx
 * @since 2021-10-13
 */
public interface OperationLogMapper extends BaseMapper<OperationLogDO> {

    /**
     * 分页
     *
     * @param page 分页
     * @param type 操作类型
     * @param name 操作人
     * @return
     */
    List<OperationLogVO> page(Page<OperationLogVO> page, @Param("type") Integer type, @Param("name") String name);

}
