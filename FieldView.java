package com.example.pong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class FieldView extends View {
    private RacketController racketController1;
    private RacketController racketController2;
    private BallController ballController;
    private int scoreJoueur1 = 0;
    private int scoreJoueur2 = 0;
    private static int WIDTH;
    private static int HEIGHT;
    private Paint paint;

    public FieldView(Context context){
        super(context);
        WIDTH = context.getResources().getDisplayMetrics().widthPixels;
        HEIGHT = context.getResources().getDisplayMetrics().heightPixels;
        racketController1 = new RacketController(new RacketModel(150,HEIGHT/7,175,HEIGHT/4));
        racketController2 = new RacketController(new RacketModel(WIDTH-175,HEIGHT/7,WIDTH-150,HEIGHT/4));
        ballController = new BallController(new BallModel(WIDTH/2, HEIGHT/2, 10));
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        setBackgroundColor(Color.BLACK);
    }

    public void touchBall(RacketController racketController){
        if(chevauche(racketController,ballController)) {
            ballController.changeDirection();
            if(racketController.getOrientation() != null) {
                ballController.setOrientation(racketController.getOrientation());
                ballController.moveBallOrientation();
            }
            ballController.moveBall();
            ballController.increaseSpeed();
        }
        invalidate();
    }

    public void moveBall(Canvas canvas){
        canvas.drawCircle(ballController.getX(),ballController.getY(),ballController.getRadius(),paint);
        ballController.moveBall();
        invalidate();
    }

    public void ballTouchWall(Canvas canvas){
        if(ballController.getY() <= 0 && ballController.getOrientation() == Element.Orientation.UP){
            ballController.setOrientation(Element.Orientation.DOWN);
            moveBall(canvas);
        }else if(ballController.getY() >= HEIGHT-HEIGHT/5 && ballController.getOrientation() == Element.Orientation.DOWN){
            ballController.setOrientation(Element.Orientation.UP);
            moveBall(canvas);
        }
    }

    public void moveRackets(float x, float y){
        if(x < getResources().getDisplayMetrics().widthPixels/2) {
            racketController1.moveRacket(y);
            touchBall(racketController1);
        }
        if(x > getResources().getDisplayMetrics().widthPixels/2) {
            racketController2.moveRacket(y);
            touchBall(racketController2);
        }
    }

    public void rebootOrientation(){
        racketController1.setOrientaion(null);
        racketController2.setOrientaion(null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.WHITE);
        canvas.drawRect(racketController1.getX(),racketController1.getY(),racketController1.getWidth(),racketController1.getHeight(),paint);
        canvas.drawRect(racketController2.getX(), racketController2.getY(), racketController2.getWidth(), racketController2.getHeight(),paint);
        moveBall(canvas);
        ballTouchWall(canvas);
        if(score()){
            Toast.makeText(getContext().getApplicationContext(),String.valueOf(scoreJoueur1)+" - "+String.valueOf(scoreJoueur2),Toast.LENGTH_SHORT).show();
            ballController.rebootPosition(getContext().getResources().getDisplayMetrics().widthPixels/2, getContext().getResources().getDisplayMetrics().heightPixels/2);
            ballController.changeDirection();
        }
        touchBall(racketController1);
        touchBall(racketController2);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch(event.getAction()){
            case MotionEvent.ACTION_UP:
                rebootOrientation();
                break;
            case MotionEvent.ACTION_MOVE:
                moveRackets(event.getX(), event.getY());
                break;
            default:
                break;
        }
        invalidate();
        return true;
    }

    public boolean chevauche(RacketController racketController, BallController ballController){
        for(float y = racketController.getY(); y < racketController.getHeight(); y++) {
            if (Math.sqrt(Math.pow(racketController.getWidth() - ballController.getX(), 2) + Math.pow(y - ballController.getY(), 2)) >= 0 &&
                    Math.sqrt(Math.pow(racketController.getWidth() - ballController.getX(), 2) + Math.pow(y - ballController.getY(), 2)) <= 10)
                return true;
            if (Math.sqrt(Math.pow(racketController.getX() - ballController.getX(), 2) + Math.pow(y - ballController.getY(), 2)) >= 0 &&
                    Math.sqrt(Math.pow(racketController.getX() - ballController.getX(), 2) + Math.pow(y - ballController.getY(), 2)) <= 10)
                return true;
        }
        return false;
    }

    public boolean score(){
        if(ballController.getX() < racketController1.getX()-HEIGHT/5) {
            scoreJoueur2++;
            return true;
        }else if(ballController.getX() > racketController2.getX()+HEIGHT/5){
            scoreJoueur1++;
            return true;
        }
        return false;
    }

    public boolean game(){
        if(scoreJoueur1 == 3 || scoreJoueur2 == 3) {
            Toast.makeText(getContext().getApplicationContext(),"Partie terminée",Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
}
