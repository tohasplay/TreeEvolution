package com.adea.evogame.seed.builder;

import com.adea.evogame.gene.Gene;
import com.adea.evogame.gene.GeneCommand;
import com.adea.evogame.gene.GeneFactory;
import com.adea.evogame.seed.Seed;
import com.adea.evogame.seed.states.ActiveSeed;
import com.adea.evogame.tree.Tree;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class SeedFactory {
    private static final int INIT_ENERGY = 300;

    private final List<Seed> producedSeeds = new ArrayList<>();
    private final SeedBuilder builder;

    public SeedFactory construct(Seed context) {
        Gene gene = context.getGenes().get(
                context.getGene()
        );
        for (GeneCommand command :
                gene.getGenes().keySet()) {
            if (gene.getGenes().get(command) < GeneFactory.GENOTYPE && (context.getLocation().getY() + command.getY()) >= 0) {
                Tree parent = (Tree) context.getParent();
                if (!parent.getWorld().haveNeighbour(command, context.getLocation()))
                    producedSeeds.add(
                            builder
                                    .buildEnergy(INIT_ENERGY)
                                    .buildState(new ActiveSeed())
                                    .buildGene(gene.getGenes().get(command))
                                    .buildGenes(context.getGenes())
                                    .buildLocation(
                                            context.getLocation().getX() + command.getX(),
                                            context.getLocation().getY() + command.getY(),
                                            parent.getColor()
                                    )
                                    .getSeed()
                    );
            }
        }
        return this;
    }

    public List<Seed> build() {
        return producedSeeds;
    }
}
