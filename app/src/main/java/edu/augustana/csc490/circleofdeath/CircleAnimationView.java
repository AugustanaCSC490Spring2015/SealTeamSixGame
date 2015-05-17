package edu.augustana.csc490.circleofdeath;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by abdulmerhi11 on 4/27/2015.
 * This class creates the animation of the deck of cards in the main activity
 */
public class CircleAnimationView extends View {

    public static final String TAG = "CircleAnimationView";
    public Paint backgroundPaint;
    public int animCardNum;
    double radius;
    double xCard;
    double yCard;
    public CountDownTimer timer;
    public ArrayList<Card> cardsDisplayed;
    public Deck deck;
    private CardBitmapCache cache;
    private AnimationDoneListener animationDoneListener;


    // it is a constructor to the animation class and initializes the variables and data fields.
    public CircleAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);

        AssetManager assets = context.getAssets();
        cache = new CardBitmapCache(assets);

        deck = GameFragment.returnNewDeck(context);


        cardsDisplayed = new ArrayList<>();
        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.BLACK);

        animCardNum= 0;

    }


    /*
    This method connects the animationDoneListener variable to the mainActivity for the title animation
     */
    public void setAnimationDoneListener(AnimationDoneListener mainActivity) {
        this.animationDoneListener = mainActivity;
    }


    /*
    This method sets the value of the radius based on the new height and new width of canvas
    */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        radius = w/2.0 - 100*(w/720.0);


        startTimer();

    }




/*
This method handles the animation of the deck cards in a circle. It uses a CountDownTimer object with intervals
equals half a second and a total time = 52*intervals. It changes the x and y coordinates of cards drawn from deck and
stores the drawn deck in an ArrayList cardsDisplayed.
 */
    private void startTimer() {
        int delayMS = 200;
        timer = new CountDownTimer(delayMS*52, delayMS) {

            @Override
            public void onTick(long leftTimeInMilliseconds) {
                long seconds = leftTimeInMilliseconds / 1000;
                double angleCard = ((double) animCardNum/52.0)*(2*Math.PI);
                xCard = getWidth()/2 + (radius*Math.cos(angleCard)) - 50*(getWidth()/720.0) ;
                yCard = (double) getHeight()/2.0 - (radius*Math.sin(angleCard)) - 40*(getHeight()/1280.0);
                Card card = deck.getNextCard();
                card.setCoordinate(xCard, yCard);
                animCardNum++;
                cardsDisplayed.add(card);
                invalidate();
            }
            @Override
            public void onFinish() {
               animationDoneListener.animationDone();

            }
        };

        timer.start();

    }

/*
This methods handles the drawing of the canvas and the cards in the cardsDisplayed ArrayList and is
called every time the invalidate() method in the startTimer() method is called.
 */
    @Override
    public void onDraw(Canvas canvas) {

        if (canvas != null){

        canvas.drawRect(0,0, canvas.getWidth(), canvas.getHeight(),backgroundPaint);

        Paint p = new Paint();
        p.setColor(Color.RED);
        for (int i = 0; i < cardsDisplayed.size(); i++){
                canvas.drawBitmap(cache.getBitmap(cardsDisplayed.get(i), canvas),(float) cardsDisplayed.get(i).getX(), (float) cardsDisplayed.get(i).getY(), p);



        }







    }

    }


}
