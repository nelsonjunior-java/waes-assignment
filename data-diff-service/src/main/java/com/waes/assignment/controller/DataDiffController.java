package com.waes.assignment.controller;

import com.waes.assignment.service.DataDiffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/diff")
@Slf4j
public class DataDiffController {

    private final DataDiffService service;

    public DataDiffController(DataDiffService service) {
        this.service = service;
    }

    @PostMapping("/{id}/left")
    public ResponseEntity<String> saveLeftValue(@PathVariable("id") String id, @RequestBody String encodedValueLeft) {

        log.info("method=saveLeftValue, id={}, id={}", id, encodedValueLeft);

        var leftAndRightRecord = service.saveLeftValue(id, encodedValueLeft);

        return ResponseEntity.ok().build();
    }
}
