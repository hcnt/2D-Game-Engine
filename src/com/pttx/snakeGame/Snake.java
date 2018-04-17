package com.pttx.snakeGame;

import com.pttx.game_engine.GameContainer;
import com.pttx.game_engine.Renderer;
import com.pttx.game_engine.gfx.Image;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Snake {
    private ArrayList<Component> components;
    private Image body = new Image("/square.png");
    private int xSpeed = 0;
    private int ySpeed = 1;
    private long updateTimer = System.nanoTime();
    private int gridNumber;
    private boolean wasDirectionChanged = false;

    public Snake(int gridNumber){
        this.gridNumber = gridNumber;
        components = new ArrayList<>();
        components.add(new Component(5,5));
        components.add(new Component(5,6));
        components.add(new Component(5,7));
    }
    public void updateSnake(GameContainer g){
        changeDirection(g);
        if (System.nanoTime() - updateTimer > 100000000) {
            wasDirectionChanged = false;
            updateTimer = System.nanoTime();
            System.out.println(components.size());
            for (int i = components.size()-1; i>=1; i--) {
                components.get(i).x = components.get(i-1).x;
                components.get(i).y = components.get(i-1).y;
            }
            components.get(0).x += xSpeed;
            components.get(0).y += ySpeed;
        }
    }
    public void changeDirection(GameContainer g){
        if(g.getInput().isKeyDown(KeyEvent.VK_DOWN) && ySpeed != -1 && !wasDirectionChanged){
            ySpeed = 1;
            xSpeed = 0;
            wasDirectionChanged = true;
        }
        if(g.getInput().isKeyDown(KeyEvent.VK_UP) && ySpeed != 1 && !wasDirectionChanged){
            ySpeed = -1;
            xSpeed = 0;
            wasDirectionChanged = true;
        }
        if(g.getInput().isKeyDown(KeyEvent.VK_LEFT) && xSpeed != 1 && !wasDirectionChanged){
            xSpeed = -1;
            ySpeed = 0;
            wasDirectionChanged = true;
        }
        if(g.getInput().isKeyDown(KeyEvent.VK_RIGHT) && xSpeed != -1 && !wasDirectionChanged){
            xSpeed = 1;
            ySpeed = 0;
            wasDirectionChanged = true;
        }
    }
    public void drawSnake(Renderer r,int windowWidth){
        int componentSize = windowWidth / gridNumber;
        for(int i = 0; i<components.size(); i++){
            components.get(i).drawComponent(r,body,componentSize);
        }
    }
}
