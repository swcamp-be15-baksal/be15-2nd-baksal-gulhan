package com.hanhwa_tae.gulhan.payment.command.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/api/v1/payment")
@Controller
public class paymentController {
    @GetMapping
    public String index() {

        return "/widget/checkout";
    }
}
