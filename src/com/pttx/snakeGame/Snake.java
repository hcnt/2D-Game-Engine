package com.pttx.snakeGame;

import com.pttx.game_engine.Renderer;
import com.pttx.game_engine.gfx.Image;

import java.util.ArrayList;

public class Snake {
    ArrayList<Component> components;
    Image body = new Image("/test.png");
    int xSpeed;
    int ySpeed;
    int gridNumber;

    public Snake(int gridNumber){
        this.gridNumber = gridNumber;
        components = new ArrayList<>();
        components.add(new Component(1,1));
        components.add(new Component(6,5));
    }

    public void drawSnake(Renderer r,int windowWidth){
        int componentSize = windowWidth / gridNumber;
        for(int i = 0; i<components.size(); i++){
            r.drawImage(body,components.get(i).x*componentSize,components.get(i).y*componentSize);
        }
    }

}
