package com.adea.evogame.world;

import com.adea.evogame.gene.GeneFactory;
import com.adea.evogame.seed.Seed;
import com.adea.evogame.seed.states.ActiveSeed;
import com.adea.evogame.tree.Tree;
import com.adea.evogame.utils.Point;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class WorldTest {

    @Test
    void globalWorldTest() {
        World world = World.getWorld();
        Tree tree = new Tree();
        world.addTree(tree);
        tree.setWorld(world);

        GeneFactory factory = GeneFactory.getFactory();
        Seed seed = new Seed(new ActiveSeed(), factory.createGenotype(), 0, 300, new Point(5, 0));

        tree.add(seed);

        for (int i = 0; i < 120; i++) {
            world.proceedLifeCycle();

            System.out.println(Arrays.toString(world.getTrees().toArray()));
            System.out.println(world.getPoints());
        }

    }
}