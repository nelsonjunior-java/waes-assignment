package com.waes.assignment.mock;

import com.waes.assignment.domain.model.DiffRecord;

import static com.waes.assignment.utils.DataDiffConstants.DIFF_RECORD_ID;
import static com.waes.assignment.utils.DataDiffConstants.NON_BASE64_DIFF_VALUE;
import static com.waes.assignment.utils.DataDiffConstants.DIFF_RECORD_WAITING_EVALUATION_STATUS;
import static com.waes.assignment.utils.DataDiffConstants.DIFF_RECORD_WAITING_EQUAL_STATUS;
import static com.waes.assignment.utils.DataDiffConstants.BIGGER_SIZE_NON_BASE64_DIFF_VALUE;
import static com.waes.assignment.utils.DataDiffConstants.SAME_SIZE_NOT_EQUAL_NON_BASE64_DIFF_VALUE;

/**
 * Class used to created default objects to be used as mock in the unit tests
 */
public class MockedDiffRecordResponse {

    public static final DiffRecord MOCK_1 = new DiffRecord(DIFF_RECORD_ID,NON_BASE64_DIFF_VALUE,NON_BASE64_DIFF_VALUE,DIFF_RECORD_WAITING_EVALUATION_STATUS, null);
    public static final DiffRecord MOCK_2 = new DiffRecord(DIFF_RECORD_ID,null,NON_BASE64_DIFF_VALUE,DIFF_RECORD_WAITING_EVALUATION_STATUS, null);
    public static final DiffRecord MOCK_3 = new DiffRecord(DIFF_RECORD_ID,NON_BASE64_DIFF_VALUE,null,DIFF_RECORD_WAITING_EVALUATION_STATUS, null);
    public static final DiffRecord MOCK_4 = new DiffRecord(DIFF_RECORD_ID,NON_BASE64_DIFF_VALUE,NON_BASE64_DIFF_VALUE,DIFF_RECORD_WAITING_EQUAL_STATUS, null);
    public static final DiffRecord MOCK_5 = new DiffRecord(DIFF_RECORD_ID,NON_BASE64_DIFF_VALUE,BIGGER_SIZE_NON_BASE64_DIFF_VALUE,DIFF_RECORD_WAITING_EVALUATION_STATUS, null);
    public static final DiffRecord MOCK_6 = new DiffRecord(DIFF_RECORD_ID,NON_BASE64_DIFF_VALUE,SAME_SIZE_NOT_EQUAL_NON_BASE64_DIFF_VALUE,DIFF_RECORD_WAITING_EVALUATION_STATUS, null);

}
