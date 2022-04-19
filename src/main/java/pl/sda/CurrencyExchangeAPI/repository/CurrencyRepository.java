package pl.sda.CurrencyExchangeAPI.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sda.CurrencyExchangeAPI.model.CurrencyRate;

@Repository
public interface CurrencyRepository extends CrudRepository<CurrencyRate, Integer> {

    CurrencyRate findCurrencyRateByBaseAndTargetAndDate(String base, String target, String date);

//    CurrencyRate findCurrencyRateByBase(String base);

}
