package com.hanhwa_tae.secondserver.delivery.command.application.service;

import com.hanhwa_tae.secondserver.common.exception.BusinessException;
import com.hanhwa_tae.secondserver.common.exception.ErrorCode;
import com.hanhwa_tae.secondserver.delivery.command.application.dto.request.DeliveryAddressRequest;
import com.hanhwa_tae.secondserver.delivery.command.domain.aggregate.DefaultAddress;
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

        if (DefaultAddress.Y.equals(request.getDefaultAddress())) {
            // 기본배송지 새로 설정하면 기존에 있던 건 모두 N 처리해야 됨
            deliveryAddressRepository.updateAllDefaultAddressToN(user);
        }

        DeliveryAddress address = DeliveryAddress.builder()
                .zipcode(request.getZipcode())
                .address(request.getAddress())
                .detailAddress(request.getDetailAddress())
                .receiver(request.getReceiver())
                .receiverPhone(request.getReceiverPhone())
                .user(user)
                .defaultAddress(request.getDefaultAddress())
                .build();

        deliveryAddressRepository.save(address);

        log.info("배송지 등록 완료: 회원 ID={}, 주소={}", id, address.getAddress());
    }

    @Transactional
    public void updateDeliveryAddress(String id, int deliveryAddressId, DeliveryAddressRequest request) {
        User user = userMapper.findUserByUserId(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        if (DefaultAddress.Y.equals(request.getDefaultAddress())) {
            deliveryAddressRepository.updateAllDefaultAddressToN(user);
        }

        DeliveryAddress foundAddress = deliveryAddressRepository.findByDeliveryAddressIdAndUser(deliveryAddressId, user)
                .orElseThrow(() -> new BusinessException(ErrorCode.ADDRESS_NOT_FOUND));

        foundAddress.setZipcode(request.getZipcode());
        foundAddress.setAddress(request.getAddress());
        foundAddress.setDetailAddress(request.getDetailAddress());
        foundAddress.setReceiver(request.getReceiver());
        foundAddress.setReceiverPhone(request.getReceiverPhone());

        if (request.getDefaultAddress() != null) {
            foundAddress.setDefaultAddress(request.getDefaultAddress());
        }

        log.info("배송지 수정 완료: 회원 ID={}, 수정된 주소={}", id, foundAddress.getAddress());
    }

    @Transactional
    public void deleteDeliveryAddress(String id, int deliveryAddressId) {
        User user = userMapper.findUserByUserId(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        DeliveryAddress foundAddress = deliveryAddressRepository.findByDeliveryAddressIdAndUser(deliveryAddressId, user)
                .orElseThrow(() -> new BusinessException(ErrorCode.ADDRESS_NOT_FOUND));

        deliveryAddressRepository.delete(foundAddress);

        log.info("배송지 삭제 완료: 회원 ID={}", id, deliveryAddressId);
    }
}
