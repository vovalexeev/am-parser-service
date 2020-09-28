package com.wine.to.up.am.parser.service.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
public class Wine {
    private String name;
    private BigDecimal previousCost;
    private BigDecimal currentCost;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wine wine = (Wine) o;
        return Objects.equals(name, wine.name) &&
                Objects.equals(previousCost, wine.previousCost) &&
                Objects.equals(currentCost, wine.currentCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, previousCost, currentCost);
    }
}
