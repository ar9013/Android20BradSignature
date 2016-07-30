package com.javaclass.anima.android20bradsignature;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    PaintView myPaintView;

    private View clear,undo,redo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myPaintView = (PaintView) findViewById(R.id.pview);

        clear = findViewById(R.id.clear);
        undo = findViewById(R.id.undo);
        redo = findViewById(R.id.redo);


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myPaintView.clearDraw();

            }
        });

        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myPaintView.undoDraw();
            }
        });


        redo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myPaintView.redoDraw();
            }
        });
    }
}
