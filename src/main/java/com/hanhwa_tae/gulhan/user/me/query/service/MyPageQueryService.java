package com.hanhwa_tae.gulhan.user.me.query.service;

import com.hanhwa_tae.gulhan.common.exception.BusinessException;
import com.hanhwa_tae.gulhan.common.exception.ErrorCode;
import com.hanhwa_tae.gulhan.user.me.query.dto.GoodsOrderHistoryDTO;
import com.hanhwa_tae.gulhan.user.me.query.dto.PackageOrderHistoryDTO;
import com.hanhwa_tae.gulhan.user.me.query.mapper.MyPageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageQueryService {

    private final MyPageMapper myPageMapper;

    @Transactional(readOnly = true)
    public List<PackageOrderHistoryDTO> getPackageOrderHistory(Long userNo) {
        return myPageMapper.findPackageOrderHistoryByUserNo(userNo);
    }

    @Transactional(readOnly = true)
    public List<GoodsOrderHistoryDTO> getGoodsOrderHistory(Long userNo) {
        return myPageMapper.findGoodsOrderHistoryByUserNo(userNo);
    }

}
