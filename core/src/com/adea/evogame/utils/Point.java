package com.adea.evogame.utils;

import com.badlogic.gdx.graphics.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Point {
    private int x = 0;
    private int y = 0;

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point copy() {
        return new Point(x, y);
    }

    public ColorPoint copy(Color color) {
        return new ColorPoint(x, y, color);
    }
}
