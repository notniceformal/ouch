package com.nnf.repository;

import com.nnf.domain.OccurrenceTime;
import com.nnf.domain.PainPosition;
import com.nnf.domain.RemedyCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RemedyRepository extends CrudRepository<RemedyCategory, Long> {

    List<OccurrenceTime> findByRemedyDescription(String remedyDescription);

}
