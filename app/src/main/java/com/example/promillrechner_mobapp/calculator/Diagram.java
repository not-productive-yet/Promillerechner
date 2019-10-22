package com.example.promillrechner_mobapp.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.promillrechner_mobapp.MainActivity;
import com.example.promillrechner_mobapp.R;

public class Diagram extends AppCompatActivity {

    Button buttonGoBackToMain = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_diagram);

        buttonGoBackToMain = findViewById(R.id.buttonGoBackToMain);
        buttonGoBackToMain.setOnClickListener(view -> handlerGoBackToMain());

        //TODO: Diagram einfügen
    }

    private void handlerGoBackToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
