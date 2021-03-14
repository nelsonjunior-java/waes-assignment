package com.waes.assignment.domain.message;

/**
 * This class keeps all the errors message codes used in the application
 */
public final class MessageCode {

    public static final String ERROR_EMPTY_ENCODED_VALUE = "error.empty.encoded_value";
    public static final String ERROR_NOT_BASE64_ENCODED_VALUE = "error.value_not_base64";
    public static final String ERROR_NULL_ID = "error.null.id";
    public static final String ERROR_ENCODED_BASE64_MAX_SIZE = "error.encoded.base64.max_size";
    public static final String ERROR_DIFF_RECORD_NOT_FOUND = "error.diff.record.not_found";
    public static final String ERROR_MISSING_LEFT_VALUE = "error.missing.left.value";
    public static final String ERROR_MISSING_RIGHT_VALUE = "error.missing.right.value";



    private String key;

    MessageCode(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
