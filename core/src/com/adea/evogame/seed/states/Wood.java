package com.adea.evogame.seed.states;

import com.adea.evogame.seed.Seed;
import com.adea.evogame.tree.Tree;

import java.util.Collections;
import java.util.List;

public class Wood extends SeedState {


    @Override
    public List<Seed> produce(Seed context) {
        return Collections.emptyList();
    }

    @Override
    public void obtainEnergy(Seed context, int sunEnergy) {
        Tree tree = (Tree) context.getParent();
        int position = tree.getWorld().amountOfAboveCells(context.getLocation());
        if (position <= 0)
            return;
        context.setEnergy(
                context.getEnergy() + sunEnergy * position
        );
    }

    @Override
    public void fall(Seed context) {
        //ignore
    }

    @Override
    public void mutate(Seed context) {
        //ignore
    }

    @Override
    public void die(Seed context) {
        context.setDead(true);
    }
}
