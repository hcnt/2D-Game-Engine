package com.pttx.snakeGame;

public class SnakeComponent extends Component {

    private boolean isOnTurn;
    SnakeComponent(int x,int y){
        super(x,y);
    }

    public void setOnTurn(boolean onTurn) {
        isOnTurn = onTurn;
    }
    public boolean isOnTurn() {
        return isOnTurn;
    }
}
