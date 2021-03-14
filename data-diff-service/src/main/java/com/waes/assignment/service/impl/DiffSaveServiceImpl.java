package com.waes.assignment.service.impl;

import com.waes.assignment.domain.exception.BusinessRuleException;
import com.waes.assignment.domain.message.MessageHelper;
import com.waes.assignment.domain.model.DiffRecord;
import com.waes.assignment.infra.integration.service.client.decode.DecoderServiceClient;
import com.waes.assignment.infra.integration.service.client.decode.DecoderServiceRequest;
import com.waes.assignment.infra.repository.DataDiffRepository;
import com.waes.assignment.service.DiffSaveService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.apache.commons.codec.binary.Base64;

import static com.waes.assignment.domain.message.MessageCode.ERROR_EMPTY_ENCODED_VALUE;
import static com.waes.assignment.domain.message.MessageCode.ERROR_NOT_BASE64_ENCODED_VALUE;
import static com.waes.assignment.domain.message.MessageCode.ERROR_NULL_ID;

/**
 * Implementation of the service to save left and right values, it interfaces
 * with mongo database that saves the encoded data in the database
 * and decode service that is responsible for decoding the base64 values receivedfrom cache
 */
@Service
public class DiffSaveServiceImpl implements DiffSaveService {

    private final DataDiffRepository dataDiffRepository;
    private final MessageHelper messageHelper;
    private final DecoderServiceClient decoderServiceClient;

    public DiffSaveServiceImpl(DataDiffRepository dataDiffRepository, MessageHelper messageHelper, DecoderServiceClient decoderServiceClient) {
        this.dataDiffRepository = dataDiffRepository;
        this.messageHelper = messageHelper;
        this.decoderServiceClient = decoderServiceClient;
    }

    /**
     * This method perform some validations on the encoded rightValue
     * and then it saves or updates it in case an record for this id already exists
     * @param id diff record that will be used as the id in the database
     * @param leftValue base64 encoded left value
     * @return the response return DiffRecord which is the database saved value
     */
    @Override
    public DiffRecord saveLeftValue(Long id, String leftValue) throws BusinessRuleException {

        performValidations(id, leftValue);
        leftValue = decodeBase64Value(leftValue);

        final var diffRecordFound = dataDiffRepository.findById(id);

        if (diffRecordFound.isPresent()) {
            diffRecordFound.get().setLeftValue(leftValue);
            return dataDiffRepository.save(diffRecordFound.get());
        }
        else{
            DiffRecord diffRecord = new DiffRecord(id, leftValue, null);
            return dataDiffRepository.save(diffRecord);
        }
    }

    /**
     * This method perform some validations on the encoded rightValue
     * and then it saves or updates it in case an record for this id already exists
     * @param id diff record that will be used as the id in the database
     * @param rightValue base64 encoded right value
     * @return the response return DiffRecord which is the database saved value
     */
    @Override
    public DiffRecord saveRightValue(Long id, String rightValue) throws BusinessRuleException {

        performValidations(id, rightValue);
        rightValue = decodeBase64Value(rightValue);

        final var diffRecordFound = dataDiffRepository.findById(id);

        if (diffRecordFound.isPresent()) {
            diffRecordFound.get().setRightValue(rightValue);
            return dataDiffRepository.save(diffRecordFound.get());
        }
        else{
            DiffRecord diffRecord = new DiffRecord(id, rightValue, null);
            return dataDiffRepository.save(diffRecord);
        }
    }

    /**
     * This method invokes the external service decoder-service
     * in order to decode the encoded Base64 value it received and returns a decoded String
     * @param encodedValue base64 encoded to be decoded
     * @return the response return an String with a decoded base64 value
     */
    private String decodeBase64Value(String encodedValue){

        //Decodes value before saving because base64 adds an average of 33% in size
        DecoderServiceRequest decoderServiceRequest = new DecoderServiceRequest(encodedValue);
        return decoderServiceClient.decode(decoderServiceRequest).getDecodeValue();
    }

    /**
     * Check if id is not null and validated if the encodedValue is not
     * null or empty and also check if it is base64 value
     * @param id provided by upstream system or user
     * @param encodedValue encoded provided by upstream system or user
     */
    private void performValidations(Long id, String encodedValue) throws BusinessRuleException {

        if(id == null){
            throw new BusinessRuleException(messageHelper.get(ERROR_NULL_ID));
        }

        if(StringUtils.isEmpty(encodedValue)){
            throw new BusinessRuleException(messageHelper.get(ERROR_EMPTY_ENCODED_VALUE));
        }

        if (!Base64.isBase64(encodedValue.getBytes())){
            throw new BusinessRuleException(messageHelper.get(ERROR_NOT_BASE64_ENCODED_VALUE));
        }
    }

}
