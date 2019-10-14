package com.example.promillrechner_mobapp.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.promillrechner_mobapp.R;

public class Diagram extends AppCompatActivity {

    Button buttonGoBackToMain = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_diagram);

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
