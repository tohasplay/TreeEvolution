package com.adea.evogame.seed.builder;

import com.adea.evogame.gene.Gene;
import com.adea.evogame.seed.states.SeedState;
import com.adea.evogame.utils.Point;

import java.util.List;

public class SeedBuilderImpl extends SeedBuilder {

    @Override
    SeedBuilder buildState(SeedState state) {
        seed.setState(state);
        return this;
    }

    @Override
    SeedBuilder buildGenes(List<Gene> genes) {
        seed.setGenes(genes);
        return this;
    }

    @Override
    SeedBuilder buildGene(int gene) {
        seed.setGene(gene);
        return this;
    }

    @Override
    SeedBuilder buildEnergy(int energy) {
        seed.setEnergy(energy);
        return this;
    }

    @Override
    SeedBuilder buildLocation(int x, int y) {
        seed.setLocation(new Point(x, y));
        return this;
    }

}
