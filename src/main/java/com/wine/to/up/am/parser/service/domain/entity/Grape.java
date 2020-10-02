package com.wine.to.up.am.parser.service.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "grapes")
@Setter
@Getter
@NoArgsConstructor
public class Grape {
    @Id
    @GeneratedValue
    @Column(name = "grape_id")
    private long grapeID;
    @Column(name = "import_id")
    private long importID;
    private String grapeName;

    public Grape(String grapeName) {
        this.grapeName = grapeName;
    }
}
