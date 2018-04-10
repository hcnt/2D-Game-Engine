package com.pttx.game_engine;

import java.awt.event.*;

public class Input implements KeyListener,MouseListener,MouseMotionListener,MouseWheelListener{
    private GameContainer gameContainer;

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
    public int getScroll() {
        return scroll;
    }

    public  boolean isKeyDown(int keyCode){
        return keys[keyCode];
    }
    public  boolean isKeyReleased(int keyCode){
        return !keys[keyCode] && keysLast[keyCode];
    }
    public  boolean isKeyPressed(int keyCode){
        return keys[keyCode] && !keysLast[keyCode];
    }
    public  boolean isButtonDown(int button){
        return buttons[button];
    }
    public  boolean isButtonReleased(int button){
        return !buttons[button] && buttonsLast[button];
    }
    public  boolean isButtonPressed(int button){
        return buttons[button] && !buttonsLast[button];
    }

    public Input(GameContainer gameContainer){
        this.gameContainer = gameContainer;
        mouseX = 0;
        mouseY = 0;
        scroll =0;

        gameContainer.getWindow().getCanvas().addKeyListener(this);
        gameContainer.getWindow().getCanvas().addMouseListener(this);
        gameContainer.getWindow().getCanvas().addMouseMotionListener(this);
        gameContainer.getWindow().getCanvas().addMouseWheelListener(this);
    }

    public void update(){
        scroll = 0;

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
        mouseX = (int)((mouseEvent.getX())/ gameContainer.getScale());
        mouseY = (int)((mouseEvent.getY())/ gameContainer.getScale());
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        mouseX = (int)((mouseEvent.getX())/ gameContainer.getScale());
        mouseY = (int)((mouseEvent.getY())/ gameContainer.getScale());
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
        scroll = mouseWheelEvent.getWheelRotation();
    }
}