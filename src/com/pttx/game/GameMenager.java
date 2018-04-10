package com.pttx.game;

import com.pttx.game_engine.AbstractGame;
import com.pttx.game_engine.GameContainer;
import com.pttx.game_engine.Renderer;

import java.awt.event.KeyEvent;

public class GameMenager implements AbstractGame {
    public GameMenager(){

    }

    @Override
    public void update(GameContainer gameContainer, double delta) {
       // if (gameContainer.getInput().isKeyReleased(KeyEvent.VK_A)){
         //   System.out.println("space");
       // }
    }
    @Override
    public void render(GameContainer g, Renderer r) {

    }

    public static void main(String[] args) {
        GameMenager gm = new GameMenager();
        GameContainer game = new GameContainer(gm);
        game.start();

    }
}
