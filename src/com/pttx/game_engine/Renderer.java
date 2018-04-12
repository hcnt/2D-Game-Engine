package com.pttx.game_engine;

import com.pttx.game_engine.gfx.Image;
import com.pttx.game_engine.gfx.ImageTile;

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
            pixels[i] = 0xffffffff;
        }
    }

    public void renderPixel (int x,int y, int value){
        if((x <= 0 || x >= pixelsWidth || y<=0 || y>= pixelsHeight) || ((value >> 24) == 0x00)){
            return;
        }
        pixels[x + y * pixelsWidth] = value;

    }
    public void drawImage(Image image, int offsetX, int offsetY){

        int renderingX = 0;
        int renderingY = 0;
        int renderingWidth = image.getWidth();
        int renderingHeight = image.getHeight();

        if(renderingWidth + offsetX > pixelsWidth)
            renderingWidth = pixelsWidth - offsetX;

        if(renderingHeight + offsetY > pixelsHeight)
            renderingHeight = pixelsHeight - offsetY;

        if (offsetX < 0) renderingX -= offsetX;
        if (offsetY < 0) renderingY -= offsetY;


        for(int y=renderingY;y<renderingHeight;y++){
            for(int x = renderingX; x<renderingWidth; x++){
                renderPixel(x+ offsetX,y + offsetY,image.getPixels()[x+y*image.getWidth()]);
                //renderPixel(0,0,0xffffffff);
            }
        }
    }

    public void drawTimageTile(ImageTile imageTile, int offsetX, int offsetY,int tileX,int tileY){
        int renderingX = 0;
        int renderingY = 0;
        int renderingWidth = imageTile.getTileWidth();
        int renderingHeight = imageTile.getTileWidth();

        if(renderingWidth + offsetX > pixelsWidth)
            renderingWidth = pixelsWidth - offsetX;

        if(renderingHeight + offsetY > pixelsHeight)
            renderingHeight = pixelsHeight - offsetY;

        if (offsetX < 0) renderingX -= offsetX;
        if (offsetY < 0) renderingY -= offsetY;


        for(int y=renderingY;y<renderingHeight;y++){
            for(int x = renderingX; x<renderingWidth; x++){
                renderPixel(x + offsetX,
                            y + offsetY,
                            imageTile.getPixels()[
                                    (x + tileX * imageTile.getTileWidth())
                                  + (y + tileY * imageTile.getTileHeight()) * imageTile.getWidth()]);
            }
        }
    }
}