package com.thomas.tp_spring.model.hero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeroDTO {
    private Long id;
    private String name;
    private String universe;
    private int strength;
    private int defence;
    private int speed;
    private int accuracy;
    private int intelligence;
    private int luck;
}
