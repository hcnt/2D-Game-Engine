package com.pttx.game_engine;

import java.awt.event.*;

public class Input implements KeyListener,MouseListener,MouseMotionListener,MouseWheelListener{
    private Game game;

    private boolean[] keys = new boolean[256];
    private boolean[] keysLast = new boolean[256];

    private boolean[] buttons = new boolean[256];
    private boolean[] buttonsLast = new boolean[256];

    private int mouseX,mouseY;
    private int scroll;

    public int getMouseX() {
        return mouseX;
    }
    public int getMouseY() {
        return mouseY;
    }

    public Input(Game game){
        this.game = game;
        mouseX = 0;
        mouseY = 0;
        scroll =0;

        game.getWindow().getCanvas().addKeyListener(this);
        game.getWindow().getCanvas().addMouseListener(this);
        game.getWindow().getCanvas().addMouseMotionListener(this);
        game.getWindow().getCanvas().addMouseWheelListener(this);
    }

    public void update(){
        for(int i=0;i<keys.length;i++){
            keysLast[i] = keys[i];
        }
        for(int i=0;i<buttons.length;i++){
            buttonsLast[i] = buttons[i];
        }

    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        keys[keyEvent.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        keys[keyEvent.getKeyCode()] = false;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        buttons[mouseEvent.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        buttons[mouseEvent.getButton()] = false;

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        mouseX = (int)((mouseEvent.getX())/game.getScale());
        mouseY = (int)((mouseEvent.getY())/game.getScale());
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {

    }
}