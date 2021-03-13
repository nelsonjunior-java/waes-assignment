package com.waes.assignment.controller.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.waes.assignment.domain.message.MessageCode.ERROR_ENCODED_BASE64_MAX_SIZE;

/**
 * This class is used like a dto for the left and right save endpoints requests
 * It uses Lombok to make the code cleaner removing unnecessary code
 */
@Data
public class DiffSaveRequest {

    public static final int BASE64_MAX_SIZE = 172;

    @NotBlank
    @Size(max = BASE64_MAX_SIZE, message = "{" + ERROR_ENCODED_BASE64_MAX_SIZE + "}")
    private String base64EncodedValue;
}
