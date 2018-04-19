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
    private ArrayList<Direction> directions = new ArrayList<>();

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
        components.add(new Component(5,4));
        components.add(new Component(5,3));
        head = components.get(0);
        directions.add(Direction.DOWN);
    }

    public ArrayList<Component> getComponents() {
        return components;
    }
    public Component getHead() {
        return head;
    }

    public void updateSnake(GameContainer g){
        changeDirection(g);
        if (System.nanoTime() - updateTimer > 180000000) {
            updateTimer = System.nanoTime();
            System.out.println(components.size());
            for (int i = components.size()-1; i>=1; i--) {
                components.get(i).x = components.get(i-1).x;
                components.get(i).y = components.get(i-1).y;
            }
            components.get(0).x += directions.get(0).xSpeed;
            components.get(0).y += directions.get(0).ySpeed;
            if(directions.size() > 1) {
                directions.remove(0);
            }
        }
    }
    public void changeDirection(GameContainer g){
        if(g.getInput().isKeyDown(KeyEvent.VK_DOWN) && directions.get(0) != Direction.DOWN && directions.get(0) != Direction.UP && !wasDirectionChanged){
            directions.add(Direction.DOWN);
        }
        if(g.getInput().isKeyDown(KeyEvent.VK_UP) && directions.get(0) != Direction.DOWN  && directions.get(0) != Direction.UP && !wasDirectionChanged ){
            directions.add(Direction.UP);
        }
        if(g.getInput().isKeyDown(KeyEvent.VK_LEFT)  && directions.get(0) != Direction.LEFT && directions.get(0) != Direction.RIGHT && !wasDirectionChanged){
            directions.add(Direction.LEFT);
        }
        if(g.getInput().isKeyDown(KeyEvent.VK_RIGHT) &&  directions.get(0) != Direction.RIGHT && directions.get(0) != Direction.LEFT && !wasDirectionChanged){
            directions.add(Direction.RIGHT);
        }
        if(directions.size() > 2){
            directions.remove(1);
        }
    }
    public void drawSnake(Renderer r,int windowWidth){
        int componentSize = windowWidth / gridNumber;
        for(int i = 0; i<components.size(); i++){
            components.get(i).drawComponent(r,body,componentSize);
        }
    }
}
