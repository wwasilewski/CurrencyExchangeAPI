package pl.sda.CurrencyExchangeAPI.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "currency")
public class CurrencyRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "base", nullable = false)
    private String base;
    @Column(name = "target", nullable = false)
    private String target;
    @Column(name = "rate")
    private Double rate;
    @Column(name = "date", nullable = false)
    private String date;
}
