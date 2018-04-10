package com.pttx.game_engine;

import java.awt.image.DataBufferInt;

public class Renderer {
    private int pixelWidth,pixelHeight;
    private int[] pixels;

    public Renderer(GameContainer gameContainer){
        pixelWidth = gameContainer.getWidth();
        pixelHeight = gameContainer.getHeight();

        pixels = ((DataBufferInt) gameContainer.getWindow().getImage().getRaster().getDataBuffer()).getData();
    }

    public void clear() {
        for (int i =0;i<pixels.length;i++) {
            pixels[i] = 0;
        }
    }
}
