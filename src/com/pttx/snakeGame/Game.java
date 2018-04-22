package com.pttx.snakeGame;

import com.pttx.game_engine.AbstractGame;
import com.pttx.game_engine.GameContainer;
import com.pttx.game_engine.Renderer;
import com.pttx.game_engine.gfx.Image;

import java.util.ArrayList;

public class Game implements AbstractGame {
    private int gridNumber = 16;
    private Snake snake = new Snake(gridNumber);
    private ArrayList<ItemComponent> items = new ArrayList<>();
    private Image itemImage = new Image("/redSquare.png");

    public Game(){
    }

    @Override
    public void update(GameContainer g, double delta) {
        checkForCollision(g);
        snake.updateSnake(g);
        if (items.size() < 1){
            addItemComponent();
        }
    }

    @Override
    public void render(GameContainer g, Renderer r) {
        r.drawGrid(gridNumber, gridNumber,0xff436325);
        snake.drawSnake(r,g.getWidth());
        for(Component item : items){
            item.drawComponent(r,itemImage,g.getHeight()/gridNumber);
        }
    }

    public void checkForCollision(GameContainer g){
        for(int i = 0; i<items.size();i++){
            if (items.get(i).x == snake.getHead().x + snake.getDirectionsOfHead().get(0).xSpeed
                    && items.get(i).y == snake.getHead().y + snake.getDirectionsOfHead().get(0).ySpeed){
                SnakeComponent newComponent = new SnakeComponent(snake.getComponents().get(snake.getComponents().size()-1).x - snake.getDirectionsOfBody().get(0).xSpeed,
                                                       snake.getComponents().get(snake.getComponents().size()-1).y - snake.getDirectionsOfBody().get(0).ySpeed);
                snake.setTail(newComponent);
                items.remove(i);
                snake.getComponents().add(newComponent);
            }
        }
        for(int i = 2; i<snake.getComponents().size();i++){
            if(snake.getHead().x == snake.getComponents().get(i).x && snake.getHead().y == snake.getComponents().get(i).y){
                g.stop();
            }
        }
        if(snake.getHead().x < 0 || snake.getHead().y < 0 || snake.getHead().x > gridNumber-1 || snake.getHead().y > gridNumber-1){
           g.stop();
        }

    }
    private boolean isItemValidToAdd(int x,int y){
        for (int i = 0; i< snake.getComponents().size(); i++){
            if(snake.getComponents().get(i).x == x && snake.getComponents().get(i).y == y){
               return false;
            }
        }
        return true;
    }

    public void addItemComponent(){
        int x = (int)(Math.random()*gridNumber);
        int y = (int)(Math.random()*gridNumber);

        while (!isItemValidToAdd(x,y)){
            x = (int)(Math.random()*gridNumber);
            y = (int)(Math.random()*gridNumber);
        }
        items.add(new ItemComponent(x,y));
    }

    public static void main(String[] args) {
        Game g = new Game();
        GameContainer gc = new GameContainer(g);
        gc.start();
    }
}
