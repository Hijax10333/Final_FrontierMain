package a420.blaze.it.final_frontier;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Conor on 09/12/2015.
 */
public class Background {

    public Bitmap image;
    public int x, y, dx;


    public Background(Bitmap res) {
        image = res;
        dx = CanvasView.MOVESPEED;

    }

    public void update() {
        x += dx;
        x++;
        if (x < -CanvasView.WIDTH) {
            x = 0;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
        if (x < 0) {
            canvas.drawBitmap(image, x + CanvasView.WIDTH, y, null);
        }
    }
}