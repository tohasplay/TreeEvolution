package com.adea.evogame.seed;

import com.adea.evogame.gene.Gene;
import com.adea.evogame.seed.states.ActiveSeed;
import com.adea.evogame.tree.Tree;
import com.adea.evogame.utils.Point;
import com.adea.evogame.world.Biologic;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class SeedTest {

    @Test
    void testSeed() {
        ArrayList<Gene> genes = new ArrayList<>() {
            {
                add(new Gene(1, 0, 1, 1));
                add(new Gene(0, 1, 0, 1));
            }
        };
        Tree tree = new Tree();
        Seed seed = new Seed(new ActiveSeed(), genes, 0, 300, new Point());
        tree.add(seed);
        List<Seed> seeds = seed.produce();
        System.out.println(seed);
        for (Seed s :
                seeds) {
            System.out.println(s);
        }

        for (Seed s :
                seed.produce()) {
            System.out.println(s);
        }

        tree.remove(seed);

        seed = new Seed(new ActiveSeed(), genes, 0, 600, new Point());
        tree.add(seed);

        tree.grow();

        for (Biologic s :
                tree.getSeeds()) {
            System.out.println("Tree: " + s);
        }

        tree.die();

        System.out.println("=======");
        for (Biologic s :
                tree.getSeeds()) {
            System.out.println("Tree: " + s);
        }

    }
}