package com.leopoldo.bcv.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "exchange")
public class Exchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rate_id")
    private Rate rate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coin_id")
    private Coin coin;

    @NotNull
    @Digits(integer=4, fraction=8)
    @Column(name = "value", precision = 12, scale = 8)
    private BigDecimal value;

    @NotNull
    @Digits(integer=4, fraction=8)
    @Column(name = "previous_value", precision = 12, scale = 8)
    private BigDecimal previousValue;
    //@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "la fecha de publicación debe tener el siguiente formato YYYY-MM-DD")
    @NotNull
    private LocalDateTime updateAt; //  2023-10-05T14:30:00
}
