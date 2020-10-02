package com.wine.to.up.am.parser.service.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "wines")
@Setter
@Getter
@NoArgsConstructor
public class Wine {
    @Id
    @GeneratedValue
    @Column(name = "wine_id")
    private long wineID;
    @Column(name = "import_id")
    private long importID;
    private byte[] picture;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id")
    private Country country;
    private double volume;
    private double strength;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "color_id")
    private Color color;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "sugar_id")
    private Sugar sugar;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "wine_grapes_id")
    private WineGrapesInfo wineGrapesInfo;
    @Column(name = "date_bottling")
    private Date dateBottling;
    private double price;

    public Wine(byte[] picture, Brand brand, Country country, double volume, double strength, Color color,
                Sugar sugar, WineGrapesInfo wineGrapesInfo, Date dateBottling, double price) {
        this.picture = picture;
        this.brand = brand;
        this.country = country;
        this.volume = volume;
        this.strength = strength;
        this.color = color;
        this.sugar = sugar;
        this.wineGrapesInfo = wineGrapesInfo;
        this.dateBottling = dateBottling;
        this.price = price;
    }
}