package com.hanhwa_tae.secondserver.user.me.query.service;

import com.hanhwa_tae.secondserver.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.secondserver.common.domain.TargetType;
import com.hanhwa_tae.secondserver.common.exception.BusinessException;
import com.hanhwa_tae.secondserver.common.exception.ErrorCode;
import com.hanhwa_tae.secondserver.delivery.query.dto.response.DeliveryAddressDTO;
import com.hanhwa_tae.secondserver.delivery.query.dto.response.DeliveryAddressResponse;
import com.hanhwa_tae.secondserver.user.me.query.dto.GoodsOrderHistoryDTO;
import com.hanhwa_tae.secondserver.user.me.query.dto.PackageOrderHistoryDTO;
import com.hanhwa_tae.secondserver.user.me.query.mapper.MyPageMapper;
import com.hanhwa_tae.secondserver.user.query.dto.response.UserBoardDTO;
import com.hanhwa_tae.secondserver.user.query.dto.response.UserBoardResponse;
import com.hanhwa_tae.secondserver.user.query.dto.response.UserCommentDTO;
import com.hanhwa_tae.secondserver.user.query.dto.response.UserCommentResponse;
import com.hanhwa_tae.secondserver.user.query.dto.response.UserReviewDTO;
import com.hanhwa_tae.secondserver.user.query.dto.response.UserReviewResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageQueryService {

    private final MyPageMapper myPageMapper;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<PackageOrderHistoryDTO> getPackageOrderHistory(Long userNo) {
        return myPageMapper.findPackageOrderHistoryByUserNo(userNo);
    }

    @Transactional(readOnly = true)
    public List<GoodsOrderHistoryDTO> getGoodsOrderHistory(Long userNo) {
        return myPageMapper.findGoodsOrderHistoryByUserNo(userNo);
    }

    @Transactional(readOnly = true)
    public UserReviewResponse getUserReview(CustomUserDetail userDetail, TargetType targetType) {

        if (targetType.equals(TargetType.PLACE)) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED_REQUEST);
        }

        Long userNo = userDetail.getUserNo();

        List<UserReviewDTO> userReview = myPageMapper.findReviewByUserNoAndTargetType(userNo, targetType);


        return UserReviewResponse.builder()
                .userReviewList(userReview)
                .build();
    }

    @Transactional(readOnly = true)
    public UserBoardResponse getUserBoard(CustomUserDetail userDetail) {
        Long userNo = userDetail.getUserNo();

        List<UserBoardDTO> boardList = myPageMapper.findBoardByUserNo(userNo);

        return UserBoardResponse.builder()
                .BoardList(boardList)
                .build();
    }

    @Transactional(readOnly = true)
    public UserCommentResponse getUserComment(CustomUserDetail userDetail) {

        Long userNo = userDetail.getUserNo();

        List<UserCommentDTO> commentList = myPageMapper.findCommentByUserNo(userNo);


        return UserCommentResponse.builder()
                .commentList(commentList)
                .build();
    }

    @Transactional(readOnly = true)
    public DeliveryAddressResponse getDeliveryAddressList(CustomUserDetail userDetail) {

        Long userNo = userDetail.getUserNo();

        List<DeliveryAddressDTO> addressList = myPageMapper.findDeliveryAddressByUserNo(userNo);

        return DeliveryAddressResponse.builder()
                .addressList(addressList)
                .build();
    }

    @Transactional(readOnly = true)
    public DeliveryAddressDTO getDeliveryAddress(CustomUserDetail userDetail, int deliveryAddressId) {
        Long userNo = userDetail.getUserNo();
        return myPageMapper.findDeliveryAddressById(userNo, deliveryAddressId);
    }


}
