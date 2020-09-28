package com.wine.to.up.am.parser.service.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "wine_grapes_info")
@Setter
@Getter
@NoArgsConstructor
public class ShopCatalog {
    @Id
    @GeneratedValue
    @Column(name = "shop_id")
    private long shopID;
    @Column(name = "wine_id")
    private long wineID;
    @Column(name = "wine_shop_id")
    private long wineShopID;
    private double price;

    public ShopCatalog(long wineID, long wineShopID, double price) {
        this.wineID = wineID;
        this.wineShopID = wineShopID;
        this.price = price;
    }
}