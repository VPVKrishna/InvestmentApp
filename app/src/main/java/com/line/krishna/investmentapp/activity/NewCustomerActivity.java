package com.line.krishna.investmentapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
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
public class NewCustomerActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEtName;
    private EditText mEtAddress;

    private LineDatabase database;
    private TextInputLayout mInputName;
    private TextInputLayout mInputAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_customer_layout);

        database = new LineDatabase(this);
        mEtName = (EditText) findViewById(R.id.etName);
        mEtAddress = (EditText) findViewById(R.id.etAddress);

        initTextInputLayouts();

        Button btnSubmit= (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
    }

    private void initTextInputLayouts(){
        mInputName = (TextInputLayout) findViewById(R.id.inputName);
        mInputAddress = (TextInputLayout) findViewById(R.id.inputAddress);
        mInputName.setErrorEnabled(true);
        mInputAddress.setErrorEnabled(true);
        mEtName.getBackground().setColorFilter(getResources().getColor(android.R.color.darker_gray), PorterDuff.Mode.SRC_ATOP);
        mEtAddress.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);//red color edit text line.
    }

    private boolean validateTextInputLayouts(Customer customer) {
        mInputName.setError(null);
        mInputAddress.setError(null);
        if(customer.getName().length()<3){
            mInputName.setError("Name should be 3 characters.");
            return true;
        }
        if(customer.getAddress().length()<4){
            mInputAddress.setError("Address should be 4 characters.");
            return true;
        }
        return false;
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

                if (validateTextInputLayouts(customer)) return;

                Intent intent=new Intent(this, CustomerLoanActivity.class);
                intent.putExtra(Constants.BEAN, customer);
                startActivity(intent);
                break;
        }
    }

}
