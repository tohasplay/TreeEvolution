package com.adea.evogame.seed.states;

import com.adea.evogame.gene.GeneCommand;
import com.adea.evogame.seed.Seed;
import com.adea.evogame.tree.Tree;

import java.util.Collections;
import java.util.List;

public class FallingSeed extends SeedState {


    @Override
    public List<Seed> produce(Seed context) {
        return Collections.emptyList();
    }

    @Override
    public void obtainEnergy(Seed context, int sunEnergy) {
        //ignore
    }

    @Override
    public void fall(Seed context) {

        Tree tree = (Tree) context.getParent();
        if (tree.getWorld().haveFallingSpace(context.getLocation()))
            die(context);


        if (context.getLocation().getY() - 1 >= 0)
            context.getLocation().setY(context.getLocation().getY() - 1);
        else
            context.setState(new ActiveSeed());
    }

    @Override
    public void mutate(Seed context) {
        //ignore
    }

    @Override
    public void die(Seed context) {
        context.getParent().remove(context);
    }
}
