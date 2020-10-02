package com.wine.to.up.am.parser.service.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sugar")
@Setter
@Getter
@NoArgsConstructor
public class Sugar {
    @Id
    @GeneratedValue
    @Column(name = "sugar_id")
    private long sugarID;
    @Column(name = "import_id")
    private long importID;
    private String sugarName;

    public Sugar(long importID, String sugarName) {
        this.importID = importID;
        this.sugarName = sugarName;
    }
}

