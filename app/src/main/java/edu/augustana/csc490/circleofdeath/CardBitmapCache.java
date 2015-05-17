package edu.augustana.csc490.circleofdeath;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import edu.augustana.csc490.circleofdeath.Card;

/**
 * Created by abdulmerhi11 on 5/8/2015.
 * It creates a hashmap of strings and bitmaps to match the path
 * of the card to its bitmap after being compressed into the desired size from the assets
 */
public class CardBitmapCache {
    AssetManager assets;
    Map<String, Bitmap> mapping = new HashMap<>();

    public CardBitmapCache(AssetManager assets){
        this.assets = assets;
    }

    /*
    This code is helped by: http://stackoverflow.com/questions/5176441/drawable-image-on-a-canvas
    It creates a HashMap of card bitmaps and their corresponding uri path value to check whether the
    bitmap exists or not for its corresponding uri card value. This code uses memory as it saves the
    images of the cards in a HashMap, but saves a lot of time instead of decoding the uri and changing
    the size of the bitmap for every card being stored
     */

    public Bitmap getBitmap(Card card, Canvas canvas) {

        Bitmap bitmap = null;
        if (mapping.containsKey(card.getUri())){
            return mapping.get(card.getUri());
        } else {
            try {
                InputStream istr = assets.open("cards/"+card.getUri());
                double cardWidthD = 75*(Math.round(canvas.getWidth()/720.0));
                int cardWidth = (int) cardWidthD;
                double cardHeightD = 100*(Math.round(canvas.getHeight()/1280.0));
                int cardHeight = (int) cardHeightD;
                bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeStream(istr),cardWidth, cardHeight, false);
                mapping.put(card.getUri(), bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bitmap;
    }

}
