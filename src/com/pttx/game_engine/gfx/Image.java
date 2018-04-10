package com.pttx.game_engine.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

public class Image {
    private int width;
    private int height;
    private int[] pixels;
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int[] getPixels() {
        return pixels;
    }
    public void setPixels(int[] pixels) {
        this.pixels = pixels;
    }

    public Image(String path){
        BufferedImage image = null;
        try {
            image = ImageIO.read(Image.class.getResourceAsStream(path));
        } catch (IOException ex){
            ex.printStackTrace();
        }
        width = image.getWidth();
        height = image.getHeight();
        pixels = image.getRGB(0,0,width,height,null,0,width);

        image.flush();
    }
}
