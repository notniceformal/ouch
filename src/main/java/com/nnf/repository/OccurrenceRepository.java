package com.nnf.repository;

import com.nnf.domain.OccurrenceTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OccurrenceRepository extends CrudRepository<OccurrenceTime, Long> {

    List<OccurrenceTime> findByTimeDescription(String timeDescription);

}
