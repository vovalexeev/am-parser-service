package com.wine.to.up.am.parser.service.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "countries")
@Setter
@Getter
@NoArgsConstructor
public class Country {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "import_id")
    private String importId;

    @Column(name = "name")
    private String name;

    public Country(String name) {
        this.name = name;
    }
}