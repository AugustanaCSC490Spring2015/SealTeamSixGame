package edu.augustana.csc490.circleofdeath;

/**
 * Created by abdulmerhi11 on 5/11/2015.
 * This is an interface to allow us use the animation in other activities and not only in the circle animation and the MainActivity.
 * We ended up not using this class because we change our minds about the title animation and we just displayed a normal text.
 * It is good to keep it for the future in case we want to reuse it.
 */
public interface AnimationDoneListener {

    public void animationDone();
}
