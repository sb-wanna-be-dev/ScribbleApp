package com.example.scribbleapp;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private com.example.scribbleapp.DrawingView drawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawingView = findViewById(R.id.drawingView);

        // Color selection buttons
        Button blackBtn = findViewById(R.id.blackColorBtn);
        Button redBtn = findViewById(R.id.redColorBtn);
        Button blueBtn = findViewById(R.id.blueColorBtn);

        // Brush size buttons
        Button thinBtn = findViewById(R.id.thinBrushBtn);
        Button mediumBtn = findViewById(R.id.mediumBrushBtn);
        Button thickBtn = findViewById(R.id.thickBrushBtn);

        // Clear button
        Button clearBtn = findViewById(R.id.clearBtn);

        // Color button listeners
        blackBtn.setOnClickListener(v -> drawingView.changeColor(Color.BLACK));
        redBtn.setOnClickListener(v -> drawingView.changeColor(Color.RED));
        blueBtn.setOnClickListener(v -> drawingView.changeColor(Color.BLUE));

        // Brush size listeners
        thinBtn.setOnClickListener(v -> drawingView.changeBrushSize(5f));
        mediumBtn.setOnClickListener(v -> drawingView.changeBrushSize(10f));
        thickBtn.setOnClickListener(v -> drawingView.changeBrushSize(20f));

        // Clear canvas
        clearBtn.setOnClickListener(v -> drawingView.clearCanvas());
    }
}