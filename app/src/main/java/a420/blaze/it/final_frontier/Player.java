package a420.blaze.it.final_frontier;

import android.graphics.Bitmap;
import android.graphics.Canvas;


/**
 * Created by Conor on 02/02/2016.
 */
public class Player extends GameObject {
    private Bitmap spriteSheet;
    private int score;
    private double dxa;
    private boolean up;
    private boolean playing;
    private Animation animation = new Animation();
    private long startTime;
    private static final int TILE_SIZE = 32;

    public Player(Bitmap res, int w, int h, int numFrames) {
        x = 100;
        y = CanvasView.HEIGHT / 2;
        dy = 0;
        score = 0;
        height = h;
        width = w;

        Bitmap[] image = new Bitmap[numFrames];
        spriteSheet = res;

        for (int i = 0; i < image.length; i++) {
            image[i] = Bitmap.createBitmap(spriteSheet, i * width, 0, width, height);

        }

        animation.setFrames(image);
        animation.setDelay(10);
        startTime = System.nanoTime();


    }

    public void setUp(Boolean b) {
        up = b;
    }

    public void update() {
        long elapsed = (System.nanoTime() - startTime) / 1000000;
        animation.update();

        if(up) {
            dy -= 1;
        } else {
            dy += 1;
        }

        if (dy > 14) {
            dy = 14;
        }
        if (dy < -14) {
            dy = -14;
        }

        y += dy*2;
        dy=0;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(animation.getImage(), x, y, null);
    }

    public int getScore() {
        return score;
    }

    public boolean getPlaying() {
        return playing;
    }

    public void setPlaying(boolean b) {
        playing = b;
    }

    public void resetDXA() {
        dxa = 0;
    }

    public void resetScore() {
        score = 0;
    }


}
