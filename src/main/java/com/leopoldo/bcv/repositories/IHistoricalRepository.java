package com.leopoldo.bcv.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.leopoldo.bcv.models.Historical;

@Repository
public interface IHistoricalRepository extends CrudRepository<Historical, Long> {

}
