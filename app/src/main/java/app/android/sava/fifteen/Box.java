package app.android.sava.fifteen;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by sava on 28.11.16.
 */

public class Box {
    private static final float PADDING = 10f;
    private int id, x, y;
    private int m_bg_color;

    Box(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        //if (id != 0) {
            m_bg_color = Color.BLUE;
        /*} else {
            m_bg_color = Color.WHITE;
        }*/
    }

    void Draw(Canvas canvas, float x0, float y0, float step) {
        if (id == 0)
            return;
        float x = x0 + this.y * step;
        float y = y0 + this.x * step;
        Paint paint = new Paint();
        paint.setColor(m_bg_color);
        canvas.drawRect(x + PADDING, y + PADDING, step + x - PADDING, step + y - PADDING, paint);
        if (id != 0) {
            paint.setColor(Color.WHITE);
            paint.setTextSize(step / 2);
            if (id < 10) {
                canvas.drawText(String.valueOf(id), x + step / 3, y + step / 1.5f, paint);
            } else {
                canvas.drawText(String.valueOf(id), x + step / 4.5f, y + step / 1.5f, paint);
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
