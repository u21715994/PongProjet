package com.example.pong;

public class RacketController {
    private RacketModel racketModel;

    public RacketController(RacketModel racketModel){
        this.racketModel = racketModel;
    }

    public float getX(){
        return racketModel.getX();
    }

    public float getY(){
        return racketModel.getY();
    }

    public float getWidth(){
        return racketModel.getWidth();
    }

    public float getHeight(){
        return racketModel.getHeight();
    }

    public RacketModel.Orientation getOrientation(){
        return racketModel.getOrientation();
    }

    public void setOrientaion(RacketModel.Orientation orientation){
        racketModel.setOrientation(orientation);
    }

    public void moveRacket(float y){
        float Y = racketModel.getY();
        float height = racketModel.getHeight();
        if(y < racketModel.getY()) {
            racketModel.setY(Y - 5);
            racketModel.setHeight(height - 5);
            racketModel.setOrientation(RacketModel.Orientation.UP);
        }else if(y > racketModel.getY()) {
            racketModel.setY(Y + 5);
            racketModel.setHeight(height + 5);
            racketModel.setOrientation(RacketModel.Orientation.DOWN);
        }
    }
}
