package com.example.runnable;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class ThirdTask extends AppCompatActivity {

    Handler handler;
    Button start;
    ProgressBar pg;
    TextView tv;
    boolean login = false;
    boolean download = false;

    final Object object = new Object();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_task);
        handler = new Handler();
        start = findViewById(R.id.start);
        pg = findViewById(R.id.prograss_bar);
        tv = findViewById(R.id.answer);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("");
                pg.setVisibility(View.VISIBLE);
                runBoth();
            }
        });

    }

    public void bothFinished(){
        if(login && download){
            if(new Random().nextBoolean()) {
                tv.setText("SUCCESS");
                tv.setTextColor(Color.GREEN);
            } else {
                tv.setText("ERROR");
                tv.setTextColor(Color.RED);

            }

            pg.setVisibility(View.GONE);
        }
    }
    public void runBoth(){
        synchronized (object){
            try{
                handler.postDelayed(new FirstRunnable(), new Random().nextInt((5 - 2) + 1) + 2 * 1000);
                handler.postDelayed(new SecondRunnable(), new Random().nextInt((5 - 3) + 1) + 3 * 1000);

            } catch(Exception e){
                e.printStackTrace();
            }
        }


    }

    class FirstRunnable implements Runnable{
        @Override
        public void run() {
            login = true;
            bothFinished();
            Log.d("Ended:", " First");
        }
    }

    class SecondRunnable implements Runnable{
        @Override
        public void run() {

            download = true;
            bothFinished();
            Log.d("Ended:", " Second");

        }
    }
}
