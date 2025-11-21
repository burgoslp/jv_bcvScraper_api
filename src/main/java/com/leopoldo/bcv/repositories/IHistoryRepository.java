package com.leopoldo.bcv.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.leopoldo.bcv.models.History;

@Repository
public interface IHistoryRepository extends CrudRepository<History, Long>{

}
