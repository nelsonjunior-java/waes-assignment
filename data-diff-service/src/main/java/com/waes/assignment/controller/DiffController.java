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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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
    public ResponseEntity<DiffResponse> saveLeftValue(@PathVariable("id") Long id, @Valid @RequestBody DiffSaveRequest encodedLeftValue) throws BusinessRuleException {

        log.info("method=saveLeftValue, id={}, encodedLeftValue={}", id, encodedLeftValue);

        var diffRecord = diffSaveService.saveLeftValue(id, encodedLeftValue.getBase64EncodedValue());

        final URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .replacePath("/v1/diff/{id}")
                .buildAndExpand(diffRecord.getId())
                .toUri();

        return ResponseEntity.created(location).body(DiffResponse.from(diffRecord));
    }

    @PostMapping("/{id}/right")
    public ResponseEntity<DiffResponse> saveRightValue(@PathVariable("id") Long id,@Valid @RequestBody DiffSaveRequest encodedRightValue) throws BusinessRuleException {

        log.info("method=saveRightValue, id={}, encodedLeftValue={}", id, encodedRightValue);

        var diffRecord = diffSaveService.saveRightValue(id, encodedRightValue.getBase64EncodedValue());

        final URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .replacePath("/v1/diff/{id}")
                .buildAndExpand(diffRecord.getId())
                .toUri();

        return ResponseEntity.created(location).body(DiffResponse.from(diffRecord));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiffResponse> getValuesDifference(@PathVariable("id") Long id) throws BusinessRuleException {

        var diffRecord = diffComparisonService.getDifferenceBetweenValues(id);

        return ResponseEntity.ok(DiffResponse.from(diffRecord));
    }
}
