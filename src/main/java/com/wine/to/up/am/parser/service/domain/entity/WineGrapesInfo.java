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
public class WineGrapesInfo {
    @Id
    @GeneratedValue
    @Column(name = "wine_grapes_info_id")
    private long wineGrapesInfoID;
    @Column(name = "wine_grapes_id")
    private long wineGrapesID;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "grape_id")
    private Grape grape;

    public WineGrapesInfo(long wineGrapesID, Grape grape) {
        this.wineGrapesID = wineGrapesID;
        this.grape = grape;
    }
}