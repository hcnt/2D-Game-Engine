package com.pttx.snakeGame;

import com.pttx.game_engine.GameContainer;
import com.pttx.game_engine.Renderer;
import com.pttx.game_engine.gfx.Image;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Snake {
    private ArrayList<Component> components;
    private Component head;
    private Component tail;
    private Image body = new Image("/square.png");
    private Image headImage = new Image("/redSquare.png");
    private long updateTimer = System.nanoTime();
    private int gridNumber;
    private boolean wasDirectionChanged = false;
    private ArrayList<Direction> directionsOfHead = new ArrayList<>();
    private ArrayList<Direction> directionsOfBody = new ArrayList<>();
    private int deltaTime = 180000000;

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
        tail = components.get(components.size()-1);
        directionsOfHead.add(Direction.DOWN);
    }

    public ArrayList<Component> getComponents() {
        return components;
    }
    public Component getHead() {
        return head;
    }
    public void setTail(Component tail) {
        this.tail = tail;
    }
    public Component getTail() {
        return tail;
    }
    public ArrayList<Direction> getDirectionsOfBody() {
        return directionsOfBody;
    }

    public void updateSnake(GameContainer g){
        changeDirection(g);
        if (System.nanoTime() - updateTimer > deltaTime) {
            updateTimer = System.nanoTime();
            for (int i = components.size()-1; i>=1; i--) {
                components.get(i).x = components.get(i-1).x;
                components.get(i).y = components.get(i-1).y;
            }
            components.get(0).x += directionsOfHead.get(0).xSpeed;
            components.get(0).y += directionsOfHead.get(0).ySpeed;

            directionsOfBody.add(directionsOfHead.get(0));
            if(directionsOfBody.size() > components.size()-1){
                directionsOfBody.remove(0);
            }
            if(directionsOfHead.size() > 1) {
                directionsOfHead.remove(0);
            }
            //System.out.println(directionsOfBody.get(0));
        }
    }
    public void changeDirection(GameContainer g){
        if(g.getInput().isKeyDown(KeyEvent.VK_DOWN) && directionsOfHead.get(0) != Direction.DOWN && directionsOfHead.get(0) != Direction.UP && !wasDirectionChanged){
            directionsOfHead.add(Direction.DOWN);
        }
        if(g.getInput().isKeyDown(KeyEvent.VK_UP) && directionsOfHead.get(0) != Direction.DOWN  && directionsOfHead.get(0) != Direction.UP && !wasDirectionChanged ){
            directionsOfHead.add(Direction.UP);
        }
        if(g.getInput().isKeyDown(KeyEvent.VK_LEFT)  && directionsOfHead.get(0) != Direction.LEFT && directionsOfHead.get(0) != Direction.RIGHT && !wasDirectionChanged){
            directionsOfHead.add(Direction.LEFT);
        }
        if(g.getInput().isKeyDown(KeyEvent.VK_RIGHT) &&  directionsOfHead.get(0) != Direction.RIGHT && directionsOfHead.get(0) != Direction.LEFT && !wasDirectionChanged){
            directionsOfHead.add(Direction.RIGHT);
        }
        if(directionsOfHead.size() > 2){
            directionsOfHead.remove(1);
        }
    }
    public void drawSnake(Renderer r,int windowWidth){
        int componentSize = windowWidth / gridNumber;
        for(int i = 0; i<components.size()-1; i++){
            components.get(i).drawComponent(r,body,componentSize);
        }
        smoothRenderComponent(r,componentSize,body,tail.x,tail.y, directionsOfBody.get(0),System.nanoTime() - updateTimer,0);
        smoothRenderComponent(r,componentSize,body,head.x,head.y, directionsOfHead.get(0),System.nanoTime() - updateTimer,2);

    }
    public void smoothRenderComponent(Renderer r, int componentSize, Image image, int x, int y, Direction direction, long timePassed,int offset) {
        float xOffset = (x * componentSize + ((direction.xSpeed * (timePassed / (float)deltaTime))*componentSize) - direction.xSpeed*offset);
        float yOffset = (y * componentSize + ((direction.ySpeed * (timePassed / (float)deltaTime))*componentSize) - direction.ySpeed*offset);
        r.drawImage(image, (int)xOffset, (int)yOffset);
    }
}
