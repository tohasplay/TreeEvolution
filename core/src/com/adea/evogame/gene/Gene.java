package com.adea.evogame.gene;

import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;


@Getter
@ToString
public class Gene{

    Map<GeneCommand, Integer> genes = new HashMap<>();

    public Gene(int... param) {
        int c = 0;
        for (GeneCommand g :
                GeneCommand.values()) {
            genes.put(g, param[c++]);
        }
    }
}
