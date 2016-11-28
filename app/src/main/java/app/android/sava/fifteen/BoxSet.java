package app.android.sava.fifteen;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by sava on 28.11.16.
 */

public class BoxSet {
    private ArrayList<ArrayList<Box>> map;
    private int n_in_line;
    private float step, x, y;
    private int bi, bj;

    BoxSet(int n, float s, float x0, float y0) {
        x = x0;
        y = y0;
        step = s;
        n_in_line = n;
        int n_items = n_in_line * n_in_line;
        Random rnd = new Random();
        ArrayList<Integer> inds = new ArrayList<Integer>();
        ArrayList<Integer> ch_inds = new ArrayList<Integer>();

        for (int i = 1; i < n_items; ++i)
            inds.add(new Integer(i));

        map = new ArrayList<>();
        for(int i = 0; i < n_in_line; ++i) {
            map.add(new ArrayList<Box>());
            for (int j = 0; j < n_in_line; ++j) {
                if ((i == n_in_line - 1) && (j == n_in_line - 1)) {
                    bi = i;
                    bj = j;
                    map.get(i).add(new Box(0, i, j));
                    break;
                }
                //int id = (int) (n_items * Math.random()) + 1;
                int id = rnd.nextInt(inds.size());
                map.get(i).add(new Box(inds.get(id).intValue(), i, j));
                ch_inds.add(new Integer(inds.get(id).intValue()));
                inds.remove(id);
            }
        }

        if (!Check(ch_inds)) {
            ArrayList<ArrayList<Box>> n_map = new ArrayList<>();
            for (int i = 0; i < n_in_line; ++i) {
                n_map.add(new ArrayList<Box>());
            }
            for (int i = 0; i < n_in_line; ++i) {
                for (int j = 0; j < n_in_line; ++j) {
                    n_map.get(j).add(map.get(i).get(j));
                }
            }
            map = n_map;
        }
    }

    public void Draw(Canvas canvas) {
        for (ArrayList<Box> line: map) {
            for (Box b: line) {
                b.Draw(canvas, x, y, step);
            }
        }
        //map.get(0).get(0).Draw(m_canvas, x, y, step);
    }

    public void MoveBox(int i, int j) {
        int len;
        len = i < bi ? bi - i : i - bi;
        if (len > 1)
            return;
        len = j < bj ? bj - j : j - bj;
        if (len > 1)
            return;
        Swap(bi - i, bj - j);
    }

    private void Swap(int i, int j) {
        int id = map.get(bi).get(bj).getId();
        if (i != 0 && j != 0)
            return;
        if (i != 0) {
            if (i < 0 && bi < n_in_line - 1) {
                map.get(bi).get(bj).setId(map.get(bi + 1).get(bj).getId());
                map.get(bi + 1).get(bj).setId(id);
                ++bi;
            } else if (bi > 0){
                map.get(bi).get(bj).setId(map.get(bi - 1).get(bj).getId());
                map.get(bi - 1).get(bj).setId(id);
                --bi;
            }
        } else if (j != 0){
            if (j < 0 && bj < n_in_line - 1) {
                map.get(bi).get(bj).setId(map.get(bi).get(bj + 1).getId());
                map.get(bi).get(bj + 1).setId(id);
                ++bj;
            } else if (bj > 0){
                map.get(bi).get(bj).setId(map.get(bi).get(bj - 1).getId());
                map.get(bi).get(bj - 1).setId(id);
                --bj;
            }
        }
    }

    private boolean Check(ArrayList<Integer> a){
        int inv = 0;
        for (int i = 0; i < a.size(); ++i) {
            if (a.get(i).intValue() > 0) {
                for (int j = 0; j < i; ++j) {
                    if (a.get(i).intValue() < a.get(j).intValue())
                        ++inv;
                }
            }
        }

        for (int i = 0; i < a.size(); ++i) {
            if (a.get(i).intValue() == 0) {
                inv += 1 + i / 4;
            }
        }
        return inv % 2 == 0;
    }
}
