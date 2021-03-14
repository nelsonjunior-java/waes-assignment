package com.waes.assignment.controller;

import com.waes.assignment.controller.dto.request.DiffSaveRequest;
import com.waes.assignment.controller.dto.response.DiffResponse;
import com.waes.assignment.domain.exception.BusinessRuleException;
import com.waes.assignment.service.DiffComparisonService;
import com.waes.assignment.service.DiffSaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * REST endpoints for saving the decoded value of an endoded64 data
 * in a left and right endpoint, each representing a different value to be compared in another endpoint
 */
@RestController
@RequestMapping("/v1/diff")
@Slf4j
public class DiffController {

    private final DiffSaveService diffSaveService;
    private final DiffComparisonService diffComparisonService;

    public DiffController(DiffSaveService diffSaveService, DiffComparisonService diffComparisonService) {
        this.diffSaveService = diffSaveService;
        this.diffComparisonService = diffComparisonService;
    }

    @PostMapping("/{id}/left")
    public ResponseEntity<String> saveLeftValue(@PathVariable("id") Long id, @Valid @RequestBody DiffSaveRequest encodedLeftValue) throws BusinessRuleException {

        log.info("method=saveLeftValue, id={}, id={}", id, encodedLeftValue);

        var leftAndRightRecord = diffSaveService.saveLeftValue(id, encodedLeftValue.getBase64EncodedValue());

        return ResponseEntity.ok().build(); //##########################################
    }

    @PostMapping("/{id}/right")
    public ResponseEntity<String> saveRightValue(@PathVariable("id") Long id,@Valid @RequestBody DiffSaveRequest encodedRightValue) throws BusinessRuleException {

        log.info("method=saveRightValue, id={}, id={}", id, encodedRightValue);

        var diffRecord = diffSaveService.saveRightValue(id, encodedRightValue.getBase64EncodedValue());

        //##########################################
        //##########################################
        return ResponseEntity.ok().build(); //##########################################
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiffResponse> getValuesDifference(@PathVariable("id") Long id) throws BusinessRuleException {

        var diffRecord = diffComparisonService.getDifferenceBetweenValues(id);

        return ResponseEntity.ok(DiffResponse.from(diffRecord));
    }
}
