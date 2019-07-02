package com.nnf.repository;

import com.nnf.domain.OccurrenceTime;
import com.nnf.domain.RemedyCategory;
import com.nnf.domain.Temperature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemperatureRepository extends CrudRepository<Temperature, Long> {

    List<OccurrenceTime> findByTemperatureDescription(String temperatureDescription);

}
