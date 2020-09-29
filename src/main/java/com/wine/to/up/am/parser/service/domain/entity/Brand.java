package com.wine.to.up.am.parser.service.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "brands")
@Setter
@Getter
@NoArgsConstructor
public class Brand {
    @Id
    @GeneratedValue
    @Column(name = "brand_id")
    private long brandID;
    @Column(name = "brand_name")
    private String brandName;

    public Brand(String brandName) {
        this.brandName = brandName;
    }
}
