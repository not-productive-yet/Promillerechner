package com.example.promillrechner_mobapp.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.promillrechner_mobapp.MainActivity;
import com.example.promillrechner_mobapp.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Diagram extends AppCompatActivity {

    Button buttonGoBackToMain;
    TextView infoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_diagram);

        final GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.setVisibility(View.VISIBLE);

        buttonGoBackToMain = findViewById(R.id.buttonGoBackToMain);
        buttonGoBackToMain.setOnClickListener(view -> handlerGoBackToMain());

        Intent intent = getIntent();
        double result = 0.5;
        double nüchternIn = result/0.1;

        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();

        gridLabel.setGridColor(Color.WHITE);
        gridLabel.setVerticalLabelsColor(Color.WHITE);
        gridLabel.setHorizontalLabelsColor(Color.WHITE);
        gridLabel.setHorizontalAxisTitle("Zeit");
        gridLabel.setVerticalAxisTitle("Promille");
        gridLabel.setHorizontalAxisTitleColor(Color.WHITE);
        gridLabel.setVerticalAxisTitleColor(Color.WHITE);

        LineGraphSeries < DataPoint > series = new LineGraphSeries< >(new DataPoint[] {
                new DataPoint(0, result),
                new DataPoint((nüchternIn),0),
        });

        series.setColor(Color.MAGENTA);
        graph.addSeries(series);

        infoText = findViewById(R.id.infoText);
        infoText.setText("Du bist in " + nüchternIn + " Stunden wieder nüchtern");



    }

    private void handlerGoBackToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
