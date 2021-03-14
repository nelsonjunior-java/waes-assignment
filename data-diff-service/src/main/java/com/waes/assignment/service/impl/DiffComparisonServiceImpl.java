package com.waes.assignment.service.impl;

import com.waes.assignment.domain.comparator.ValueDifferenceComparator;
import com.waes.assignment.domain.exception.BusinessRuleException;
import com.waes.assignment.domain.exception.NotFoundException;
import com.waes.assignment.domain.message.MessageHelper;
import com.waes.assignment.domain.model.DiffRecord;
import com.waes.assignment.domain.model.DiffRecordStatus;
import com.waes.assignment.domain.model.Difference;
import com.waes.assignment.infra.repository.DataDiffRepository;
import com.waes.assignment.service.DiffComparisonService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.waes.assignment.domain.message.MessageCode.ERROR_NULL_ID;
import static com.waes.assignment.domain.message.MessageCode.ERROR_DIFF_RECORD_NOT_FOUND;
import static com.waes.assignment.domain.message.MessageCode.ERROR_MISSING_LEFT_VALUE;
import static com.waes.assignment.domain.message.MessageCode.ERROR_MISSING_RIGHT_VALUE;

/**
 * Implementation of the service comparison that will have all the logic compare the Left and Right values
 * saved on the database
 */
@Service
public class DiffComparisonServiceImpl implements DiffComparisonService {

    private final DataDiffRepository dataDiffRepository;
    private final MessageHelper messageHelper;
    private final ValueDifferenceComparator<List<Difference>, String> valueDifferenceComparator;

    public DiffComparisonServiceImpl(DataDiffRepository dataDiffRepository, MessageHelper messageHelper, ValueDifferenceComparator valueDifferenceComparator) {
        this.dataDiffRepository = dataDiffRepository;
        this.messageHelper = messageHelper;
        this.valueDifferenceComparator = valueDifferenceComparator;
    }

    /**
     * This method is responsible for comparing the values from the Left and Right endpoints
     * that were stored in the database
     * @param id diff record id that will be used to compare the values
     * @return the response return DiffRecord which is the database saved value
     */
    @Override
    public DiffRecord getDifferenceBetweenValues(Long id) throws BusinessRuleException {

        performIdValidatons(id);

        final var diffRecordFound = dataDiffRepository.findById(id).orElseThrow(()
                -> new NotFoundException(messageHelper.get(ERROR_DIFF_RECORD_NOT_FOUND)));

        validateDiffRecord(diffRecordFound);

        //if the status is NOT equals to WAITING_EVALUATION, then it not necessary to evaluate it again
        if(diffRecordFound.getDiffRecordStatus().equals(DiffRecordStatus.WAITING_EVALUATION)){
            evaluateDifferences(diffRecordFound);
        }

        return diffRecordFound;
    }

    /**
     * This method validates the DiffRecord to make sure it can be compared
     * that were stored in the database
     * @param diffRecord diff record to be validated
     */
    private void validateDiffRecord(DiffRecord diffRecord) throws BusinessRuleException {

        if (StringUtils.isEmpty(diffRecord.getLeftValue())) {
            throw new BusinessRuleException(messageHelper.get(ERROR_MISSING_LEFT_VALUE));
        }

        if (StringUtils.isEmpty(diffRecord.getRightValue())) {
            throw new BusinessRuleException(messageHelper.get(ERROR_MISSING_RIGHT_VALUE));
        }
    }

    /**
     * This method validates the DiffRecord id before continuing
     * with the values comparison
     * @param id DiffRecord id to be validated
     */
    public void performIdValidatons(Long id) throws BusinessRuleException {
        if(id == null){
            throw new BusinessRuleException(messageHelper.get(ERROR_NULL_ID));
        }
    }

    /**
     * This method has all the logic for the left and right values stored on the database
     * and setting the status accordingly
     * @param diffRecord DiffRecord id to be validated
     */
    private void evaluateDifferences(DiffRecord diffRecord) {

        if (diffRecord.getLeftValue().equals(diffRecord.getRightValue())){
            diffRecord.setDiffRecordStatus(DiffRecordStatus.EQUAL);
        }
        else if (diffRecord.getLeftValue().length() != diffRecord.getRightValue().length()){
            diffRecord.setDiffRecordStatus(DiffRecordStatus.DIFFERENT_SIZE);
        }
        else{
            List<Difference> differences = valueDifferenceComparator.compare(diffRecord.getLeftValue(), diffRecord.getRightValue());
            diffRecord.setDifferences(differences);
            diffRecord.setDiffRecordStatus(DiffRecordStatus.SAME_SIZE_WITH_DIFFERENCES);
        }

        dataDiffRepository.save(diffRecord);
    }


}
