package com.leopoldo.bcv.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.leopoldo.bcv.models.Rate;

@Repository
public interface IRateRepository  extends CrudRepository<Rate, Long> {
    
}
