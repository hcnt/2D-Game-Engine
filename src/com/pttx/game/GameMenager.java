package com.pttx.game;

import com.pttx.game_engine.AbstractGame;
import com.pttx.game_engine.GameContainer;
import com.pttx.game_engine.Renderer;
import com.pttx.game_engine.gfx.Image;
import com.pttx.game_engine.gfx.ImageTile;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameMenager implements AbstractGame {
    private Image image;
    private ImageTile animation;
    private double animationCounter = 0;

    public GameMenager(){
       // image = new Image("/test.png");
        animation = new ImageTile("/xD.png",64,64);
    }

    @Override
    public void update(GameContainer gameContainer, double delta) {
        if (gameContainer.getInput().isKeyReleased(KeyEvent.VK_A)) {
            System.out.println("space");
        }

        if (animationCounter > 8) animationCounter = 0;
        animationCounter += 1;
    }
    @Override
    public void render(GameContainer g, Renderer r) {
        //r.drawImage(image,g.getInput().getMouseX()-10,g.getInput().getMouseY());
        r.drawGrid(16,16,0xff436325);
        r.drawTimageTile(animation,g.getInput().getMouseX(),g.getInput().getMouseY(),(int)animationCounter,0);
    }

    public static void main(String[] args) {
        GameMenager gm = new GameMenager();
        GameContainer game = new GameContainer(gm);
        game.start();
    }
}
