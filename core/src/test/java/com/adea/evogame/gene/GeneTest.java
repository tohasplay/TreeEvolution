package com.adea.evogame.gene;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class GeneTest {

    @Test
    void createGene() {

        System.out.println(Arrays.toString(GeneCommand.values()));
        Gene gene = new Gene(2,10,5,2);
        for (GeneCommand c :
                gene.getGenes().keySet()) {
            System.out.printf("Command %s x: %d y: %d", c.name(), c.getX(), c.getY());
            System.out.println();
        }
        for (GeneCommand c :
                gene.getGenes().keySet()) {
            System.out.printf("Command %s gene: %d", c.name(),gene.getGenes().get(c) );
            System.out.println();
        }

    }
}