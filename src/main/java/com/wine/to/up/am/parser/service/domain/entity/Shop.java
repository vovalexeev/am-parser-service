package com.wine.to.up.am.parser.service.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "shops")
@Setter
@Getter
@NoArgsConstructor
public class Shop {
    @Id
    @GeneratedValue
    @Column(name = "shop_id")
    private long shopID;
    @Column(name = "shop_name")
    private String shopName;

    public Shop(String shopName) {
        this.shopName = shopName;
    }

}
