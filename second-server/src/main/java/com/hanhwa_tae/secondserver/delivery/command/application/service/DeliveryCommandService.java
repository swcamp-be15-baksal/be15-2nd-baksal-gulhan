package com.hanhwa_tae.secondserver.delivery.command.application.service;

import com.hanhwa_tae.secondserver.common.exception.BusinessException;
import com.hanhwa_tae.secondserver.common.exception.ErrorCode;
import com.hanhwa_tae.secondserver.delivery.command.application.dto.request.DeliveryAddressRequest;
import com.hanhwa_tae.secondserver.delivery.command.domain.aggregate.DeliveryAddress;
import com.hanhwa_tae.secondserver.delivery.command.domain.repository.DeliveryAddressRepository;
import com.hanhwa_tae.secondserver.user.command.domain.aggregate.User;
import com.hanhwa_tae.secondserver.user.query.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryCommandService {

    private final DeliveryAddressRepository deliveryAddressRepository;
    private final UserMapper userMapper;

    @Transactional
    public void registerDeliveryAddress(String id, DeliveryAddressRequest request) {
        User user = userMapper.findUserByUserId(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        DeliveryAddress address = DeliveryAddress.builder()
                .address(request.getAddress())
                .receiver(request.getReceiver())
                .receiverPhone(request.getReceiverPhone())
                .user(user)
                .build();

        deliveryAddressRepository.save(address);

        log.info("배송지 등록 완료: 회원 ID={}, 주소={}", id, address.getAddress());
    }

    @Transactional
    public void updateDeliveryAddress(String id, DeliveryAddressRequest request) {
        User user = userMapper.findUserByUserId(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        DeliveryAddress foundAddress = deliveryAddressRepository.findByUser(user)
                .orElseThrow(() -> new BusinessException(ErrorCode.ADDRESS_NOT_FOUND));

        foundAddress.setAddress(request.getAddress());
        foundAddress.setReceiver(request.getReceiver());
        foundAddress.setReceiverPhone(request.getReceiverPhone());

        log.info("배송지 수정 완료: 회원 ID={}, 수정된 주소={}", id, foundAddress.getAddress());
    }

    @Transactional
    public void deleteDeliveryAddress(String id) {
        User user = userMapper.findUserByUserId(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        DeliveryAddress foundAddress = deliveryAddressRepository.findByUser(user)
                .orElseThrow(() -> new BusinessException(ErrorCode.ADDRESS_NOT_FOUND));

        deliveryAddressRepository.delete(foundAddress);

        log.info("배송지 삭제 완료: 회원 ID={}", id);
    }
}
