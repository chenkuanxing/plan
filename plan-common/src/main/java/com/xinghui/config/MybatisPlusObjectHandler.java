package com.xinghui.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <p>
 * 默认字段自动填充原处理器
 * 在创建时填充创建者id
 * 在更新时填充更新者id
 * </p>
 *
 * @author ckx
 * @since 2021-09-13
 */
@Component
public class MybatisPlusObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();

        this.strictInsertFill(metaObject, "createBy", Long.class, 1L);
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, now);

        this.strictInsertFill(metaObject, "updateBy", Long.class, 1L);
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, now);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateBy", 1L, metaObject);
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }

}
