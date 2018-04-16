package com.pttx.snakeGame;

import com.pttx.game_engine.AbstractGame;
import com.pttx.game_engine.GameContainer;
import com.pttx.game_engine.Renderer;

public class Game implements AbstractGame {
    private int GridNumber;
    private Snake snake;

    public Game(){
        GridNumber = 16;
        snake = new Snake(GridNumber);
    }

    @Override
    public void update(GameContainer g, double delta) {

    }

    @Override
    public void render(GameContainer g, Renderer r) {
        r.drawGrid(GridNumber,GridNumber,0xff436325);
        snake.drawSnake(r,g.getWidth());
    }

    public static void main(String[] args) {
        Game g = new Game();
        GameContainer gc = new GameContainer(g);
        gc.start();
    }
}
