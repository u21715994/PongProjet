package com.example.pong;

import java.util.Random;

public class BallModel extends Element{
    private final float radius;
    private float speed;
    public enum Direction{
        LEFT,
        RIGHT,
    };
    private Direction direction;

    public BallModel(float x, float y, float radius){
        super(x,y);
        this.radius = radius;
        speed = 1;
        if(new Random().nextInt(2) == 0)
            direction = Direction.LEFT;
        else
            direction = Direction.RIGHT;
    }

    public float getRadius(){
        return radius;
    }

    public float getSpeed() {
        return speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
