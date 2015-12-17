package com.line.krishna.investmentapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.line.krishna.investmentapp.R;
import com.line.krishna.investmentapp.adapter.LineAdapter;
import com.line.krishna.investmentapp.core.Account;
import com.line.krishna.investmentapp.core.Customer;
import com.line.krishna.investmentapp.core.Line;
import com.line.krishna.investmentapp.database.LineDatabase;
import com.line.krishna.investmentapp.util.Constants;

import java.util.ArrayList;

/**
 * Created by Krishna on 04/12/2015.
 */
public class CustomerLoanActivity extends Activity implements View.OnClickListener {

    private AutoCompleteTextView mEtLineSerialNumber;
    private EditText mEtInvestmentAmount;
    private EditText mEtActualAmount;
    private EditText mEtNumberPeriods;
    private EditText mEtReferredPerson;
    private LineDatabase database;
    private Customer mCustomer;
    private EditText mEtCustomerSerialNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_loan_layout);

        database = new LineDatabase(this);

        ArrayList<Line> allLines = database.getAllLines();

        final LineAdapter lineAdapter=new LineAdapter(this, allLines);

        mEtCustomerSerialNumber = (EditText) findViewById(R.id.etCustomerSerialNumber);
        mEtLineSerialNumber = (AutoCompleteTextView) findViewById(R.id.etSerialNumber);
        mEtInvestmentAmount = (EditText) findViewById(R.id.etInvestmentAmount);
        mEtActualAmount = (EditText) findViewById(R.id.etActualAmount);
        mEtNumberPeriods = (EditText) findViewById(R.id.etLinePeriods);
        mEtReferredPerson = (EditText) findViewById(R.id.etCustomerReferredPersion);

        Button btnSubmit= (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);

        Intent intent=getIntent();
        if(intent!=null) {
            mCustomer = (Customer) intent.getSerializableExtra(Constants.BEAN);
            if(mCustomer!=null) {
                mEtCustomerSerialNumber.setVisibility(View.GONE);
            }
        }

        mEtLineSerialNumber.setAdapter(lineAdapter);
        mEtLineSerialNumber.setThreshold(1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSubmit:
                Account account=new Account();
                int serialNumber=0;
                int investmentAmount=0;
                int actualAmount=0;
                int numberPeriods=0;
                String referredPerson=mEtReferredPerson.getText().toString().trim();
                try {
                    serialNumber=Integer.parseInt(mEtLineSerialNumber.getText().toString().trim());
                    investmentAmount = Integer.parseInt(mEtInvestmentAmount.getText().toString().trim());
                    actualAmount = Integer.parseInt(mEtActualAmount.getText().toString().trim());
                    numberPeriods = Integer.parseInt(mEtNumberPeriods.getText().toString().trim());
                }catch (Exception e){
                    e.printStackTrace();
                }
                account.setLineId(serialNumber);
                account.setInvestmentAmount(investmentAmount);
                account.setActualAmount(actualAmount);
                account.setNumPeriods(numberPeriods);
                account.setReferredPerson(referredPerson);

                long accountId = database.insertAccount(account);
                Log.d("CustomerLoanActivity", "onClick (Line:68) :" + accountId+" "+account);
                Toast.makeText(getApplicationContext(), "account id :"+accountId, Toast.LENGTH_SHORT).show();

                if (mCustomer != null) {
                    mCustomer.setLatestAccountId((int) accountId);
                    long customerId = database.insertCustomer(mCustomer);
                    Log.d("CustomerLoanActivity", "onClick (Line:85) :"+customerId+" "+mCustomer);
                    Toast.makeText(getApplicationContext(), "customer id :"+customerId, Toast.LENGTH_SHORT).show();
                }else{
                    int customerId=Integer.parseInt(mEtCustomerSerialNumber.getText().toString().trim());
                    int affectedRows = database.updateCustomerLatestAccountId(customerId, (int) accountId);
                    Log.d("CustomerLoanActivity", "onClick (Line:92) :"+affectedRows);
                    Toast.makeText(getApplicationContext(), "updated successfully :"+affectedRows, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


}
