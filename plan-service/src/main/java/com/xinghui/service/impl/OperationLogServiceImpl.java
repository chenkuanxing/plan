package com.xinghui.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinghui.dao.OperationLogMapper;
import com.xinghui.entity.OperationLogDO;
import com.xinghui.enums.OperationLogTypeEnum;
import com.xinghui.service.OperationLogService;
import com.xinghui.vo.OperationLogVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 操作日志 服务实现类
 * </p>
 *
 * @author ckx
 * @since 2021-10-13
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLogDO> implements OperationLogService {

    @Override
    public Page<OperationLogVO> page(int pageNum, int pageSize, Integer type, String name) {
        Page<OperationLogVO> page = new Page<>(pageNum, pageSize);
        List<OperationLogVO> list = baseMapper.page(page, type, name);
        list.forEach(operationLogVO -> {
            operationLogVO.setTypeName(OperationLogTypeEnum.getDesc(Long.valueOf(operationLogVO.getType())));
        });
        page.setRecords(list);
        return page;
    }

}
