package com.adea.evogame.seed.states;

import com.adea.evogame.gene.GeneFactory;
import com.adea.evogame.seed.Seed;
import com.adea.evogame.seed.builder.SeedBuilderImpl;
import com.adea.evogame.seed.builder.SeedFactory;
import com.adea.evogame.tree.Tree;

import java.util.List;

public class ActiveSeed extends SeedState {


    private static final int MUTATION_RATE = 25;

    @Override
    public List<Seed> produce(Seed context) {
        context.setEnergy(context.getEnergy() - Seed.PROD_ENERGY);
        SeedFactory seedFactory = new SeedFactory(new SeedBuilderImpl());
        List<Seed> seeds = seedFactory.construct(context).build();
        if (!seeds.isEmpty())
            context.setState(new Wood());
        return seeds;
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
        GeneFactory factory = GeneFactory.getFactory();
        int random = (int) (Math.random() * 101);
        if (random < MUTATION_RATE) {
            int position = (int) (Math.random() * context.getGenes().size());
            context.getGenes().set(position, factory.generateRandomGene());
        }
    }

    @Override
    public void die(Seed context) {
        context.setState(new FallingSeed());
        context.setGene(0);
        mutate(context);
    }
}
