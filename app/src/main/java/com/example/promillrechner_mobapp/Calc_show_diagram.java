package com.example.promillrechner_mobapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Calc_show_diagram extends AppCompatActivity {

    Button buttonGoBackToMain = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_show_diagram);

        buttonGoBackToMain = findViewById(R.id.buttonGoToShowDiagram);
        buttonGoBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerGoBackToMain();
            }
        });
    }

    private void handlerGoBackToMain() {
        finish();
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
    }
}
