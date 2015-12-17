package com.line.krishna.investmentapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.line.krishna.investmentapp.R;
import com.line.krishna.investmentapp.core.Period;
import com.line.krishna.investmentapp.database.LineDatabase;

/**
 * Created by Krishna on 05/12/2015.
 */
public class PaymentPeriodActivity extends Activity implements View.OnClickListener {

    private EditText mEtSerialNumber;
    private EditText mEtPeriodAmount;
    private LineDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_period_layout);

        database = new LineDatabase(this);
        mEtSerialNumber = (EditText) findViewById(R.id.etSerialNumber);
        mEtPeriodAmount = (EditText) findViewById(R.id.etPeriodAmount);

        Button btnSubmit= (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSubmit:
                Period period=new Period();

                int serialNumber=0;
                int periodAmount=0;

                try{
                    serialNumber=Integer.parseInt(mEtSerialNumber.getText().toString().trim());
                    periodAmount=Integer.parseInt(mEtPeriodAmount.getText().toString().trim());
                }catch (Exception e){
                    e.printStackTrace();
                }
                period.setAccountId(serialNumber);
                period.setPaidAmount(periodAmount);

                long insertId=database.insertPeriod(period);
                Log.d("PaymentPeriodActivity", "onClick (Line:54) :"+insertId);
                Toast.makeText(getApplicationContext(), "inserted period id :"+insertId, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
