package com.waes.assignment.unit.service;

import com.waes.assignment.domain.exception.BusinessRuleException;
import com.waes.assignment.domain.message.MessageHelper;
import com.waes.assignment.domain.model.DiffRecord;
import com.waes.assignment.infra.integration.service.client.decode.DecoderServiceClient;
import com.waes.assignment.infra.repository.DataDiffRepository;
import com.waes.assignment.service.impl.DiffSaveServiceImpl;
import com.waes.assignment.mock.MockedDecoderServiceRequest;
import com.waes.assignment.mock.MockedDecoderServiceResponse;
import com.waes.assignment.mock.MockedDiffRecordResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.waes.assignment.utils.DataDiffConstants.ENCODED_BASE64_VALUE;
import static com.waes.assignment.utils.DataDiffConstants.DIFF_RECORD_ID;
import static com.waes.assignment.utils.DataDiffConstants.EMPTY_DIFF_VALUE;
import static com.waes.assignment.utils.DataDiffConstants.ONLY_WHITE_SPACE_DIFF_VALUE;
import static com.waes.assignment.utils.DataDiffConstants.NON_BASE64_DIFF_VALUE;
import static com.waes.assignment.utils.DataDiffConstants.INVALID_DIFF_RECORD_ID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.isA;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * This class unit tests the service DiffSaveServiceImpl
 */
@ExtendWith(MockitoExtension.class)
public class DiffSaveServiceImplTest {

    @InjectMocks
    private DiffSaveServiceImpl diffSaveService;

    @Mock
    private MessageHelper messageHelper;
    @Mock
    private DecoderServiceClient decoderServiceClient;
    @Mock
    private DataDiffRepository dataDiffRepository;

    @Test
    @DisplayName("Should throw a BusinessRuleException when trying to save an encoded leftValue value with a null Id")
    void shouldThrowAnBusinessRuleExceptionWhenTryingToSaveLeftValueValueWithANullId() {

        assertThrows(BusinessRuleException.class, () ->
                diffSaveService.saveLeftValue(INVALID_DIFF_RECORD_ID, ENCODED_BASE64_VALUE)
        );
    }

    @Test
    @DisplayName("Should throw a BusinessRuleException when trying to save an empty leftValue")
    void ShouldThrowAnBusinessRuleExceptionWhenTryingToSaveAnEmptyLeftValue() {
        assertThrows(BusinessRuleException.class, () ->
                diffSaveService.saveLeftValue(DIFF_RECORD_ID, EMPTY_DIFF_VALUE)
        );
    }

    @Test
    @DisplayName("Should throw a BusinessRuleException when trying to save a leftValue with only blank spaces")
    void ShouldThrowAnBusinessRuleExceptionWhenTryingToSaveALeftValueWithOnlyBlankSpaces() {
        assertThrows(BusinessRuleException.class, () ->
                diffSaveService.saveLeftValue(DIFF_RECORD_ID, ONLY_WHITE_SPACE_DIFF_VALUE)
        );
    }

    @Test
    @DisplayName("Should throw a BusinessRuleException when trying to save a leftValue value with not in the base64 format")
    void ShouldThrowABusinessRuleExceptionWhenTryingToSaveALeftValueValueWithNotInTheBase64Format() {
        assertThrows(BusinessRuleException.class, () ->
                diffSaveService.saveLeftValue(DIFF_RECORD_ID, NON_BASE64_DIFF_VALUE)
        );
    }

    @Test
    @DisplayName("Should update the LeftValue and return the updated DiffRecord when a it already exists")
    void ShouldUpdateLeftValueAndReturnUpdatedDiffRecordWhenItAlreadyExists() throws BusinessRuleException {

        when(decoderServiceClient.decode(MockedDecoderServiceRequest.MOCK_1)).thenReturn(MockedDecoderServiceResponse.MOCK_1);
        when(dataDiffRepository.findById(DIFF_RECORD_ID)).thenReturn(Optional.of(MockedDiffRecordResponse.MOCK_1));

        when(dataDiffRepository.save(any(DiffRecord.class))).thenReturn(MockedDiffRecordResponse.MOCK_1);

        var diffRecord = diffSaveService.saveLeftValue(DIFF_RECORD_ID, ENCODED_BASE64_VALUE);

        assertThat(diffRecord, isA(DiffRecord.class));
        Assertions.assertThat(diffRecord.getLeftValue()).isNotBlank();
        Assertions.assertThat(diffRecord.getRightValue()).isNotBlank();
    }

