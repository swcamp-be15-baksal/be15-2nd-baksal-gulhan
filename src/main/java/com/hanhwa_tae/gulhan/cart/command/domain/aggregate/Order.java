package com.hanhwa_tae.gulhan.cart.command.domain.aggregate;

import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table(name = "`order`")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    private String orderId;

    @CreatedDate
    private Date orderedAt;

    private int totalPrice;

    private int totalPoint;

    private int totalAmount;

    @Column(unique = true)
    private String shippingNo;

    private String address;

    private String receiver;

    private String receiverPhone;

    /*payment code*/
    private String orderCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User userNo;

    @Enumerated(EnumType.STRING)
    private Confirm isConfirmed = Confirm.N; // enum : Y, N


    @Builder
    public Order(String orderId, Date orderedAt, int totalPrice,
                 int totalPoint, int totalAmount,
                 String shippingNo, String address,
                 String receiver, String receiverPhone,
                 String orderCode, User user,
                 Confirm isConfirmed
                 ){
        this.orderId = orderId;
        this.orderedAt = orderedAt;
        this.totalPrice = totalPrice;
        this.totalPoint = totalPoint;
        this.totalAmount = totalAmount;
        this.shippingNo = shippingNo;
        this.address = address;
        this.receiver = receiver;
        this.receiverPhone = receiverPhone;
        this.orderCode = orderCode;
        this.userNo = user;
        this.isConfirmed = isConfirmed;
    }

    /* 입력을 안하면 기존 값 유지 */
    public void update(String address, String orderCode, String receiver, String receiverPhone, @NotBlank(message = "운송장 번호입력해주세요.") String shippingNo) {
        if (address != null) this.address = address;
        if (orderCode != null) this.orderCode = orderCode;
        if (receiver != null) this.receiver = receiver;
        if (receiverPhone != null) this.receiverPhone = receiverPhone;
        if (shippingNo != null) this.shippingNo = shippingNo;
    }
}