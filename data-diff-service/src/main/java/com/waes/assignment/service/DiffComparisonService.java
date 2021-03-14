package com.waes.assignment.service;

import com.waes.assignment.domain.exception.BusinessRuleException;
import com.waes.assignment.domain.model.DiffRecord;

/**
 * This interface defines the contract for the DiffComparison service
 */
public interface DiffComparisonService {

    DiffRecord getDifferenceBetweenValues(Long id) throws BusinessRuleException;
}
