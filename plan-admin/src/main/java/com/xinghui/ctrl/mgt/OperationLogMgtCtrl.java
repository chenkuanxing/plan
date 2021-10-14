package com.xinghui.ctrl.mgt;


import com.xinghui.service.OperationLogService;
import com.xinghui.utils.ResponseUtil;
import com.xinghui.utils.ResultDTO;
import io.swagger.annotations.ApiOperation;
import ma.glasnost.orika.MapperFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 操作日志 前端控制器
 * </p>
 *
 * @author ckx
 * @since 2021-10-13
 */
@RestController
@RequestMapping("/amg/log")
public class OperationLogMgtCtrl {

    @Resource
    private OperationLogService operationLogService;

    @GetMapping("/v1")
    @ApiOperation(value = "操作日志列表")
    public ResultDTO page(@RequestParam(defaultValue = "1") int pageNum,
                          @RequestParam(defaultValue = "10") int pageSize,
                          @RequestParam(required = false) Integer type,
                          @RequestParam(required = false) String name) {
        return ResponseUtil.success(operationLogService.page(pageNum, pageSize, type, name));
    }

}
