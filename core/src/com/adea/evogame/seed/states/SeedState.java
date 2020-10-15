package com.adea.evogame.seed.states;

import com.adea.evogame.seed.Seed;

import java.util.List;

public abstract class SeedState {

    public abstract List<Seed> produce(Seed context);
    public abstract void obtainEnergy(Seed context, int sunEnergy);
    public abstract void fall(Seed context);
    public abstract void mutate(Seed context);
    public abstract void die(Seed context);

}
