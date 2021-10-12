package com.xinghui.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinghui.dto.SettlementListingDTO;
import com.xinghui.entity.SettlementListingDO;
import com.xinghui.vo.SettlementListingVO;

/**
 * <p>
 * 结算清单 服务类
 * </p>
 *
 * @author ckx
 * @since 2021-10-08
 */
public interface SettlementListingService extends IService<SettlementListingDO> {

    /**
     * 获取结算清单分页列表
     *
     * @param pageNum          第几页
     * @param pageSize         每页大小
     * @param settlement       是否结算
     * @param settlementByName 结算人
     * @param gainByName       获取人
     * @return 分页
     */
    Page<SettlementListingVO> page(int pageNum, int pageSize, Integer settlement, String settlementByName, String gainByName);

    /**
     * 编辑
     *
     * @param settlementListingDTO 编辑对象
     * @return boolean
     */
    Boolean update(SettlementListingDTO settlementListingDTO);

    /**
     * 新增
     *
     * @param settlementListingDTO 新增对象
     * @return boolean
     */
    Boolean save(SettlementListingDTO settlementListingDTO);

}
