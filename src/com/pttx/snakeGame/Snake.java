package com.pttx.snakeGame;

import com.pttx.game_engine.GameContainer;
import com.pttx.game_engine.Renderer;
import com.pttx.game_engine.gfx.Image;
import com.pttx.game_engine.gfx.ImageTile;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Snake {
    private ArrayList<SnakeComponent> components;
    private SnakeComponent head;
    private SnakeComponent tail;
    private ImageTile body = new ImageTile("/body.png",64,64);
    private Image headImage = new Image("/square.png");
    private long updateTimer = System.nanoTime();
    private int gridNumber;
    private boolean wasDirectionChanged = false;
    private ArrayList<Direction> directionsOfHead = new ArrayList<>();
    private ArrayList<Direction> directionsOfBody = new ArrayList<>();
    private int deltaTime = 166000000;

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
        components.add(new SnakeComponent(5,5));
        components.add(new SnakeComponent(5,4));
        components.add(new SnakeComponent(5,3));
        head = components.get(0);
        tail = components.get(components.size()-1);
        directionsOfHead.add(Direction.DOWN);
    }

    public ArrayList<SnakeComponent> getComponents() {
        return components;
    }
    public Component getHead() {
        return head;
    }
    public void setTail(SnakeComponent tail) {
        this.tail = tail;
    }
    public Component getTail() {
        return tail;
    }
    public ArrayList<Direction> getDirectionsOfBody() {
        return directionsOfBody;
    }
    public ArrayList<Direction> getDirectionsOfHead() {
        return directionsOfHead;
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
        int xSpeed;
        for(int i = 1; i<components.size()-1; i++){
            xSpeed = Math.abs(directionsOfBody.get(components.size()-2-i).xSpeed);
            components.get(i).drawComponentTile(r,body,componentSize,xSpeed,0);

        }
        xSpeed = Math.abs(directionsOfHead.get(0).xSpeed);
        head.drawComponentTile(r, body, componentSize,xSpeed,0);
        smoothRenderComponent(r,componentSize,headImage,tail.x,tail.y, directionsOfBody.get(0),System.nanoTime() - updateTimer,0);
        smoothRenderComponent(r,componentSize,headImage,head.x,head.y, directionsOfHead.get(0),System.nanoTime() - updateTimer,2);

    }
    public void smoothRenderComponent(Renderer r, int componentSize, Image image, int x, int y, Direction direction, long timePassed,int offset) {
        float xOffset = (x * componentSize + ((direction.xSpeed * (timePassed / (float)deltaTime))*componentSize) - direction.xSpeed*offset);
        float yOffset = (y * componentSize + ((direction.ySpeed * (timePassed / (float)deltaTime))*componentSize) - direction.ySpeed*offset);
        r.drawImage(image, (int)xOffset, (int)yOffset);
    }
}
