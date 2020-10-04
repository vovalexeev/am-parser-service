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
    @Column(name = "id")
    private long id;

    @Column(name = "import_id")
    private String importId;

    private String name;

    public Brand(String name) {
        this.name = name;
    }
}
