package com.adea.evogame.seed.builder;

import com.adea.evogame.gene.Gene;
import com.adea.evogame.seed.Seed;
import com.adea.evogame.seed.states.SeedState;

import java.util.List;

public abstract class SeedBuilder {

    protected Seed seed = new Seed();

    abstract SeedBuilder buildState(SeedState state);
    abstract SeedBuilder buildGenes(List<Gene> genes);
    abstract SeedBuilder buildGene(int gene);
    abstract SeedBuilder buildEnergy(int energy);
    abstract SeedBuilder buildLocation(int x, int y);

    public Seed getSeed(){
        Seed result = seed;
        seed = new Seed();
        return result;
    }




}
