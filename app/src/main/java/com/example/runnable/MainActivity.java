package com.example.runnable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private static final String regex = "\\w{2}\\d\\w{2}\\d\\p{Upper}[!,@,#,$,%,&,\\*]\\d\\w{2}\\d\\w\\p{Upper}\\d[!,@,#,$,%,&,\\*]";
    private Pattern pattern;
    private Matcher matcher;
    private TextView password;
    private TextView email;
    private TextView success;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        button = findViewById(R.id.submit);
        success = findViewById(R.id.success);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new LoginTask());
                try{
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e){
                    Log.d("imm : " , e.toString());
                }
            }
        });

        ((Button) findViewById(R.id.second_task)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondTask.class);
                startActivity(i);
            }
        });

        ((Button) findViewById(R.id.third_task)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ThirdTask.class);
                startActivity(i);
            }
        });
    }

    class LoginTask implements Runnable{
        @Override
        public void run() {
            String pass = password.getText().toString();
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(pass);

            if(matcher.matches()){
               success.setText("SUCCESS");
               success.setTextColor(Color.GREEN);
            } else {
                success.setText("FAIL");
                success.setTextColor(Color.RED);
            }
        }
    }
}
