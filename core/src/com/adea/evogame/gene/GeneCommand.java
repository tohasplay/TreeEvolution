package com.adea.evogame.gene;

import lombok.Getter;

@Getter
public enum GeneCommand {

    UP_GROWTH(0, 1),
    DOWN_GROWTH(0, -1),
    LEFT_GROWTH(-1, 0),
    RIGHT_GROWTH(1, 0);

    private int x;
    private int y;

    GeneCommand(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
