package com.hanhwa_tae.secondserver.delivery.command.application.service;

import com.hanhwa_tae.secondserver.common.domain.DeleteType;
import com.hanhwa_tae.secondserver.delivery.command.application.dto.request.DeliveryAddressRequest;
import com.hanhwa_tae.secondserver.delivery.command.domain.aggregate.DeliveryAddress;
import com.hanhwa_tae.secondserver.delivery.command.domain.repository.DeliveryAddressRepository;
import com.hanhwa_tae.secondserver.user.command.domain.aggregate.GenderType;
import com.hanhwa_tae.secondserver.user.command.domain.aggregate.LoginType;
import com.hanhwa_tae.secondserver.user.command.domain.aggregate.User;
import com.hanhwa_tae.secondserver.user.query.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class DeliveryCommandServiceTest {

    @Mock
    private DeliveryAddressRepository deliveryAddressRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private DeliveryCommandService deliveryCommandService;

    private User testUser;
    private DeliveryAddressRequest request;
    private DeliveryAddress address;

    @BeforeEach
    void setUp() {
        testUser = User.builder()
                .userId("user101")
                .password("pass101")
                .email("user101@naver.com")
                .gender(GenderType.M)
                .loginType(LoginType.GENERAL)
                .isDeleted(DeleteType.N)
                .username("송강")
                .build();

        request = DeliveryAddressRequest.builder()
                .address("서울시 강남구")
                .receiver("차은우")
                .receiverPhone("010-1357-9753")
                .build();

        address = DeliveryAddress.builder()
                .address(request.getAddress())
                .receiver(request.getReceiver())
                .receiverPhone(request.getReceiverPhone())
                .user(testUser)
                .build();
    }

    @Test
    void registerDeliveryAddress() {
        // given
        Mockito.when(userMapper.findUserByUserId("user101"))
                .thenReturn(Optional.of(testUser));

        // when
        deliveryCommandService.registerDeliveryAddress("user101", request);

        // then
        Mockito.verify(deliveryAddressRepository, Mockito.times(1))
                .save(Mockito.any(DeliveryAddress.class));
    }

    @Test
    void updateDeliveryAddress() {
        // given
        Mockito.when(userMapper.findUserByUserId("user101")).thenReturn(Optional.of(testUser));

        Mockito.when(deliveryAddressRepository.findByUser(testUser)).thenReturn(Optional.of(address));

        // when
        deliveryCommandService.updateDeliveryAddress("user101",1, request);
//
        // then
        Assertions.assertEquals(request.getAddress(), address.getAddress());
        Assertions.assertEquals(request.getReceiver(), address.getReceiver());
        Assertions.assertEquals(request.getReceiverPhone(), address.getReceiverPhone());
    }

    @Test
    void deleteDeliveryAddress_success() {
        // given
        Mockito.when(userMapper.findUserByUserId("user101"))
                .thenReturn(Optional.of(testUser));

        Mockito.when(deliveryAddressRepository.findByUser(testUser))
                .thenReturn(Optional.of(address));

        // when
        deliveryCommandService.deleteDeliveryAddress("user101");

        // then
        Mockito.verify(deliveryAddressRepository, Mockito.times(1)).delete(address);
    }

}
