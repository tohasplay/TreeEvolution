package com.adea.evogame;

import com.adea.evogame.gene.GeneFactory;
import com.adea.evogame.notifyer.Observer;
import com.adea.evogame.seed.Seed;
import com.adea.evogame.seed.states.ActiveSeed;
import com.adea.evogame.seed.states.FallingSeed;
import com.adea.evogame.tree.Tree;
import com.adea.evogame.utils.Point;
import com.adea.evogame.world.World;
import lombok.Getter;

@Getter
public class GameFactory {

    private static GameFactory factory = null;
    private final World world;
    private Thread main = null;
    private boolean isStarted = false;

    private GameFactory() {
        world = World.getWorld();
        Tree tree = new Tree();
        tree.setWorld(world);
        world.addTree(tree);

        GeneFactory factory = GeneFactory.getFactory();

        Seed seed = new Seed(new ActiveSeed(), factory.createGenotype(), 0, 300, new Point(50, 0));
        tree.add(seed);
    }

    public static GameFactory getGameFactory() {
        if (factory == null) {
            factory = new GameFactory();
        }
        return factory;
    }

    public void attach(Observer observer) {
        world.attach(observer);
    }

    public void start() {
        isStarted = true;
        if (main == null) {
            main = new Thread(
                    () -> {
                        while (true) {
                            world.proceedLifeCycle();
                            try {
                                Thread.sleep(25);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            );
            main.start();
        }
    }

}
