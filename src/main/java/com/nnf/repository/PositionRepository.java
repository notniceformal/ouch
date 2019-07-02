package com.nnf.repository;

import com.nnf.domain.OccurrenceTime;
import com.nnf.domain.PainPosition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends CrudRepository<PainPosition, Long> {

    List<OccurrenceTime> findByPositionName(String positionName);

}
