package com.hanhwa_tae.gulhan.cart.command.domain.aggregate;

import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table(name = "`order`")
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int OrderId;
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

    private String orderCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User userNo;

    /* 입력을 안하면 기존 값 유지 */
    public void update(String address, String receiver, String receiverPhone, @NotBlank(message = "운송장 번호입력해주세요.") String shippingNo) {
        if (address != null) this.address = address;
        if (receiver != null) this.receiver = receiver;
        if (receiverPhone != null) this.receiverPhone = receiverPhone;
        if (shippingNo != null) this.shippingNo = shippingNo;
    }
}