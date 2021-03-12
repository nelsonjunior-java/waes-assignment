package com.waes.assignment.repository;

import com.waes.assignment.domain.model.LeftAndRightRecord;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataDiffRepository extends MongoRepository<LeftAndRightRecord, ObjectId> {
}
