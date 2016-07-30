package com.javaclass.anima.android20bradsignature;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by anima on 2016/7/30.
 */
public class PaintView extends View {

    Paint paintLine;
    LinkedList<HashMap<String,Float>> line = new LinkedList<>();

    LinkedList<LinkedList<HashMap<String,Float>>> lines = new LinkedList<>();
    LinkedList<LinkedList<HashMap<String,Float>>> recyle = new LinkedList<>();

    private boolean istouched;


    public PaintView(Context context, AttributeSet attrs) {
        super(context,attrs);

        // 設定畫筆
        paintLine = new Paint();
        paintLine.setColor(Color.YELLOW);
        paintLine.setStrokeWidth(4);

        // 初始建構線條資料結構物件
          lines = new LinkedList<LinkedList<HashMap<String,Float>>>();
        recyle = new LinkedList<LinkedList<HashMap<String,Float>>>();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

         // 手指離開螢幕
        if(event.getAction() == event.ACTION_UP){
            istouched = false;
        }else {
            LinkedList<HashMap<String, Float>> line;

            if(!istouched){
                // 觸摸開始
                line = new   LinkedList<HashMap<String, Float>>();
                lines.add(line);
                istouched = true;
            }else{
                // 開始滑動
                line = lines.getLast();
            }

            HashMap<String, Float> point = new HashMap<String, Float>();
            point.put("x", event.getX());
            point.put("y", event.getY());
            line.add(point);
            postInvalidate();
        }
    return true;
    }

    // 清除畫面
    void clearDraw() {
        lines.clear();
        postInvalidate();
    }

    // Undo功能
    void undoDraw() {
        if (lines.size() > 0) {
            recyle.add(lines.removeLast());
            postInvalidate();
        }
    }

    // Redo功能
    void redoDraw() {
        if (recyle.size() > 0) {
            lines.add(recyle.removeLast());
            postInvalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (LinkedList<HashMap<String, Float>> line : lines) {
            if (line.size() > 1) {
                // 至少有兩點才需要畫出線段
                for (int i = 1; i < line.size(); i++) { // i = 1表示從第二點開始處理
                    HashMap<String, Float> point1 = line.get(i - 1); // 前一點
                    HashMap<String, Float> point2 = line.get(i); // 目前點
                    canvas.drawLine(point1.get("x"), point1.get("y"),
                            point2.get("x"), point2.get("y"), paintLine);
                }
            }
        }
    }
}
