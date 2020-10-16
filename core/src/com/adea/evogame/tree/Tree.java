package com.adea.evogame.tree;

import com.adea.evogame.utils.Point;
import com.adea.evogame.world.Biologic;
import com.adea.evogame.world.World;
import com.badlogic.gdx.graphics.Color;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Tree implements Biologic {

    private static final int MAX_LIFECYCLE = 90;
    private static final int ENERGY_CONSUMING = 17;
    private int lifeCycle = 0;

    private final List<Biologic> seeds = new ArrayList<>();
    private boolean isDead = false;

    private Color color = new Color((float) Math.random(), (float) Math.random(), (float) Math.random(), 1);

    @Setter
    private World world;

    public void incrementLifeCycle() {
        if (lifeCycle == MAX_LIFECYCLE) {
            die();
            return;
        }
        lifeCycle++;
    }

    public int chekEnergy() {
        int energy = 0;
        for (Biologic c :
                seeds) {
            energy += c.chekEnergy();
        }
        return energy - seeds.size() * ENERGY_CONSUMING;
    }

    @Override
    public void fall() {
        for (int i = 0; i < seeds.size(); i++) {
            Biologic c = seeds.get(i);
            c.fall();
        }
    }


    List<Point> area = new ArrayList<>();

    @Override
    public synchronized List<Point> planted() {
        area.clear();
        if (!seeds.isEmpty())
            for (int i = 0; i < seeds.size(); i++) {
                if (i <= seeds.size() -1 ) {
                    Biologic c = seeds.get(i);
                    if (area != null && c != null)
                        area.addAll(c.planted());
                }
            }
        return area;
    }

    @Override
    public void energyCycle() {
        for (Biologic c :
                seeds) {
            c.energyCycle();
        }
    }

    @Override
    public void grow() {
        int size = seeds.size();
        int i = 0;
        while (i < size) {
            seeds.get(i).grow();
            i++;
        }
    }

    @Override
    public void die() {
        isDead = true;
        for (int i = 0, seedsSize = seeds.size(); i < seedsSize; i++) {
            Biologic seed = seeds.get(i);
            seed.die();
        }

        while (seeds.stream().anyMatch(Biologic::isDead)) {
            seeds.stream().filter(Biologic::isDead).findAny().ifPresent(seeds::remove);
        }

        for (Biologic c :
                seeds) {
            Tree created = new Tree();
            world.addTree(created);
            created.setWorld(world);
            created.add(c);
        }
        seeds.clear();
        world.removeTree(this);
    }

    @Override
    public void setParent(Biologic parent) {
        //ignore
    }

    @Override
    public void add(Biologic b) {
        b.setParent(this);
        seeds.add(b);
    }

    @Override
    public void remove(Biologic b) {
        seeds.remove(b);
    }

    @Override
    public Biologic getChild(int child) {
        return seeds.get(child);
    }

    @Override
    public boolean isDead() {
        return isDead;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "lifeCycle=" + lifeCycle +
                ", seeds=" + seeds.size() +
                '}';
    }
}
