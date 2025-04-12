package com.hanhwa_tae.gulhan.payment.command.application.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SuccessFailController {
    @GetMapping("/widget/success")
    public String showSuccessPage() {
        return "widget/success";
    }

    @GetMapping("/widget/fail")
    public String failPayment(HttpServletRequest request, Model model) {
        model.addAttribute("code", request.getParameter("code"));
        model.addAttribute("message", request.getParameter("message"));
        return "/widget/fail";
    }
}
