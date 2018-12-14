package com.nzt.paymentService.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import org.springframework.web.client.RestTemplate;


@RestController
public class PaymentServiceController {

    @PostMapping("/create")
    public String createCharge(@RequestBody Charge charge) throws Exception{

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        Stripe.apiKey = "sk_test_4eC39HqLyjWDarjtT1zdp7dc";

        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", charge.getAmount());
        chargeParams.put("currency", charge.getCurrency());
        chargeParams.put("description", charge.getDescription());
        chargeParams.put("source", charge.getSource());
        HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(chargeParams, headers);

        final String uri = "https://api.stripe.com/v1/charges";
        RestTemplate restTemplate = new RestTemplate();
        Charge.create(chargeParams);
        ResponseEntity<String> response = restTemplate.postForEntity( uri, request , String.class );
        return "success";

    }
}
