package com.adea.evogame.utils;

import com.badlogic.gdx.graphics.Color;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ColorPoint extends Point{

    public ColorPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    Color  color;
}

