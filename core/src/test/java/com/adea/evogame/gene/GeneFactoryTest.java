package com.adea.evogame.gene;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class GeneFactoryTest {

    @Test
    void generation() {
        GeneFactory geneFactory = GeneFactory.getFactory();
        System.out.println(Arrays.toString(geneFactory.createGenotype().toArray()));
    }
}