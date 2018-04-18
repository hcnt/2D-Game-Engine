package com.pttx.snakeGame;

import com.pttx.game_engine.GameContainer;
import com.pttx.game_engine.Renderer;
import com.pttx.game_engine.gfx.Image;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Snake {
    private ArrayList<Component> components;
    private Component head;
    private Image body = new Image("/square.png");
    private long updateTimer = System.nanoTime();
    private int gridNumber;
    private boolean wasDirectionChanged = false;
    Direction direction = Direction.DOWN;
    public enum Direction{
        UP(0,-1),DOWN(0,1),RIGHT(1,0),LEFT(-1,0);
        int xSpeed;
        int ySpeed;
        Direction(int xSpeed, int ySpeed){
            this.xSpeed = xSpeed;
            this.ySpeed = ySpeed;
        }
    }

    public Snake(int gridNumber){
        this.gridNumber = gridNumber;
        components = new ArrayList<>();
        components.add(new Component(5,5));
        components.add(new Component(5,6));
        components.add(new Component(5,7));
        head = components.get(0);
    }

    public ArrayList<Component> getComponents() {
        return components;
    }
    public Component getHead() {
        return head;
    }

    public void updateSnake(GameContainer g){
        changeDirection(g);
        if (System.nanoTime() - updateTimer > 300000000) {
            wasDirectionChanged = false;
            updateTimer = System.nanoTime();
            System.out.println(components.size());
            for (int i = components.size()-1; i>=1; i--) {
                components.get(i).x = components.get(i-1).x;
                components.get(i).y = components.get(i-1).y;
            }
            components.get(0).x += direction.xSpeed;
            components.get(0).y += direction.ySpeed;
        }
    }
    public void changeDirection(GameContainer g){
        if(g.getInput().isKeyDown(KeyEvent.VK_DOWN) && direction != Direction.UP && !wasDirectionChanged){
            direction = Direction.DOWN;
            wasDirectionChanged = true;
        }
        if(g.getInput().isKeyDown(KeyEvent.VK_UP) && direction != Direction.DOWN && !wasDirectionChanged){
            direction = Direction.UP;
            wasDirectionChanged = true;
        }
        if(g.getInput().isKeyDown(KeyEvent.VK_LEFT) && direction != Direction.RIGHT && !wasDirectionChanged){
            direction = Direction.LEFT;
            wasDirectionChanged = true;
        }
        if(g.getInput().isKeyDown(KeyEvent.VK_RIGHT) && direction != Direction.LEFT && !wasDirectionChanged){
            direction = Direction.RIGHT;
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
