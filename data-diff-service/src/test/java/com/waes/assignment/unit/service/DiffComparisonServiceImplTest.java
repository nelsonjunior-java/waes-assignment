package com.waes.assignment.unit.service;

import com.waes.assignment.domain.comparator.ValueDifferenceComparator;
import com.waes.assignment.domain.exception.BusinessRuleException;
import com.waes.assignment.domain.message.MessageHelper;
import com.waes.assignment.domain.model.DiffRecord;
import com.waes.assignment.domain.model.Difference;
import com.waes.assignment.infra.repository.DataDiffRepository;
import com.waes.assignment.mock.MockedDiffRecordResponse;
import com.waes.assignment.service.impl.DiffComparisonServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.isA;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static com.waes.assignment.utils.DataDiffConstants.DIFF_RECORD_ID;
import static com.waes.assignment.utils.DataDiffConstants.INVALID_DIFF_RECORD_ID;
import static com.waes.assignment.utils.DataDiffConstants.DIFF_RECORD_WAITING_EQUAL_STATUS;
import static com.waes.assignment.utils.DataDiffConstants.DIFF_RECORD_DIFFERENT_SIZE_STATUS;
import static com.waes.assignment.utils.DataDiffConstants.DIFF_RECORD_SAME_SIZE_WITH_DIFFERENCES_STATUS;

/**
 * This class unit tests the service DiffComparisonServiceImpl
 */
@ExtendWith(MockitoExtension.class)
public class DiffComparisonServiceImplTest {

    @InjectMocks
    private DiffComparisonServiceImpl diffComparisonService;

    @Mock
    private MessageHelper messageHelper;
    @Mock
    private DataDiffRepository dataDiffRepository;

    @Mock
    private ValueDifferenceComparator<List<Difference>, String> valueDifferenceComparator;

    @Test
    @DisplayName("Should throw a BusinessRuleException when trying to evaluate the differences with a null Id")
    void ShouldThrowABusinessRuleExceptionWhenTryingToEvaluateTheDifferencesWithANullId() {

        assertThrows(BusinessRuleException.class, () ->
                diffComparisonService.getDifferenceBetweenValues(INVALID_DIFF_RECORD_ID)
        );

    }

    @Test
    @DisplayName("Should throw a BusinessRuleException when a left value was not saved before comparing")
    void ShouldThrowABusinessRuleExceptionWhenALeftValueWasNotSavedBeforeComparing() {

        when(dataDiffRepository.findById(DIFF_RECORD_ID)).thenReturn(Optional.of(MockedDiffRecordResponse.MOCK_2));

        assertThrows(BusinessRuleException.class, () ->
                diffComparisonService.getDifferenceBetweenValues(DIFF_RECORD_ID)
        );
    }

    @Test
    @DisplayName("Should throw a BusinessRuleException when a right value was not saved before comparing")
    void ShouldThrowABusinessRuleExceptionWhenARightValueWasNotSavedBeforeComparing() {

        when(dataDiffRepository.findById(DIFF_RECORD_ID)).thenReturn(Optional.of(MockedDiffRecordResponse.MOCK_3));

        assertThrows(BusinessRuleException.class, () ->
                diffComparisonService.getDifferenceBetweenValues(DIFF_RECORD_ID)
        );
    }

    @Test
    @DisplayName("Should return a Diff record without evaluating the differences when the Diff record saved has status different from WAITING_EVALUATION")
    void ShouldNotEvaluateDifferencesWhenDiffRecordStatusIsDifferentFromWaitingEvaluation() throws BusinessRuleException {

        when(dataDiffRepository.findById(DIFF_RECORD_ID)).thenReturn(Optional.of(MockedDiffRecordResponse.MOCK_4));

        var diffRecord = diffComparisonService.getDifferenceBetweenValues(DIFF_RECORD_ID);

        assertThat(diffRecord, isA(DiffRecord.class));
        Assertions.assertThat(diffRecord.getLeftValue()).isNotBlank();
        Assertions.assertThat(diffRecord.getRightValue()).isNotBlank();
        Assertions.assertThat(diffRecord.getDiffRecordStatus()).isEqualTo(DIFF_RECORD_WAITING_EQUAL_STATUS);
    }

    @Test
    @DisplayName("Should return a Diff record with EQUAL status when left and right values are equal and current status is WAITING_EVALUATION")
    void ShouldReturnADiffRecordWithEQUALStatusWhenValuesAreEqualAndCurrentStatusIsWaitingEvaluation() throws BusinessRuleException {

        when(dataDiffRepository.findById(DIFF_RECORD_ID)).thenReturn(Optional.of(MockedDiffRecordResponse.MOCK_1));

        var diffRecord = diffComparisonService.getDifferenceBetweenValues(DIFF_RECORD_ID);

        assertThat(diffRecord, isA(DiffRecord.class));
        Assertions.assertThat(diffRecord.getLeftValue()).isNotBlank();
        Assertions.assertThat(diffRecord.getRightValue()).isNotBlank();
        Assertions.assertThat(diffRecord.getDiffRecordStatus()).isEqualTo(DIFF_RECORD_WAITING_EQUAL_STATUS);
    }

    @Test
    @DisplayName("Should return a Diff record with DIFFERENT_SIZE status when left and right values have different size and current status is WAITING_EVALUATION")
    void ShouldReturnARecordWithDifferentSIzeStatusWhenValuesHaveDifferentSizeAndCurrentStatusIsWaitingEvaluation() throws BusinessRuleException {

        when(dataDiffRepository.findById(DIFF_RECORD_ID)).thenReturn(Optional.of(MockedDiffRecordResponse.MOCK_5));

        var diffRecord = diffComparisonService.getDifferenceBetweenValues(DIFF_RECORD_ID);

        assertThat(diffRecord, isA(DiffRecord.class));
        Assertions.assertThat(diffRecord.getLeftValue()).isNotBlank();
        Assertions.assertThat(diffRecord.getRightValue()).isNotBlank();
        Assertions.assertThat(diffRecord.getDiffRecordStatus()).isEqualTo(DIFF_RECORD_DIFFERENT_SIZE_STATUS);

    }

    @Test
    @DisplayName("Should return a Diff record with SAME_SIZE_WITH_DIFFERENCES status when left and right values have the same size but not equal")
    void ShouldReturnADiffRecordWithSameSizeWithDifferencesStatusWhenLeftAndRightValuesHaveTheSameSizeButNotEqual() throws BusinessRuleException {

        when(dataDiffRepository.findById(DIFF_RECORD_ID)).thenReturn(Optional.of(MockedDiffRecordResponse.MOCK_6));

        var diffRecord = diffComparisonService.getDifferenceBetweenValues(DIFF_RECORD_ID);

        assertThat(diffRecord, isA(DiffRecord.class));
        Assertions.assertThat(diffRecord.getLeftValue()).isNotBlank();
        Assertions.assertThat(diffRecord.getRightValue()).isNotBlank();
        Assertions.assertThat(diffRecord.getDiffRecordStatus()).isEqualTo(DIFF_RECORD_SAME_SIZE_WITH_DIFFERENCES_STATUS);
    }
}
