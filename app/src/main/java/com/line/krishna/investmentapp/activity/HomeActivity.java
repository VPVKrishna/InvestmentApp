package com.line.krishna.investmentapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.line.krishna.investmentapp.R;
import com.line.krishna.investmentapp.design.NavigationMainActivity;
import com.line.krishna.investmentapp.design.SnakeBarMainActivity;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void createCustomer(View view) {
        Intent intent=new Intent(this, NewCustomerActivity.class);
        startActivity(intent);
    }

    public void createAccount(View view) {
        Intent intent=new Intent(this, CustomerLoanActivity.class);
        startActivity(intent);
    }

    public void paymentPeriod(View view) {
        Intent intent=new Intent(this, PaymentPeriodActivity.class);
        startActivity(intent);
    }

    public void createLine(View view){
        Intent intent=new Intent(this, LineDetailActivity.class);
        startActivity(intent);
    }

    public void searchResults(View view) {
        Intent intent=new Intent(this, SearchResultActivity.class);
        startActivity(intent);
    }

    public void showCustomerDetails(View view) {
        Intent intent=new Intent(this, CustomerDetailsActivity.class);
        startActivity(intent);
    }

    public void designSnackBar(View view) {
        Intent intent=new Intent(this, SnakeBarMainActivity.class);
        startActivity(intent);
    }

    public void designNavigation(View view) {
        Intent intent=new Intent(this, NavigationMainActivity.class);
        startActivity(intent);
    }
}
