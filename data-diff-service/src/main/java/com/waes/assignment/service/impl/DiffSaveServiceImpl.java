package com.waes.assignment.service.impl;

import com.waes.assignment.domain.exception.BusinessRuleException;
import com.waes.assignment.domain.message.MessageHelper;
import com.waes.assignment.domain.model.DiffRecord;
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

    public DiffSaveServiceImpl(DataDiffRepository dataDiffRepository, MessageHelper messageHelper) {
        this.dataDiffRepository = dataDiffRepository;
        this.messageHelper = messageHelper;
    }

    /**
     * Decodes base64 left value and create or update diff record on the database
     * @param id diff record that will be used as the id in the database
     * @param encodedLeftValue base64 encoded left value
     * @return the response return DiffRecord which is the database saved value
     */
    @Override
    public DiffRecord saveLeftValue(Long id, String encodedLeftValue) throws BusinessRuleException {

        performBusinessValidations(id, encodedLeftValue);

        //Decode before save

        final var diffRecordFound = dataDiffRepository.findById(id);

        if (diffRecordFound.isPresent()) {
            diffRecordFound.get().setLeftValue(encodedLeftValue);
            return dataDiffRepository.save(diffRecordFound.get());
        }
        else{
            DiffRecord diffRecord = new DiffRecord(id, encodedLeftValue, null);
            return dataDiffRepository.save(diffRecord);
        }
    }

    /**
     * Decodes base64 left value and create or update diff record on the database
     * @param id diff record that will be used as the id in the database
     * @param encodedRightValue base64 encoded right value
     * @return the response return DiffRecord which is the database saved value
     */
    @Override
    public DiffRecord saveRightValue(Long id, String encodedRightValue) throws BusinessRuleException {

        performBusinessValidations(id, encodedRightValue);

        //Decode before save

        final var diffRecordFound = dataDiffRepository.findById(id);

        if (diffRecordFound.isPresent()) {
            diffRecordFound.get().setRightValue(encodedRightValue);
            return dataDiffRepository.save(diffRecordFound.get());
        }
        else{
            DiffRecord diffRecord = new DiffRecord(id, encodedRightValue, null);
            return dataDiffRepository.save(diffRecord);
        }
    }

    /**
     * Check if id is not null and validated if the encodedValue is not
     * null or empty and also check if it is base64 value
     * @param id provided by upstream system or user
     * @param encodedValue encoded provided by upstream system or user
     */
    private void performBusinessValidations(Long id, String encodedValue) throws BusinessRuleException {

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
