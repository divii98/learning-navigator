package com.crio.learning_navigator.controller;

import com.crio.learning_navigator.constant.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@ResponseStatus(HttpStatus.OK)
public class HiddenController {

    @GetMapping("/hidden-feature/{number}")
    public ResponseEntity<String> hiddenFeatureFact(@PathVariable String number){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(String.format(Constant.NUMBER_API_URL,number),String.class);
    }
}
