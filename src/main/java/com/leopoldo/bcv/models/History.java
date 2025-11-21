package com.leopoldo.bcv.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String rateName;
    @NotNull
    private String coinName; 
    @NotNull
    @Digits(integer = 4, fraction = 8)
    @Column(name = "value", precision = 12, scale = 8)
    private BigDecimal value;
    @NotNull
    @Digits(integer = 4, fraction = 8)
    @Column(name = "previous_value", precision = 12, scale = 8)
    private BigDecimal previousValue;
    @NotNull
    private LocalDateTime createAt; 
}
