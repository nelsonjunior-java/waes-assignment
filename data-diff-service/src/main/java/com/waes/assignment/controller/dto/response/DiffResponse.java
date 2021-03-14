package com.waes.assignment.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.waes.assignment.domain.model.DiffRecord;
import com.waes.assignment.domain.model.Difference;
import lombok.Data;

import java.util.List;

/**
 * This class creates and independent response to the application layer
 * It uses Lombok to make the code cleaner removing unnecessary code
 */
@Data
public class DiffResponse {

    private DiffResponseStatus diffResponseStatus;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Difference> diferences;

    public static DiffResponse from(DiffRecord diffRecord) {

        final DiffResponse diffResponse = new DiffResponse();
        diffResponse.setDiffResponseStatus(DiffResponseStatus.valueOf(diffRecord.getDiffRecordStatus().name()));
        diffResponse.setDiferences(diffRecord.getDifferences());

        return diffResponse;
    }
}
