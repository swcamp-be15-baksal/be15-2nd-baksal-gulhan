package com.hanhwa_tae.secondserver.cart.command.domain.aggregate;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

@Entity
@Table(name = "refund")
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    private String refundReason;

    @Enumerated(EnumType.STRING)
    private RefundStatus refundStatus; // enum : '신청','접수','완료','반려'

	@CreatedDate
	private Timestamp refundedAt;
}