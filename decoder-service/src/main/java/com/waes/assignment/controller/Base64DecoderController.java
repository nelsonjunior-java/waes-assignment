package com.waes.assignment.controller;

import com.waes.assignment.controller.dto.request.Base64DecoderRequest;
import com.waes.assignment.controller.dto.response.Base64DecoderResponse;
import com.waes.assignment.service.Base64DecoderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/decoder/base64")
@Slf4j
public class Base64DecoderController {

    private final Base64DecoderService service;

    public Base64DecoderController(Base64DecoderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Base64DecoderResponse> decode(@Valid @RequestBody Base64DecoderRequest encodedValue) {

        log.info("method=decode, id={}, encodedValue={}", encodedValue);

        String decodeValue = service.decode(encodedValue.getBase64EncodedValue());

        return ResponseEntity.ok(Base64DecoderResponse.from(decodeValue));
    }
}
