package com.pttx.snakeGame;

import com.pttx.game_engine.Renderer;
import com.pttx.game_engine.gfx.Image;

public class Component {
    int x;
    int y;

    public Component(){}
    public Component(int x,int y){
        this.x = x;
        this.y = y;
    }

    public void drawComponent(Renderer r, Image image,int size){
        r.drawImage(image,x*size,y*size);
    }

}
