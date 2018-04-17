package com.pttx.game_engine;

public class GameContainer implements Runnable {
    private Thread thread;
    private boolean running = false;
    private final double FRAMES_PER_SECOND = 60D/1000000000D;

    private  int width = 1024, height = 1024;
    private float scale = 1f;
    private String title = "My game";

    private Window window;
    private Renderer renderer;
    private Input input;
    private AbstractGame game;

    public GameContainer(AbstractGame game){
        this.game = game;
    }

    public void start(){
        running = true;
        window = new Window(this);
        renderer = new Renderer(this);
        input = new Input(this);
        thread = new Thread(this);
        thread.run();
    }
    public void stop(){
        running = false;
    }
    public void render(){
        renderer.clear();
        game.render(this,renderer);
        window.update();

    }
    public  void update(){
        game.update(this,FRAMES_PER_SECOND); //u sure u want to pass this?
        input.update();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double numberOfFramesToRun = 0;

        double fps_counter = 0;
        double fpsa_lastTime = System.currentTimeMillis();

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
    public Window getWindow() {
        return window;
    }
    public Input getInput() {
        return input;
    }
}
