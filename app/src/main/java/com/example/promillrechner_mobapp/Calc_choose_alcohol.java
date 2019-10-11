package com.example.promillrechner_mobapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.view.View;
import android.widget.Button;

public class Calc_choose_alcohol extends AppCompatActivity {

    Button buttonGoToShowPromille = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_choose_alcohol);

        buttonGoToShowPromille = findViewById(R.id.buttonGoToShowPromille);

        buttonGoToShowPromille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlerGoToShowPromille();
            }
        });
    }

    private void handlerGoToShowPromille() {
        Intent intent = new Intent(this, Calc_show_promille.class);
        startActivity(intent);
        overridePendingTransition(R.xml.enter, R.xml.exit);
    }
}
