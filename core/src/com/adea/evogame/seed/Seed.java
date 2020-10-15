package com.adea.evogame.seed;

import com.adea.evogame.gene.Gene;
import com.adea.evogame.seed.states.SeedState;
import com.adea.evogame.utils.Point;
import com.adea.evogame.world.Biologic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seed implements Biologic {
    public static final int PROD_ENERGY = 500;

    private Biologic parent;

    private SeedState state;
    private List<Gene> genes;
    private int gene;
    private int energy;
    private Point location;
    private boolean isDead = false;

    public Seed(SeedState state, List<Gene> genes, int gene, int energy, Point location) {
        this.state = state;
        this.genes = genes;
        this.gene = gene;
        this.energy = energy;
        this.location = location;
    }

    public List<Seed> produce() {
        return state.produce(this);
    }


    public void obtainEnergy() {
        state.obtainEnergy(this, (location.getY() + 3) * 2);
    }

    public void fall() {
        state.fall(this);
    }

    public boolean hasEnoughEnergy() {
        return energy >= PROD_ENERGY;
    }


    @Override
    public List<Point> planted() {
        return Collections.singletonList(location);
    }

    @Override
    public void grow() {
        if (hasEnoughEnergy())
            for (Seed seed :
                    produce()) {
                parent.add(seed);
            }
    }

    @Override
    public void energyCycle() {
        obtainEnergy();
    }

    @Override
    public void die() {
        state.die(this);
    }

    @Override
    public void setParent(Biologic parent) {
        this.parent = parent;
    }


    @Override
    public int chekEnergy() {
        return energy;
    }

    @Override
    public boolean isDead() {
        return isDead;
    }
}
