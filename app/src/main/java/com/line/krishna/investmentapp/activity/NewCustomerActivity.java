package com.line.krishna.investmentapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.line.krishna.investmentapp.R;
import com.line.krishna.investmentapp.core.Customer;
import com.line.krishna.investmentapp.database.LineDatabase;
import com.line.krishna.investmentapp.util.Constants;

/**
 * Created by Krishna on 04/12/2015.
 */
public class NewCustomerActivity extends Activity implements View.OnClickListener {

    private EditText mEtName;
    private EditText mEtAddress;

    private LineDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_customer_layout);

        database = new LineDatabase(this);
        mEtName = (EditText) findViewById(R.id.etName);
        mEtAddress = (EditText) findViewById(R.id.etAddress);

        Button btnSubmit= (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSubmit:

                String name=mEtName.getText().toString().trim();
                String address=mEtAddress.getText().toString().trim();
                Customer customer=new Customer();
                customer.setName(name);
                customer.setAddress(address);

                Intent intent=new Intent(this, CustomerLoanActivity.class);
                intent.putExtra(Constants.BEAN, customer);
                startActivity(intent);
                break;
        }
    }
}
