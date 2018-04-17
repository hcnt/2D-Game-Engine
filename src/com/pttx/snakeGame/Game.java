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
        snake.updateSnake(g);
        if (items.size() < 1){
            items.add(new ItemComponent((int)(Math.random()*gridNumber),(int)(Math.random()*gridNumber)));
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

    public static void main(String[] args) {
        Game g = new Game();
        GameContainer gc = new GameContainer(g);
        gc.start();
    }
}
