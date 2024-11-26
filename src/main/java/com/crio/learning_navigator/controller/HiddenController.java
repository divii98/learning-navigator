package com.crio.learning_navigator.controller;

import com.crio.learning_navigator.constant.Constant;
import com.crio.learning_navigator.exchange.HiddenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController

public class HiddenController {
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/hidden-feature/{number}")
    public HiddenResponse hiddenFeatureFact(@PathVariable String number) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(String.format(Constant.NUMBER_API_URL, number), String.class);
        return new HiddenResponse(number,response.getBody());

    }
}
