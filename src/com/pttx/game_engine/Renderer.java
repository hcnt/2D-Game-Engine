package com.pttx.game_engine;

import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;

public class Renderer {
    private int pixelWidth,pixelHeight;
    private int[] pixels;

    public Renderer(Game game){
        pixelWidth = game.getWidth();
        pixelHeight = game.getHeight();

        pixels = ((DataBufferInt)game.getWindow().getImage().getRaster().getDataBuffer()).getData();
    }

    public void clear() {
        for (int i =0;i<pixels.length;i++) {
            pixels[i] = i;
        }
    }
}
