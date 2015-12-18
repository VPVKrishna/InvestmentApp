package com.line.krishna.investmentapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.line.krishna.investmentapp.R;
import com.line.krishna.investmentapp.database.LineDatabase;

/**
 * Created by Krishna on 05/12/2015.
 */
public class SearchResultActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEtQuery;
    private EditText mEtTable;
    private LineDatabase database;
    private WebView mWvSearchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results_layout);
        database = new LineDatabase(this);

        mEtQuery = (EditText) findViewById(R.id.etQuery);
        mEtTable = (EditText) findViewById(R.id.etTableName);
        mWvSearchResults = (WebView) findViewById(R.id.wvSearchResults);
        Button btnShowResults= (Button) findViewById(R.id.btnShowResults);
        Button btnExportResults= (Button) findViewById(R.id.btnExportResults);
        btnShowResults.setOnClickListener(this);
        btnExportResults.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnShowResults:
                String query=mEtQuery.getText().toString().trim();
                if(query.length()==0){
                    String tableName=mEtTable.getText().toString().trim();
                    if(tableName.length()==0){
                        Toast.makeText(this, "Please enter query or tablename", Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        String html=database.getHtmlStringFromTable(tableName);
                        showHtmlData(html);
                    }
                }else{
                    String html=database.getHtmlString(query);
                    showHtmlData(html);
                }
                Toast.makeText(this, "Successfully retrieved...!", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btnExportResults:
                query=mEtQuery.getText().toString().trim();
                if(query.length()==0){
                    String tableName=mEtTable.getText().toString().trim();
                    if(tableName.length()==0){
                        Toast.makeText(this, "Please enter query or tablename", Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        database.getExportedTableFile(tableName);
                    }
                }else{
                    database.getExportedFile(query);
                }
                Toast.makeText(this, "Successfully exported", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    private void showHtmlData(String html) {
        mWvSearchResults.loadData(html, "text/html", "UTF-8");
    }
}
