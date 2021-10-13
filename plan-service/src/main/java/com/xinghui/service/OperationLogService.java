package com.xinghui.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinghui.entity.OperationLogDO;
import com.xinghui.vo.OperationLogVO;

/**
 * <p>
 * 操作日志 服务类
 * </p>
 *
 * @author ckx
 * @since 2021-10-13
 */
public interface OperationLogService extends IService<OperationLogDO> {

    /**
     * 分页
     *
     * @param pageNum  第几页
     * @param pageSize 每页大小
     * @param type     操作类型
     * @param name     操作人
     * @return
     */
    Page<OperationLogVO> page(int pageNum, int pageSize, Integer type, String name);

}
