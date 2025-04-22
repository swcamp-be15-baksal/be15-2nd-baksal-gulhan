package com.hanhwa_tae.secondserver.delivery.query.service;

import com.hanhwa_tae.secondserver.delivery.query.dto.request.DeliveryInfoRequest;
import com.hanhwa_tae.secondserver.user.command.application.dto.response.DeliveryStateResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("local")
class DeliveryQueryServiceTest {

    @Autowired
    DeliveryQueryService deliveryQueryService;


    @Test
    @DisplayName("운송장 조회 테스트")
    void testAccessToken(){
        DeliveryInfoRequest request = DeliveryInfoRequest.builder()
                .carrierId("kr.lotte")
                .trackingNumber("254933068353")
                .build();

        DeliveryStateResponse response = deliveryQueryService.getDeliveryInfo(request);
        Assertions.assertNotNull(response.getState());
        Assertions.assertNotNull(response.getTime());
    }

}