package com.waes.assignment.service.impl;

import com.waes.assignment.domain.model.LeftAndRightRecord;
import com.waes.assignment.repository.DataDiffRepository;
import com.waes.assignment.service.DataDiffService;
import org.springframework.stereotype.Service;

@Service
public class DataDiffServiceImpl implements DataDiffService {

    private final DataDiffRepository dataDiffRepository;

    public DataDiffServiceImpl(DataDiffRepository dataDiffRepository) {
        this.dataDiffRepository = dataDiffRepository;
    }

    @Override
    public LeftAndRightRecord saveLeftValue(String id, String encodedLeftValue) {

        //Validate Json

        //Validate Base64

        //Decode before save

        LeftAndRightRecord leftAndRightRecord = new LeftAndRightRecord(id, encodedLeftValue, null);

        return dataDiffRepository.save(leftAndRightRecord);
    }
}
