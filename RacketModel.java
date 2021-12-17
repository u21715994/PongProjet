package com.example.pong;

public class RacketModel extends Element{
    private float width;
    private float height;

    public RacketModel(float x, float y, float width, float height){
        super(x,y);
        this.width = width;
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
