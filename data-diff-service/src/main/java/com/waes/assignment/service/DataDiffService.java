package com.waes.assignment.service;

import com.waes.assignment.domain.model.LeftAndRightRecord;

public interface DataDiffService {

    LeftAndRightRecord saveLeftValue(String id, String encodedLeftValue);
}
