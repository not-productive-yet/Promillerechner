package com.example.promillrechner_mobapp.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.promillrechner_mobapp.R;

public class Alcohol extends AppCompatActivity {

    Button buttonGoToShowPromille = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_alcohol);

        buttonGoToShowPromille = findViewById(R.id.buttonGoToShowPromille);

        buttonGoToShowPromille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerGoToShowPromille();
            }
        });
    }

    private void handlerGoToShowPromille() {
        Intent intent = new Intent(this, Results.class);
        startActivity(intent);
        overridePendingTransition(R.xml.enter, R.xml.exit);
    }
}
