package com.example.pong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FieldView field = new FieldView(getApplicationContext());
        setContentView(field);
        while(field.game()){
            field.invalidate();
        }
    }
}