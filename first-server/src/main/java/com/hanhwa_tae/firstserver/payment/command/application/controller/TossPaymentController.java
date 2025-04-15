package com.hanhwa_tae.firstserver.payment.command.application.controller;

import com.hanhwa_tae.firstserver.security.model.CustomUserDetail;
import com.hanhwa_tae.firstserver.cart.command.application.service.OrderCommandService;
import com.hanhwa_tae.firstserver.config.TossPaymentConfig;
import com.hanhwa_tae.firstserver.payment.command.application.dto.request.CancelRequestDto;
import com.hanhwa_tae.firstserver.payment.command.application.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RequestMapping("/payment")
@Controller
@RequiredArgsConstructor
public class TossPaymentController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PaymentService paymentService;
    private final OrderCommandService orderCommandService;
    private final TossPaymentConfig tossPaymentConfig;
    @PostMapping(value = {"/confirm/widget"})
    public ResponseEntity<JSONObject> confirmPayment(@RequestBody String jsonBody) throws Exception {
        boolean is_paid = paymentService.validatePayment((String)parseRequestData(jsonBody).get("orderId"),
                Integer.parseInt((String) parseRequestData(jsonBody).get("amount")));

        JSONObject response = sendRequest(parseRequestData(jsonBody), tossPaymentConfig.getTestSecretApiKey(), TossPaymentConfig.URL + "/confirm");
        if(!is_paid) {
            response.put("payerror","잘못된 결제요청입니다.");
        }
        int statusCode = (response.containsKey("error") || response.containsKey("payerror"))
                ? 400
                : 200;

        return ResponseEntity.status(statusCode).body(response);
    }

    @PostMapping(value = {"/cancel"})
    public ResponseEntity<JSONObject> cancelPayment(@AuthenticationPrincipal CustomUserDetail userDetail, @RequestBody CancelRequestDto cancelRequestDto){
        String paymentKey = orderCommandService.searchPaymentKeyByUserNo(userDetail.getUserNo()).getOrderCode();
        String secretKey = tossPaymentConfig.getTestSecretApiKey();
        String cancelReasonContent = "{ \"cancelReason\": \"" + cancelRequestDto.getCancelReason() + "\" }";
        JSONObject response = sendRequest(parseRequestData(cancelReasonContent),
                secretKey, TossPaymentConfig.URL + "/" + paymentKey + "/cancel");

        int statusCode = (response.containsKey("error"))
                ?400
                :200;

        return ResponseEntity.status(statusCode).body(response);
    }


    private JSONObject parseRequestData(String jsonBody) {
        try {
            return (JSONObject) new JSONParser().parse(jsonBody);
        } catch (ParseException e) {
            logger.error("JSON Parsing Error", e);
            return new JSONObject();
        }
    }

    private JSONObject sendRequest(JSONObject requestData, String secretKey, String urlString){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String encodeString = Base64.getEncoder().encodeToString((secretKey + ":").getBytes(StandardCharsets.UTF_8));
        httpHeaders.set("Authorization", "Basic " + encodeString);

        // 요청 엔티티 생성
        HttpEntity<String> entity = new HttpEntity<>(requestData.toJSONString(), httpHeaders);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    urlString,
                    HttpMethod.POST,
                    entity,
                    String.class
            );
            return parseRequestData(response.getBody());
    }catch(Exception e) {
            JSONObject error = new JSONObject();
            error.put("error", "Error sending request or parsing response");
            return error;
        }
    }

    @GetMapping()
    public String index() {
        return "/widget/checkout";
    }



}
