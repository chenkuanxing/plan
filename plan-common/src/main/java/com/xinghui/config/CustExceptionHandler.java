package com.xinghui.config;


import com.xinghui.utils.RedisUtil;
import com.xinghui.utils.ResponseUtil;
import com.xinghui.utils.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


/**
 * @author ckx
 * @since 2021/9/14
 * 统一异常处理类
 */

@Slf4j
@ControllerAdvice
public class CustExceptionHandler {

    @Resource
    private RedisUtil redisUtil;

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultDTO helper(Exception ex) {

        log.error("OH,MY GOD! SOME ERRORS OCCURED! AS FOLLOWS :", ex);

        if (ex instanceof GlobalException) {
            if (((GlobalException) ex).getCode() != null) {
                return ResponseUtil.fail(((GlobalException) ex).getCode(), ex.getMessage());
            } else {
                return ResponseUtil.fail(ex.getMessage());
            }
        } else if (ex instanceof BindException) {
            BindingResult bindingResult = ((BindException) ex).getBindingResult();
            if (bindingResult != null && bindingResult.hasErrors()) {
                return ResponseUtil.fail((bindingResult.getAllErrors().get(0).getDefaultMessage()));
            }
        }


        return ResponseUtil.fail(ex.toString());
    }
}
