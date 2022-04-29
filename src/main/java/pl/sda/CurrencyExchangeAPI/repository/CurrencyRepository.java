package pl.sda.CurrencyExchangeAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.CurrencyExchangeAPI.model.CurrencyRate;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyRate, Integer> {

    CurrencyRate findCurrencyRateByBaseAndTargetAndDate(String base, String target, String date);

    List<CurrencyRate> findByBase(String base);
}
