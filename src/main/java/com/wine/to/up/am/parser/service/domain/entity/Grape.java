package com.wine.to.up.am.parser.service.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "grapes")
@Setter
@Getter
@NoArgsConstructor
public class Grape {
    @Id
    @GeneratedValue
    private long grapeID;
    private String grapeName;

    public Grape(String grapeName) {
        this.grapeName = grapeName;
    }
}
