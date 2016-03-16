package a420.blaze.it.final_frontier;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


/**
 * Created by Conor on 04/11/2015.
 */
public class CanvasView extends SurfaceView implements SurfaceHolder.Callback {
    Context context;
    private GameThread thread;
    private Background bg;
    public static final int MOVESPEED = -5;
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 512;

    private Player player;

    public CanvasView(Context c) {
        super(c);
        context = c;

        getHolder().addCallback(this);

        thread = new GameThread(getHolder(), this);

        //make gamePanel focusable so it can handle events
        setFocusable(true);


    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    // override onSizeChanged
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }


    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.background));

        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.moulinroguefiredrun), 32, 32, 3);

        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();

    }

    public void update() {
        if (player.getPlaying()) {


        }
        player.update();
        bg.update();
    }

    // override draw
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        final float scaleFactorX = getWidth() / (WIDTH * 1.f);
        final float scaleFactorY = getHeight() / (HEIGHT * 1.f);
        if (canvas != null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);
            player.draw(canvas);
            canvas.restoreToCount(savedState);
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (!player.getPlaying()) {
                player.setPlaying(true);
            } else {
                player.setUp(true);
            }
            return true;

        }

        if (event.getAction() == MotionEvent.ACTION_UP) {
            player.setPlaying(false);
            player.setUp(false);
            return true;
        }
        return super.onTouchEvent(event);
    }


}

