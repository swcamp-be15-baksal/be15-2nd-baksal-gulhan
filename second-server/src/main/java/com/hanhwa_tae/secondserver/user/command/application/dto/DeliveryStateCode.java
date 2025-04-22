package com.hanhwa_tae.secondserver.user.command.application.dto;

public enum DeliveryStateCode {
    UNKNOWN("배송 상태를 알 수 없음"),
    INFORMATION_RECEIVED("배송 정보 수신됨"),
    AT_PICKUP("집화처에 있음"),
    IN_TRANSIT("배송 중"),
    OUT_FOR_DELIVERY("배송 출발"),
    ATTEMPT_FAIL("배송 실패"),
    DELIVERED("배송 완료"),
    AVAILABLE_FOR_PICKUP("수령 대기 중"),
    EXCEPTION("배송 중 문제 발생");

    private final String description;

    DeliveryStateCode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
