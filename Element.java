package com.example.pong;

public abstract class Element {
    private float x;
    private float y;
    public enum Orientation{
        UP,
        DOWN,
    };
    private Orientation orientation;

    public Element(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

}
