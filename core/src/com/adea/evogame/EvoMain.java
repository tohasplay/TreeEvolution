package com.adea.evogame;

import com.adea.evogame.notifyer.Observer;
import com.adea.evogame.tree.Tree;
import com.adea.evogame.utils.Point;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EvoMain extends ApplicationAdapter implements Observer {

    private GameFactory game;

    ShapeRenderer shapeRenderer;
    Rectangle rectangle;

    @Override
    public void create() {
        game = GameFactory.getGameFactory(this);
        rectangle = new Rectangle(0, 0, 5, 5);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

    }

    List<Tree> bio = new ArrayList<>();
    List<Point> tmp = new ArrayList<>();

    @Override
    public void render() {
        if (!game.isStarted())
            game.start();
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.begin();
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);

        bio.clear();
        bio.addAll(game.getWorld().getTrees());

        synchronized (this) {
            if (!bio.isEmpty())
                for (Tree t : bio) {
                    tmp.clear();
                    tmp.addAll(t.planted());
                    for (Point p : tmp) {
                        shapeRenderer.setColor(t.getColor());
                        shapeRenderer.rect(p.getX() * 10, (p.getY() + 1) * 10, 10, 10);
                    }
                }
        }
        shapeRenderer.end();

    }

    @Override
    public void dispose() {

    }

    @Override
    public void update() {
        render();
    }
}
