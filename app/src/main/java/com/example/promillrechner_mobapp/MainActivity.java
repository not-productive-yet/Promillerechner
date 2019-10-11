package com.example.promillrechner_mobapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonGoToCalculator = null;
    Button buttonGoToDatabase = null;
    Button buttonGoToInformations = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGoToCalculator = findViewById(R.id.buttonGoToCalculator);
        buttonGoToDatabase = findViewById(R.id.buttonGoToDatabase);
        buttonGoToInformations = findViewById(R.id.buttonGoToInformations);

        buttonGoToCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerGoToCalculator();
            }
        });

        buttonGoToDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerGoToDatabase();
            }
        });

        buttonGoToInformations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerGoToInformations();
            }
        });
    }

    private void handlerGoToCalculator(){
        Intent intent = new Intent(this, Calc_choose_person.class);
        startActivity(intent);
    }

    private void handlerGoToDatabase(){
        Intent intent = new Intent(this, Database_persons.class);
        startActivity(intent);
    }

    private void handlerGoToInformations(){
        Intent intent = new Intent(this, Information.class);
        startActivity(intent);
    }
}
