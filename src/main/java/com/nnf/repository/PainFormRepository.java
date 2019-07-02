package com.nnf.repository;

import com.nnf.domain.CauseCategory;
import com.nnf.dto.chronic.PainCaptureForm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PainFormRepository extends CrudRepository<PainCaptureForm, Long> {

}
