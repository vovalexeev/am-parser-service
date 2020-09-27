package com.wine.to.up.am.parser.service.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Wine {
    private String name;
    private BigDecimal previousCost;
    private BigDecimal currentCost;
}
