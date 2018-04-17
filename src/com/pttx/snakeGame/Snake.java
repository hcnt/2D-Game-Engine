package com.pttx.snakeGame;

import com.pttx.game_engine.GameContainer;
import com.pttx.game_engine.Renderer;
import com.pttx.game_engine.gfx.Image;

import java.util.ArrayList;

public class Snake {
    private ArrayList<Component> components;
    private Image body = new Image("/test.png");
    private int xSpeed = 0;
    private int ySpeed = 1;
    private long updateTimer = System.nanoTime();
    private int gridNumber;

    public Snake(int gridNumber){
        this.gridNumber = gridNumber;
        components = new ArrayList<>();
        components.add(new Component(5,5));
        components.add(new Component(6,5));
    }
    public void updateSnake(GameContainer g){
        if (System.nanoTime() - updateTimer > 1000000000) {
            updateTimer = System.nanoTime();
            for (int i = 1; i<components.size(); i++) {
                components.get(i).x = components.get(i-1).x;
                components.get(i).y = components.get(i-1).y;
            }
            components.get(0).x += xSpeed;
            components.get(0).y += ySpeed;
        }
    }

    public void drawSnake(Renderer r,int windowWidth){
        int componentSize = windowWidth / gridNumber;
        for(int i = 0; i<components.size(); i++){
            r.drawImage(body,components.get(i).x*componentSize,components.get(i).y*componentSize);
        }
    }

}
