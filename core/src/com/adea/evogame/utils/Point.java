package com.adea.evogame.utils;

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

    public void move(int x, int y){
        this.x = x;
        this.y = y;
    }
}
