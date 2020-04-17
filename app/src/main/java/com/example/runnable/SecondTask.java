package com.example.runnable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondTask extends AppCompatActivity {

    Button start;
    Button stop;
    TextView counter;
    Handler handler;

    boolean isStopped = true;
    static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_task);

        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        counter = findViewById(R.id.counter);
        handler = new Handler();

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                count++;
                counter.setText(String.valueOf(count));
                handler.postDelayed(this, 1000);
            }
        };

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.post(runnable);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(runnable);
            }
        });
    }
}
