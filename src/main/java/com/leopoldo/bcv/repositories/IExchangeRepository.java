package com.leopoldo.bcv.repositories;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.leopoldo.bcv.models.Coin;
import com.leopoldo.bcv.models.Exchange;
import com.leopoldo.bcv.models.Rate;

@Repository
public interface IExchangeRepository  extends CrudRepository<Exchange,Long>{

    //consultar por nombre de coin
    Optional<Exchange> findByCoinAndRate(Coin coin, Rate rate);

    //consultar por nombre de coin
    List<Exchange> findByCoin_Name(String name);
}
