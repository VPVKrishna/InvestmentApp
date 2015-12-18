package com.line.krishna.investmentapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import com.line.krishna.investmentapp.R;
import com.line.krishna.investmentapp.database.LineDatabase;

/**
 * Created by Krishna on 12/12/2015.
 */
public class CustomerDetailsActivity extends AppCompatActivity implements View.OnClickListener{

    private WebView mWvShowResults;
    private EditText mEtCustomerId;
    private LineDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_details);

        database = new LineDatabase(this);

        mWvShowResults= (WebView) findViewById(R.id.wvShowResults);
        mEtCustomerId = (EditText) findViewById(R.id.etCustId);

        Button btnSubmit= (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSubmit:
                try {
                    int id = Integer.parseInt(mEtCustomerId.getText().toString().trim());
                    String htmlCustomerDetails=database.getHtmlAccountInformation(id);
                    Log.d("CustomerDetailsActivity", "onClick (Line:45) :"+htmlCustomerDetails);
                    showHtmlData(htmlCustomerDetails);
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    private void showHtmlData(String html) {
        mWvShowResults.loadData(html, "text/html", "UTF-8");
    }
}
