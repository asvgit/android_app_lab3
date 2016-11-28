package app.android.sava.fifteen;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.PathInterpolator;

/**
 * Created by sava on 28.11.16.
 */

public class CanvasView extends View {
    private int width, height;
    private GameManager m_game_manager;

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWidthAndHeight(context);
        m_game_manager = new GameManager(width, height);
    }

    private void initWidthAndHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        width = point.x;
        height = point.y;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        m_game_manager.onDraw(canvas);
    }

    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX(), y = event.getY();
        if (event.getAction() == MotionEvent.ACTION_UP)
            m_game_manager.onTouchEvent(x, y);
        invalidate();
        return true;
    }
}
