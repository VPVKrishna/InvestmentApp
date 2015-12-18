package com.line.krishna.investmentapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.line.krishna.investmentapp.R;
import com.line.krishna.investmentapp.core.Line;
import com.line.krishna.investmentapp.database.LineDatabase;

/**
 * Created by Krishna on 12/12/2015.
 */
public class LineDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private int id;
    private String name;
    private String weekDay;
    private String investersAmounts;
    private EditText mEtLineName;
    private EditText mEtLineWeek;
    private EditText mEtInvestersAmount;
    private LineDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.line_detail_activity);

        database = new LineDatabase(this);

        mEtLineName = (EditText) findViewById(R.id.etLineName);
        mEtLineWeek = (EditText) findViewById(R.id.etLineWeek);
        mEtInvestersAmount = (EditText) findViewById(R.id.etLineInvesterAmount);

        Button btnSubmit= (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSubmit:
                String lineName=mEtLineName.getText().toString().trim();
                String lineWeek=mEtLineWeek.getText().toString().trim();
                String investersAmount=mEtInvestersAmount.getText().toString().trim();

                Line line=new Line();
                line.setName(lineName);
                line.setWeekDay(lineWeek);
                line.setInvestersAmounts(investersAmount);

                long insertId=database.insertLine(line);
                Log.d("LineDetailActivity", "onClick (Line:55) :"+insertId);

                Toast.makeText(this, "line id :"+insertId, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
