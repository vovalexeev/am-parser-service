package com.wine.to.up.am.parser.service.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    @Column(name = "import_id")
    private long importID;
    @Column(name = "brand_name")
    private String brandName;

    public Brand(long importID, String brandName) {
        this.importID = importID;
        this.brandName = brandName;
    }
}
