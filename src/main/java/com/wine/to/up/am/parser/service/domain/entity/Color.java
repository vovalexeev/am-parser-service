package com.wine.to.up.am.parser.service.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "colors")
@Setter
@Getter
@NoArgsConstructor
public class Color {
    @Id
    @GeneratedValue
    @Column(name = "color_id")
    private long colorID;
    @Column(name = "color_name")
    private String colorName;

    public Color(String colorName) {
        this.colorName = colorName;
    }
}
