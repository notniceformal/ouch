package com.nnf.repository;

import com.nnf.domain.CauseCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CauseRepository extends CrudRepository<CauseCategory, Long> {

    List<CauseCategory> findByCauseDescription(String causeDescription);

}
