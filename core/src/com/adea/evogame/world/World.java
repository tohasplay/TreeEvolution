package com.adea.evogame.world;

import com.adea.evogame.gene.GeneCommand;
import com.adea.evogame.notifyer.Observer;
import com.adea.evogame.notifyer.Subject;
import com.adea.evogame.tree.Tree;
import com.adea.evogame.utils.Point;
import lombok.Data;

import java.util.*;

@Data
public class World implements Subject {
    private int width = 128;
    private int height = 72;

    private int GLOBAL_SHADOW = 3;

    private static World world = null;

    private List<Point> points = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();

    private HashMap<Point, Boolean> map = new HashMap<>();

    private World() {
        for (int i = 0; i <= width; i++) {
            for (int j = 0; j <= height; j++) {
                map.put(new Point(i,j), false);
            }
        }
    }

    public static World getWorld() {
        if (world == null) world = new World();
        return world;
    }

    private final List<Tree> trees = new ArrayList<>();

    public void addTree(Tree tree) {
        trees.add(tree);
    }

    public void removeTree(Tree tree){
        trees.remove(tree);
    }
    public void proceedLifeCycle(){

        for (int i = 0; i < trees.size(); i++) {
            Tree t = trees.get(i);
            t.incrementLifeCycle();
        }

        for (int i = 0; i < trees.size(); i++) {
            Tree t = trees.get(i);
            t.energyCycle();
        }

        for (int i = 0; i < trees.size(); i++) {
            Tree t = trees.get(i);
            if (t.chekEnergy() <= 0) {
                t.die();
            }
        }

        while(trees.stream().anyMatch(Tree::isDead))
            trees.stream().filter(Tree::isDead).findAny().ifPresent(trees::remove);

        for (int i = 0; i < trees.size(); i++) {
            Tree t = trees.get(i);
            t.grow();
        }

        for (int i = 0; i < trees.size(); i++) {
            Tree t = trees.get(i);
            t.fall();
        }

        refreshSet();

    }

    Point chek = new Point();

    public int amountOfAboveCells(Point position){
        int count = 0;
        chek.move(position.getX(), position.getY());
        for (int i = position.getY() + 1; i < height; i++) {
            chek.setY(i);
            if (map.get(chek)){
                count++;
            }
            if (count == GLOBAL_SHADOW){
                break;
            }
        }
        return GLOBAL_SHADOW - count;
    }

    private void refreshSet() {
        for (Point point : map.keySet()) {
            map.put(point, false);
        }
        for (Tree t :
                trees) {
            List<Point> planted = t.planted();
            for (int i = 0; i < planted.size(); i++) {
                Point p = planted.get(i);
                map.put(p, true);
                points.add(p);
            }
        }
    }

    Point growthPoint = new Point();

    public boolean haveNeighbour(GeneCommand command, Point position) {
        growthPoint.move(
                position.getX() + command.getX(),
                position.getY() + command.getY()
        );
        if (growthPoint.getY() < 0 || growthPoint.getX() < 0) return true;
        if (growthPoint.getY() >= height || growthPoint.getX() >= width) return true;
        return map.get(growthPoint);
    }
    public boolean haveFallingSpace(Point position) {
        growthPoint.move(
                position.getX(),
                position.getY() - 1
        );
        if (growthPoint.getY() < 0) return false;
        return map.get(growthPoint);
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer o :
                observers) {
            o.update();
        }
    }
}

