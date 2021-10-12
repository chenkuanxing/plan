package com.xinghui.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinghui.entity.SettlementListingDO;
import com.xinghui.vo.SettlementListingVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 结算清单 Mapper 接口
 * </p>
 *
 * @author ckx
 * @since 2021-10-08
 */
public interface SettlementListingMapper extends BaseMapper<SettlementListingDO> {

    /**
     * 获取结算清单分页列表
     *
     * @param page         分页
     * @param settlement   是否结算
     * @param settlementByName 结算人
     * @param gainByName       获取人
     * @return
     */
    List<SettlementListingVO> page(Page page, @Param("settlement") Integer settlement, @Param("settlementByName") String settlementByName, @Param("gainByName") String gainByName);

}
