package com.xinghui.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinghui.config.GlobalException;
import com.xinghui.constants.ResultCode;
import com.xinghui.dao.SettlementListingMapper;
import com.xinghui.dto.SettlementListingDTO;
import com.xinghui.entity.SettlementListingDO;
import com.xinghui.enums.SettlementTypeEnum;
import com.xinghui.vo.SettlementListingVO;
import com.xinghui.service.SettlementListingService;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 结算清单 服务实现类
 * </p>
 *
 * @author ckx
 * @since 2021-10-08
 */
@Service
public class SettlementListingServiceImpl extends ServiceImpl<SettlementListingMapper, SettlementListingDO> implements SettlementListingService {

    @Resource
    private MapperFacade mapperFacade;

    @Override
    public Page<SettlementListingVO> page(int pageNum, int pageSize, Integer settlement, String settlementByName, String gainByName) {
        Page<SettlementListingVO> page = new Page<>(pageNum, pageSize);
        List<SettlementListingVO> list = baseMapper.page(page, settlement, settlementByName, gainByName);
        list.forEach(settlementListingVO -> {
            settlementListingVO.setSettlementName("未结算");
            if (settlementListingVO.getSettlement()) {
                settlementListingVO.setSettlementName("已结算");
            }
        });
        page.setRecords(list);
        return page;
    }

    @Override
    public Boolean update(SettlementListingDTO settlementListingDTO) {
        validation(settlementListingDTO);
        SettlementListingDO settlementListingDO = mapperFacade.map(settlementListingDTO, SettlementListingDO.class);
        return updateById(settlementListingDO);
    }

    private void validation(SettlementListingDTO settlementListingDTO) {

        if (settlementListingDTO.getGainBy() == null) {
            throw new GlobalException(ResultCode.GAINBY_NOT_NULL);
        }

        if (settlementListingDTO.getSettlementBy() == null) {
            throw new GlobalException(ResultCode.SETTLEMENTBY_NOT_NULL);
        }

        if (settlementListingDTO.getPlanDate() == null) {
            throw new GlobalException(ResultCode.PLANDATE_NOT_NULL);
        }

        if (StringUtils.isBlank(settlementListingDTO.getAddress())) {
            throw new GlobalException(ResultCode.ADDRESS_NOT_NULL);
        }

        if (StringUtils.isBlank(settlementListingDTO.getMoney())) {
            throw new GlobalException(ResultCode.MONEY_NOT_NULL);
        }

        if (settlementListingDTO.getSettlementLong() == null) {
            throw new GlobalException(ResultCode.SETTLEMENT_NOT_NULL);
        }

        settlementListingDTO.setSettlement(true);
        if (settlementListingDTO.getSettlementLong().equals(SettlementTypeEnum.NOT.getCode())) {
            settlementListingDTO.setSettlement(false);
        }

    }
}
