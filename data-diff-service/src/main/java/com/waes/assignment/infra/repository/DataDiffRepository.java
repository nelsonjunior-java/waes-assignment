package com.waes.assignment.infra.repository;

import com.waes.assignment.domain.model.DiffRecord;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DataDiffRepository extends MongoRepository<DiffRecord, ObjectId> {
    Optional<DiffRecord> findById(Long id);
}
