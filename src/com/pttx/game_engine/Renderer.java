package com.pttx.game_engine;

import com.pttx.game_engine.gfx.Font;
import com.pttx.game_engine.gfx.Image;
import com.pttx.game_engine.gfx.ImageTile;

import java.awt.*;
import java.awt.image.DataBufferInt;

public class Renderer {
    private int pixelsWidth, pixelsHeight;
    private int[] pixels;

    private Font font = Font.STANDARD;

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

    public void setPixel(int x, int y, int value){
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
                setPixel(x+ offsetX,y + offsetY,image.getPixels()[x+y*image.getWidth()]);
                //setPixel(0,0,0xffffffff);
            }
        }
    }
    public void drawText(String text, int offsetX, int offesetY, int color){
        Image fontImage = font.getFontImage();
        text = text.toUpperCase();
        int offset = 0;

        for(int i = 0; i<text.length(); i++){
            int unicode = text.codePointAt(i)-32;
            for (int y = 0; y<fontImage.getHeight();y++ ){
                for (int x=0; x<font.getWidths()[unicode];x++){

                    if(fontImage.getPixels()[x+font.getOffsets()[unicode]+y*fontImage.getWidth()] == 0xffffffff){
                        setPixel(x+ offsetX+offset,y+offesetY,color);
                    }
                }
            }
            offset+= font.getWidths()[unicode];
        }
    }

    public void drawGrid(int numberOfXTiles, int numberOfYTiles, int color){
        int xTileWidth = pixelsWidth / numberOfXTiles;
        int yTileWidth = pixelsHeight / numberOfYTiles;
        for(int i =0; i<numberOfYTiles; i++){
            for(int j =0; j< pixelsWidth;j++){
                setPixel(j,i*yTileWidth,color);
            }
        }
        for(int i =0; i<numberOfXTiles; i++){
            for(int j =0; j< pixelsHeight;j++){
                setPixel(i*xTileWidth,j,color);
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
                setPixel(x + offsetX,
                            y + offsetY,
                            imageTile.getPixels()[
                                    (x + tileX * imageTile.getTileWidth())
                                  + (y + tileY * imageTile.getTileHeight()) * imageTile.getWidth()]);
            }
        }
    }
}