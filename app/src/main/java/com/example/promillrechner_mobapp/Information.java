package com.example.promillrechner_mobapp;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import android.os.Bundle;

public class Information extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        Button button = (Button) findViewById(R.id.backButton);
        button.setOnClickListener(this);

    }
    @Override
    public void onClick(View view){
        finish();
    }
}
