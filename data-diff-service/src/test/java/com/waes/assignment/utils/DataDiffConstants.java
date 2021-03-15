package com.waes.assignment.utils;

import com.waes.assignment.domain.model.DiffRecordStatus;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DataDiffConstants {
    public static final String ENCODED_BASE64_VALUE = "Q2xlYW4gQ29kZSE=";
    public static final Long DIFF_RECORD_ID = 1L;
    public static final Long INVALID_DIFF_RECORD_ID = null;
    public static final String EMPTY_DIFF_VALUE = "";
    public static final String ONLY_WHITE_SPACE_DIFF_VALUE = "";
    public static final String NON_BASE64_DIFF_VALUE = "{name: \"Johan Cruyff\", age: 31, city: \"Eindhoven\"}";
    public static final String SAME_SIZE_NOT_EQUAL_NON_BASE64_DIFF_VALUE = "{name: \"Johan Cruyff\", age: 25, city: \"Eindhoven\"}";
    public static final String BIGGER_SIZE_NON_BASE64_DIFF_VALUE = "{name: \"Ruud Van Nistelrooy\", age: 31, city: \"Eindhoven\"}";
    public static final DiffRecordStatus DIFF_RECORD_WAITING_EVALUATION_STATUS = DiffRecordStatus.WAITING_EVALUATION;
    public static final DiffRecordStatus DIFF_RECORD_WAITING_EQUAL_STATUS = DiffRecordStatus.EQUAL;
    public static final DiffRecordStatus DIFF_RECORD_DIFFERENT_SIZE_STATUS = DiffRecordStatus.DIFFERENT_SIZE;
    public static final DiffRecordStatus DIFF_RECORD_SAME_SIZE_WITH_DIFFERENCES_STATUS = DiffRecordStatus.SAME_SIZE_WITH_DIFFERENCES;
}
