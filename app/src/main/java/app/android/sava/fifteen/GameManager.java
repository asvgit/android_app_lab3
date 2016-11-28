package app.android.sava.fifteen;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by sava on 28.11.16.
 */
public class GameManager {
    private static final int MAX_RECT_IN_LINE = 4;
    private BoxSet box_set;
    private float step, x0, y0;

    private void InitBoxSet(int width, int height){
        float map_width;
        if (width < height) {
            map_width = width;
            x0 = 0;
            y0 = (height - width) / 2;
        } else {
            map_width = height;
            y0 = 0;
            x0 = (width - height) / 2;
        }
        step = map_width / MAX_RECT_IN_LINE;
        box_set = new BoxSet(MAX_RECT_IN_LINE, step, x0, y0);
    }

    private int GetId(float x, float x0) {
        int i;
        boolean f = false;
        for (i = 1; i <= MAX_RECT_IN_LINE; ++i) {
            if (x0 + i * step > x) {
                f = true;
                break;
            }
        }
        --i;
        if (f)
            return i;
        else
            return -1;
    }

    public GameManager(int w, int h) {
        InitBoxSet(w, h);
    }

    public void onDraw(Canvas canvas) {
        box_set.Draw(canvas);
    }

    public void onTouchEvent(float x, float y) {
        int i = GetId(y, y0), j = GetId(x, x0);
        if (i >= 0 && j >= 0 )
            box_set.MoveBox(i, j);
    }
}