    @Test
    @DisplayName("Should create a new DiffRecord with a leftValue when it does not exist")
    void ShouldCreateANewDiffRecordWithALeftValueWhenItDoesNotExist() throws BusinessRuleException {

        when(decoderServiceClient.decode(MockedDecoderServiceRequest.MOCK_1)).thenReturn(MockedDecoderServiceResponse.MOCK_1);
        when(dataDiffRepository.findById(DIFF_RECORD_ID)).thenReturn(Optional.empty());

        when(dataDiffRepository.save(any(DiffRecord.class))).thenReturn(MockedDiffRecordResponse.MOCK_1);

        DiffRecord diffRecord = diffSaveService.saveLeftValue(DIFF_RECORD_ID, ENCODED_BASE64_VALUE);

        assertThat(diffRecord, isA(DiffRecord.class));
        Assertions.assertThat(diffRecord.getLeftValue()).isNotBlank();
        Assertions.assertThat(diffRecord.getRightValue()).isNotBlank();
    }

    @Test
    @DisplayName("Should throw a BusinessRuleException when trying to save an encoded rightValue value with a null Id")
    void shouldThrowAnBusinessRuleExceptionWhenTryingToSaveARightValueValueWithANullId() {

        assertThrows(BusinessRuleException.class, () ->
                diffSaveService.saveRightValue(null, ENCODED_BASE64_VALUE)
        );
    }

    @Test
    @DisplayName("Should throw a BusinessRuleException when trying to save an empty rightValue")
    void ShouldThrowAnBusinessRuleExceptionWhenTryingToSaveAnEmptyRightValue() {
        assertThrows(BusinessRuleException.class, () ->
                diffSaveService.saveRightValue(DIFF_RECORD_ID, EMPTY_DIFF_VALUE)
        );
    }

    @Test
    @DisplayName("Should throw a BusinessRuleException when trying to save a rightValue with only blank spaces")
    void ShouldThrowAnBusinessRuleExceptionWhenTryingToSaveARightValueWithOnlyBlankSpaces() {
        assertThrows(BusinessRuleException.class, () ->
                diffSaveService.saveRightValue(DIFF_RECORD_ID, ONLY_WHITE_SPACE_DIFF_VALUE)
        );
    }

    @Test
    @DisplayName("Should throw a BusinessRuleException when trying to save a rightValue value with not in the base64 format")
    void ShouldThrowABusinessRuleExceptionWhenTryingToSaveARightValueValueWithNotInTheBase64Format() {
        assertThrows(BusinessRuleException.class, () ->
                diffSaveService.saveRightValue(DIFF_RECORD_ID, NON_BASE64_DIFF_VALUE)
        );
    }

    @Test
    @DisplayName("Should update the RightValue and return the updated DiffRecord when it already exists")
    void ShouldUpdateRightValueAndReturnUpdatedDiffRecordWhenItAlreadyExists() throws BusinessRuleException {

        when(decoderServiceClient.decode(MockedDecoderServiceRequest.MOCK_1)).thenReturn(MockedDecoderServiceResponse.MOCK_1);
        when(dataDiffRepository.findById(DIFF_RECORD_ID)).thenReturn(Optional.of(MockedDiffRecordResponse.MOCK_1));

        when(dataDiffRepository.save(any(DiffRecord.class))).thenReturn(MockedDiffRecordResponse.MOCK_1);

        DiffRecord diffRecord = diffSaveService.saveRightValue(DIFF_RECORD_ID, ENCODED_BASE64_VALUE);

        assertThat(diffRecord, isA(DiffRecord.class));
        Assertions.assertThat(diffRecord.getLeftValue()).isNotBlank();
        Assertions.assertThat(diffRecord.getRightValue()).isNotBlank();
    }

    @Test
    @DisplayName("Should create a new DiffRecord with a rightValue when it does not exist")
    void ShouldCreateANewDiffRecordWithARightValueWhenItDoesNotExist() throws BusinessRuleException {

        when(decoderServiceClient.decode(MockedDecoderServiceRequest.MOCK_1)).thenReturn(MockedDecoderServiceResponse.MOCK_1);
        when(dataDiffRepository.findById(DIFF_RECORD_ID)).thenReturn(Optional.empty());

        when(dataDiffRepository.save(any(DiffRecord.class))).thenReturn(MockedDiffRecordResponse.MOCK_1);

        DiffRecord diffRecord = diffSaveService.saveRightValue(DIFF_RECORD_ID, ENCODED_BASE64_VALUE);

        assertThat(diffRecord, isA(DiffRecord.class));
        Assertions.assertThat(diffRecord.getLeftValue()).isNotBlank();
        Assertions.assertThat(diffRecord.getRightValue()).isNotBlank();
    }
}
