package com.lucas.producer.controllers;

import com.lucas.producer.dtos.UserRequestDTO;
import com.lucas.producer.producers.TestProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
@Validated
@RequiredArgsConstructor
public class TestController {

    private final TestProducer testProducer;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> sendTest(@RequestBody final UserRequestDTO userRequestDTO) {

        testProducer.sendToTestQueue(userRequestDTO);

        return ResponseEntity.ok().body("success" + userRequestDTO);
    }

}
