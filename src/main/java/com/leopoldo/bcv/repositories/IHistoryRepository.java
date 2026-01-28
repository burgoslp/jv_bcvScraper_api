package com.leopoldo.bcv.repositories;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.leopoldo.bcv.models.History;

@Repository
public interface IHistoryRepository extends CrudRepository<History, Long>{
    List<History> findAllByCoinNameAndCreateAtBetween(String coinName, LocalDateTime start, LocalDateTime end);
}