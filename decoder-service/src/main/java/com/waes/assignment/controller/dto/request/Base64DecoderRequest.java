package com.waes.assignment.controller.dto.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

import static com.waes.assignment.domain.message.MessageCode.ERROR_ENCODED_BASE64_MAX_SIZE;

@Data
public class Base64DecoderRequest {

    public static final int BASE64_MAX_SIZE = 172;

    @NotBlank
    @Size(max = BASE64_MAX_SIZE, message = "{" + ERROR_ENCODED_BASE64_MAX_SIZE + "}")
    private String base64EncodedValue;

}
