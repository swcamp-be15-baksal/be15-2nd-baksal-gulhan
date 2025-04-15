package com.hanhwa_tae.firstserver.cart.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

@Entity
@Table(name = "refund")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String orderId;

    private String refundReason;

    @Enumerated(EnumType.STRING)
    private RefundStatus refundStatus; // enum : '신청','접수','완료','반려'

	@CreatedDate
	private Timestamp refundedAt;

    @Builder
    public Refund(String orderId, String refundReason, RefundStatus refundStatus, Timestamp refundedAt){
        this.orderId = orderId;
        this.refundReason = refundReason;
        this.refundStatus = refundStatus;
        this.refundedAt = refundedAt;
    }
}