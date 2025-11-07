package com.leopoldo.bcv.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.leopoldo.bcv.models.Coin;

@Repository
public interface ICoinRepository   extends CrudRepository<Coin, Long> {

}
