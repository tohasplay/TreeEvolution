package com.adea.evogame;

import com.adea.evogame.notifyer.Observer;
import com.adea.evogame.tree.Tree;
import com.adea.evogame.utils.ColorPoint;
import com.adea.evogame.utils.Point;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.List;

public class EvoMain extends ApplicationAdapter implements Observer {


    GameFactory game;

    ShapeRenderer shapeRenderer;

    List<ColorPoint> render = new ArrayList<>();

    @Override
    public void create() {
        game = GameFactory.getGameFactory();

        game.attach(this);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

    }


    @Override
    public void render() {
        if (!game.isStarted())
            game.start();
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.begin();
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);


        try {
            ArrayList<ColorPoint> points;
            synchronized (this) {
                points = new ArrayList<>(render);
            }
            for (ColorPoint p :
                    points) {
                Color color = p.getColor();
                shapeRenderer.setColor(color);
                shapeRenderer.rect(p.getX() * 10, (p.getY() + 1) * 10, 10, 10);
            }
        } catch(NullPointerException e){
            e.printStackTrace();
        }
        shapeRenderer.end();

    }

    @Override
    public void dispose() {

    }

    @Override
    public void update() {
        render.clear();
        for (Tree tree :
                game.getWorld().getTrees()) {
            for (Point p :
                    tree.planted()) {
                render.add(p.copy(tree.getColor()));
            }
        }
    }
}
