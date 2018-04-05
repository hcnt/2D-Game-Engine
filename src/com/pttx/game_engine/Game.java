package com.pttx.game_engine;

public class Game implements Runnable {
    private Thread thread;
    private boolean running = false;
    private final double FRAMES_PER_SECOND = 60D/1000000000D;

    private  int width = 320, height = 240;
    private float scale = 4f;
    private String title = "My game";

    private Window window;
    private Renderer renderer;
    private  Input input;

    public Game(){

    }

    public void start(){
        running = true;
        window = new Window(this);
        renderer = new Renderer(this);
        thread = new Thread(this);
        input = new Input(this);
        thread.run();
    }
    public void stop(){
        running = false;
    }
    public void render(){
        window.update();
    }
    public void update(){
        renderer.clear();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double numberOfFramesToRun = 0;

        double fps_counter = 0;
        double fps_lastTime = System.currentTimeMillis();

        while (running){
            double fps_currentTime = System.currentTimeMillis();
            long currentTime = System.nanoTime();
            numberOfFramesToRun += (currentTime - lastTime)* FRAMES_PER_SECOND;
            lastTime = currentTime;
            while (numberOfFramesToRun >= 1){
                numberOfFramesToRun--;
                update();
                render();

            }

            //fps counter
//            fps_counter++;
//            if(fps_currentTime - fps_lastTime >= 1000){
//                System.out.println("FPS: "+ fps_counter);
//                fps_counter=0;
//                fps_lastTime = fps_currentTime;
//            }
        }
    }


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
    public float getScale() {
        return scale;
    }
    public void setScale(float scale) {
        this.scale = scale;
    }
    public String getTitle() { return title;
    }
    public void setTitle(String title) { this.title = title;
    }
    public Window getWindow() {
        return window;
    }
}
