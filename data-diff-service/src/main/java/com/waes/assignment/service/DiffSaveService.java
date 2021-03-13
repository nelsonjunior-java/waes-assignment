package com.waes.assignment.service;

import com.waes.assignment.domain.exception.BusinessRuleException;
import com.waes.assignment.domain.model.DiffRecord;

public interface DiffSaveService {

    DiffRecord saveLeftValue(Long id, String encodedLeftValue) throws BusinessRuleException;

    DiffRecord saveRightValue(Long id, String encodedRightValue) throws BusinessRuleException;
}
