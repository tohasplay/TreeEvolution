package com.adea.evogame.world;

import com.adea.evogame.utils.Point;
import java.util.List;

public interface Biologic {

    List<Point> planted();
    void grow();
    void die();
    void setParent(Biologic parent);
    void energyCycle();
    int chekEnergy();
    void fall();

    default void add(Biologic b){
        //for leaf ignore
    }
    default void remove(Biologic b){
        //for leaf ignore
    }
    default Biologic getChild(int child){
        //for leaf
        return null;
    }
    boolean isDead();
}
