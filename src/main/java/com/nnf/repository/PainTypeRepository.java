package com.nnf.repository;

import com.nnf.domain.OccurrenceTime;
import com.nnf.domain.PainPosition;
import com.nnf.domain.PainType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PainTypeRepository extends CrudRepository<PainType, Long> {

    List<OccurrenceTime> findByTypeDescription(String typeDescription);

}
