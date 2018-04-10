package com.pttx.game_engine;

import com.pttx.game_engine.gfx.Image;

import java.awt.image.DataBufferInt;

public class Renderer {
    private int pixelsWidth, pixelsHeight;
    private int[] pixels;

    public Renderer(GameContainer gameContainer){
        pixelsWidth = gameContainer.getWidth();
        pixelsHeight = gameContainer.getHeight();

        pixels = ((DataBufferInt) gameContainer.getWindow().getImage().getRaster().getDataBuffer()).getData();
    }

    public void clear() {
        for (int i =0;i<pixels.length;i++) {
            pixels[i] = 0;
        }
    }

    public void renderPixel (int x,int y, int value){
        if((x <= 0 || x >= pixelsWidth || y<=0 || y>= pixelsHeight) || ((value >> 24) == 0x00)){
            return;
        }
        pixels[x + y * pixelsWidth] = value;

    }
    public void drawImage(Image image, int offsetX, int offsetY){
        for(int y=0;y<image.getHeight();y++){
            for(int x = 0; x<image.getWidth(); x++){
                renderPixel(x+ offsetX,y + offsetY,image.getPixels()[x+y*image.getWidth()]);
                //renderPixel(0,0,0xffffffff);
            }
        }
    }
}