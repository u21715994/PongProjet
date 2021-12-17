package com.example.pong;

public class BallController {
    private BallModel ball;

    public BallController(BallModel ball){
        this.ball = ball;
    }

    public float getX(){
        return ball.getX();
    }

    public float getY(){
        return ball.getY();
    }

    public float getRadius(){
        return ball.getRadius();
    }

    public Element.Orientation getOrientation(){
        return ball.getOrientation();
    }

    public void setOrientation(Element.Orientation orientation){
        ball.setOrientation(orientation);
    }

    public void increaseSpeed(){
        ball.setSpeed(Float.valueOf(ball.getSpeed()+1));
    }

    public void rebootPosition(float x, float y){
        ball.setX(x);
        ball.setY(y);
        ball.setSpeed(1);
        ball.setOrientation(null);
    }

    public void changeDirection(){
        if(ball.getDirection().equals(BallModel.Direction.RIGHT))
            ball.setDirection(BallModel.Direction.LEFT);
        else
            ball.setDirection(BallModel.Direction.RIGHT);
    }

    public void moveBall(){
        if(ball.getOrientation() == null) {
            if (ball.getDirection().equals(BallModel.Direction.RIGHT))
                ball.setX(ball.getX() + 1 * ball.getSpeed());
            else
                ball.setX(ball.getX() - 1 * ball.getSpeed());
        }else
            moveBallOrientation();
    }

    public void moveBallOrientation(){
        switch (ball.getOrientation()){
            case UP:
                if(ball.getDirection().equals(BallModel.Direction.RIGHT)){
                    ball.setX(ball.getX() + 1 * ball.getSpeed());
                    ball.setY(ball.getY() - 1 * ball.getSpeed());
                }else {
                    ball.setX(ball.getX() - 1 * ball.getSpeed());
                    ball.setY(ball.getY() - 1 * ball.getSpeed());
                }
                break;
            case DOWN:
                if(ball.getDirection().equals(BallModel.Direction.RIGHT)){
                    ball.setX(ball.getX() + 1 * ball.getSpeed() );
                    ball.setY(ball.getY() + 1 * ball.getSpeed());
                }else {
                    ball.setX(ball.getX() - 1 * ball.getSpeed());
                    ball.setY(ball.getY() + 1 * ball.getSpeed());
                }
                break;
            default:
                break;
        }
    }
}
