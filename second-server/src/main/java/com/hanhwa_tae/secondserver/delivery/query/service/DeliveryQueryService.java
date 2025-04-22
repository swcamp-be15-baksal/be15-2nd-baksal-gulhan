package com.hanhwa_tae.secondserver.delivery.query.service;


import com.hanhwa_tae.secondserver.common.exception.BusinessException;
import com.hanhwa_tae.secondserver.common.exception.ErrorCode;
import com.hanhwa_tae.secondserver.delivery.query.dto.request.DeliveryInfoRequest;
import com.hanhwa_tae.secondserver.user.command.application.dto.DeliveryStateCode;
import com.hanhwa_tae.secondserver.user.command.application.dto.TrackResponse;
import com.hanhwa_tae.secondserver.user.command.application.dto.response.DeliveryStateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DeliveryQueryService {
    @Value("${delivery.client.id}")
    private String clientId;

    @Value("${delivery.client.secret}")
    private String clientSecret;

    public DeliveryStateResponse getDeliveryInfo(DeliveryInfoRequest request) {
        DeliveryStateResponse deliveryStateResponse = null;

        // TODO : 현재 임시로 하드코딩 해놓음 추후 orderId에 입력된 거로 가져오기
        RestClient restClient = RestClient.builder()
                .baseUrl("https://apis.tracker.delivery/graphql")
                .build();

        String authHeader = "TRACKQL-API-KEY " + clientId + ":" + clientSecret;
        System.out.println(authHeader);

        // GraphQL 쿼리 정의
        String query = """
                query Track($carrierId: ID!, $trackingNumber: String!) {
                  track(carrierId: $carrierId, trackingNumber: $trackingNumber) {
                    lastEvent {
                      time
                      status {
                        code
                      }
                    }
                  }
                }
                """;

        // variables 구성
        Map<String, Object> variables = new HashMap<>();
        variables.put("carrierId", request.getCarrierId());
        variables.put("trackingNumber", request.getTrackingNumber());

        // 요청 body 구성
        Map<String, Object> body = new HashMap<>();
        body.put("query", query);
        body.put("variables", variables);

        try {
            TrackResponse response = restClient.post()
                    .uri("")
                    .header(HttpHeaders.AUTHORIZATION, authHeader)
                    .body(body)
                    .retrieve()
                    .body(TrackResponse.class);
            deliveryStateResponse = DeliveryStateResponse.builder()
                    .state(DeliveryStateCode.valueOf(
                                    response.getData()
                                            .getTrack()
                                            .getLastEvent()
                                            .getStatus()
                                            .getCode())
                            .getDescription())
                    .time(response.getData()
                            .getTrack()
                            .getLastEvent()
                            .getTime())
                    .build();

        } catch (RestClientResponseException e) {
            throw new BusinessException(ErrorCode.DELIVERY_RESPONSE_ERROR);
        }
        return deliveryStateResponse;
    }
}

