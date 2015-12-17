package com.line.krishna.investmentapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.line.krishna.investmentapp.core.Account;
import com.line.krishna.investmentapp.core.AccountInfo;
import com.line.krishna.investmentapp.core.Customer;
import com.line.krishna.investmentapp.core.Line;
import com.line.krishna.investmentapp.core.Period;
import com.line.krishna.investmentapp.util.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Krishna on 01/12/2015.
 */
public class LineDatabase extends SQLiteOpenHelper {

    private static final String TAG = "LineDatabase";

    private static final String DB_NAME = "line.db";
    private static final int DB_VERSION = 2;

    private static final String TABLE_CUSTOMER_DETAIL_INFO = "cust_detail_info";
    private static final String TABLE_CUSTOMER_ACCOUNT_INFO = "cust_account_info";
    private static final String TABLE_PERIOD_INFO = "period_info";
    private static final String TABLE_WEEK_LINE_INFO = "week_line_info";

    private static final String DETAIL_SERIAL_ID = "serial_id";
    private static final String DETAIL_CUST_NAME = "cust_name";
    private static final String DETAIL_CUST_ADDRESS = "cust_address";
    private static final String DETAIL_CUST_LATEST_ACCOUNT_ID = "account_id";

    String table_cust = "create table " + TABLE_CUSTOMER_DETAIL_INFO + " ( "
            + DETAIL_SERIAL_ID + " integer primary key Autoincrement, "
            + DETAIL_CUST_NAME + " text, "
            + DETAIL_CUST_ADDRESS + " text, "
            + DETAIL_CUST_LATEST_ACCOUNT_ID + " integer )";

    private static final String ACCOUNT_ID = "acc_id";
    private static final String ACCOUNT_TAKEN_DATE = "taken_date";
    private static final String ACCOUNT_LINE_ID = "line_id";
    private static final String ACCOUNT_INVESTMENT_AMOUNT = "investment_amount";
    private static final String ACCOUNT_ACTUAL_AMOUNT = "actual_amount";
    private static final String ACCOUNT_NUM_PERIODS = "num_periods";
    private static final String ACCOUNT_REFERED_PERSON = "referred_persion";
    private static final String ACCOUNT_ACTIVE_STATUS = "active_status";

    String table_account = "create table " + TABLE_CUSTOMER_ACCOUNT_INFO + " ( "
            + ACCOUNT_ID + " integer primary key Autoincrement, "
            + ACCOUNT_TAKEN_DATE + " date, "
            + ACCOUNT_LINE_ID + " integer, "
            + ACCOUNT_INVESTMENT_AMOUNT + " integer, "
            + ACCOUNT_ACTUAL_AMOUNT + " integer, "
            + ACCOUNT_NUM_PERIODS + " integer, "
            + ACCOUNT_REFERED_PERSON + " integer, "
            + ACCOUNT_ACTIVE_STATUS + " integer )";

    private static final String PERIOD_ID = "period_id";
    private static final String PERIOD_ACCCOUNT_ID = "period_acc_id";//check
    private static final String PERIOD_DATE = "period_date";
    private static final String PERIOD_NUM = "period_num";
    private static final String PERIOD_PAID_AMOUNT = "paid_amount";

    String table_period = "create table " + TABLE_PERIOD_INFO + " ( "
            + PERIOD_ID + " integer primary key Autoincrement, "
            + PERIOD_ACCCOUNT_ID + " integer, "
            + PERIOD_DATE + " date, "
            + PERIOD_NUM + " integer, "
            + PERIOD_PAID_AMOUNT + " integer ) ";

    private static final String LINE_ID = "line_id";
    private static final String LINE_NAME = "line_name";
    private static final String LINE_WEEK_DAY = "week_day";
    private static final String LINE_INVESTERS_AMOUNTS = "investers_amounts";

    String table_line = "create table " + TABLE_WEEK_LINE_INFO + " ( "
            + LINE_ID + " integer primary key Autoincrement, "
            + LINE_NAME + " text, "
            + LINE_WEEK_DAY + " text, "
            + LINE_INVESTERS_AMOUNTS + " text ) ";

    public LineDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, table_cust);
        Log.d(TAG, table_account);
        Log.d(TAG, table_period);
        Log.d(TAG, table_line);

        db.execSQL(table_cust);
        db.execSQL(table_account);
        db.execSQL(table_period);
        db.execSQL(table_line);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_CUSTOMER_DETAIL_INFO);
        db.execSQL("drop table if exists " + TABLE_CUSTOMER_ACCOUNT_INFO);
        db.execSQL("drop table if exists " + TABLE_PERIOD_INFO);
        db.execSQL("drop table if exists " + TABLE_WEEK_LINE_INFO);
        onCreate(db);
    }


    public long insertCustomer(Customer customer) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put(DETAIL_SERIAL_ID, customer.getSerialId());
        values.put(DETAIL_CUST_NAME, customer.getName());
        values.put(DETAIL_CUST_ADDRESS, customer.getAddress());
        values.put(DETAIL_CUST_LATEST_ACCOUNT_ID, customer.getLatestAccountId());
        long insertId = database.insert(TABLE_CUSTOMER_DETAIL_INFO, null, values);
        database.close();
        return insertId;
    }

    /**
     * Which returns numRowsAffected in Customer table for the given account id.
     * @param customerId id of Customer
     * @param latestAccountId id of latest Account
     * @return which return the number of rows affected in Customer table.
     */
    public int updateCustomerLatestAccountId(int customerId, int latestAccountId){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(DETAIL_CUST_LATEST_ACCOUNT_ID, latestAccountId);
        String whereClause = DETAIL_SERIAL_ID + " = ? ";
        String[] whereArgs = {customerId + ""};
        int numRowsAffected = database.update(TABLE_CUSTOMER_DETAIL_INFO, values, whereClause, whereArgs);
        Log.d("LineDatabase", "updateCustomerLatestAccountId (Line:134) :" + numRowsAffected);
        return numRowsAffected;
    }

    public long insertAccount(Account account) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put(ACCOUNT_ID, account.getId());
        values.put(ACCOUNT_TAKEN_DATE, account.getTakenDate());
        values.put(ACCOUNT_LINE_ID, account.getLineId());
        values.put(ACCOUNT_INVESTMENT_AMOUNT, account.getInvestmentAmount());
        values.put(ACCOUNT_ACTUAL_AMOUNT, account.getActualAmount());
        values.put(ACCOUNT_NUM_PERIODS, account.getNumPeriods());
        values.put(ACCOUNT_REFERED_PERSON, account.getReferredPerson());
        values.put(ACCOUNT_ACTIVE_STATUS, account.getActiveStatusInt());
        values.put(ACCOUNT_TAKEN_DATE, Util.getDateString(new Date(), Util.DATE_FORMAT));
        long insertId = database.insert(TABLE_CUSTOMER_ACCOUNT_INFO, null, values);
        database.close();
        return insertId;
    }

    public long insertPeriod(Period period) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put(PERIOD_ID, period.getId());
        values.put(PERIOD_ACCCOUNT_ID, period.getAccountId());
        values.put(PERIOD_DATE, period.getDate());
        values.put(PERIOD_NUM, period.getNumPeriods());
        values.put(PERIOD_PAID_AMOUNT, period.getPaidAmount());
        values.put(PERIOD_DATE, Util.getDateString(new Date(), Util.DATE_FORMAT));
        long insertId = database.insert(TABLE_PERIOD_INFO, null, values);
        database.close();
        return insertId;
    }

    public long insertLine(Line line) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put(LINE_ID, line.getId());
        values.put(LINE_NAME, line.getName());
        values.put(LINE_WEEK_DAY, line.getWeekDay());
        values.put(LINE_INVESTERS_AMOUNTS, line.getInvestersAmounts());
        long insertId = database.insert(TABLE_WEEK_LINE_INFO, null, values);
        database.close();
        return insertId;
    }

    public ArrayList<Line> getAllLines(){
        SQLiteDatabase database=getReadableDatabase();

        Cursor cursor=database.query(TABLE_WEEK_LINE_INFO, null, null, null, null, null, null);

        ArrayList<Line> lines=new ArrayList<>();
        while (cursor.moveToNext()){
            int lineId=cursor.getInt(cursor.getColumnIndex(LINE_ID));
            String lineName=cursor.getString(cursor.getColumnIndex(LINE_NAME));
            String lineWeek=cursor.getString(cursor.getColumnIndex(LINE_WEEK_DAY));
            String lineInvesterAmount=cursor.getString(cursor.getColumnIndex(LINE_INVESTERS_AMOUNTS));

            Line line=new Line();
            line.setId(lineId);
            line.setName(lineName);
            line.setWeekDay(lineWeek);
            line.setInvestersAmounts(lineInvesterAmount);
            lines.add(line);
        }
        cursor.close();
        database.close();
        return lines;
    }

    public File getExportedTableFile(String tableName) {
        return getExportedFile("select * from " + tableName);
    }

    public File getExportedFile(String query) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        File file = ExportDatabaseToCSV.exportDataBaseIntoCSV(cursor);
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        database.close();
        return file;
    }

    public Customer getCustomer(int customerId){
        SQLiteDatabase database = getReadableDatabase();
        Cursor custCursor = getCursorCustomer(customerId, database);

        custCursor.moveToFirst();

        String custName = custCursor.getString(custCursor.getColumnIndex(DETAIL_CUST_NAME));
        String custAddress = custCursor.getString(custCursor.getColumnIndex(DETAIL_CUST_ADDRESS));
        int latestAccId = custCursor.getInt(custCursor.getColumnIndex(DETAIL_CUST_LATEST_ACCOUNT_ID));

        custCursor.close();

        Customer customer = new Customer();
        customer.setName(custName);
        customer.setAddress(custAddress);
        customer.setLatestAccountId(latestAccId);
        customer.setSerialId(customerId);

        database.close();
        return customer;
    }

    private Cursor getCursorCustomer(int customerId, SQLiteDatabase database) {
        // getting Customer details.
        String[] custWhereArgs = {customerId + ""};
        String custWhere = DETAIL_SERIAL_ID + " = ? ";

        return database.query(TABLE_CUSTOMER_DETAIL_INFO, null, custWhere, custWhereArgs, null, null, null, null);
    }

    public Account getAccount(int latestAccId){
        SQLiteDatabase database = getReadableDatabase();
        Cursor accCursor = getCursorAccount(latestAccId, database);

        accCursor.moveToFirst();

        String accReferredPerson = accCursor.getString(accCursor.getColumnIndex(ACCOUNT_REFERED_PERSON));
        int accNumPeriod = accCursor.getInt(accCursor.getColumnIndex(ACCOUNT_NUM_PERIODS));
        int accActualAmount = accCursor.getInt(accCursor.getColumnIndex(ACCOUNT_ACTUAL_AMOUNT));
        int accStatusInt = accCursor.getInt(accCursor.getColumnIndex(ACCOUNT_ACTIVE_STATUS));
        int accInvestmentAmount = accCursor.getInt(accCursor.getColumnIndex(ACCOUNT_INVESTMENT_AMOUNT));
        int accLineId = accCursor.getInt(accCursor.getColumnIndex(ACCOUNT_LINE_ID));
        String accDate = accCursor.getString(accCursor.getColumnIndex(ACCOUNT_TAKEN_DATE));

        accCursor.close();
        Account account = new Account();
        account.setId(latestAccId);
        account.setReferredPerson(accReferredPerson);
        account.setNumPeriods(accNumPeriod);
        account.setActualAmount(accActualAmount);
        account.setActiveStatusInt(accStatusInt);
        account.setInvestmentAmount(accInvestmentAmount);
        account.setLineId(accLineId);
        account.setTakenDate(accDate);

        Log.d("LineDatabase", "getAccountInformation (Line:234) :" + account);

        database.close();
        return account;
    }

    private Cursor getCursorAccount(int latestAccId, SQLiteDatabase database) {
        // getting account details
        String[] accWhereArgs = {latestAccId + ""};
        String accWhere = ACCOUNT_ID + " = ? ";
        return database.query(TABLE_CUSTOMER_ACCOUNT_INFO, null, accWhere, accWhereArgs, null, null, null, null);
    }

    public ArrayList<Period> getPeriods(int latestAccId){
        // getting periods info
        SQLiteDatabase database=getReadableDatabase();

        Cursor periodCursor = getCursorPeriods(latestAccId, database);

        ArrayList<Period> periods=new ArrayList<>();
        while (periodCursor.moveToNext()) {

            int periodId=periodCursor.getInt(periodCursor.getColumnIndex(PERIOD_ID));
            int periodNum=periodCursor.getInt(periodCursor.getColumnIndex(PERIOD_NUM));
            int periodAmount=periodCursor.getInt(periodCursor.getColumnIndex(PERIOD_PAID_AMOUNT));
            String periodDate=periodCursor.getString(periodCursor.getColumnIndex(PERIOD_DATE));
//            int periodAccId=periodCursor.getInt(periodCursor.getColumnIndex(PERIOD_ACCCOUNT_ID));

            Period period = new Period();
            period.setId(periodId);
            period.setAccountId(latestAccId);
            period.setNumPeriods(periodNum);
            period.setPaidAmount(periodAmount);
            period.setDate(periodDate);
            periods.add(period);
        }
        periodCursor.close();
        database.close();
        return periods;
    }

    private Cursor getCursorPeriods(int latestAccId, SQLiteDatabase database) {
        String[] periodWhereArgs = {latestAccId + ""};
        String periodWhere = PERIOD_ACCCOUNT_ID + " = ? ";
        return database.query(TABLE_PERIOD_INFO, null, periodWhere, periodWhereArgs, null, null, null, null);
    }

    public AccountInfo getAccountInformation(int customerId) {
        SQLiteDatabase database = getReadableDatabase();

        Customer customer=getCustomer(customerId);
        Log.d("LineDatabase", "getAccountInformation (Line:206) :" + customer);

        Account account=getAccount(customer.getLatestAccountId());

        ArrayList<Period> periods=getPeriods(customer.getLatestAccountId());

        Log.d("LineDatabase", "getAccountInformation (Line:261) :" + periods);

        AccountInfo accountInfo=new AccountInfo();
        accountInfo.setAccount(account);
        accountInfo.setCustomer(customer);
        accountInfo.setPeriods(periods);

        Log.d("LineDatabase", "getAccountInformation (Line:268) :" + accountInfo);

        database.close();
        return accountInfo;
    }

    public String getHtmlAccountInformation(int customerId) {
        Customer customer=getCustomer(customerId);

        SQLiteDatabase database = getReadableDatabase();

        StringBuilder builder=new StringBuilder("");

        Cursor cursorCustomer = getCursorCustomer(customerId, database);
        String htmlCustomer=getHtmlString(cursorCustomer);
        cursorCustomer.close();

        Cursor cursorAccount = getCursorAccount(customer.getLatestAccountId(), database);
        String htmlAccount=getHtmlString(cursorAccount);
        cursorAccount.close();

        Cursor cursorPeriods = getCursorPeriods(customer.getLatestAccountId(), database);
        String htmlPeriods=getHtmlString(cursorPeriods);
        cursorPeriods.close();

        builder.append(htmlCustomer);builder.append("<br><br><br>");
        builder.append(htmlAccount);builder.append("<br><br><br>");
        builder.append(htmlPeriods);

        database.close();
        return builder.toString();
    }

    /**
     * Which returns html String for the given cursor.
     * @param cursor cursor of the table.
     * @return which returns the table form of html for the given cursor.
     */
    public String getHtmlString(Cursor cursor) {
        StringBuilder html=new StringBuilder("");
        html.append("<table " +
                "border='1' cellpadding='0' cellspacing='0' width='200px' " +
                "style='border-collapse:collapse; border-style: solid;" +
                "  border-color: #ff0000 #00ff00 #0000ff rgb(250,0,255); '>");
        if (cursor != null) {
            int columnCount=cursor.getColumnCount();
            html.append("<tr>");
            for(int i=0; i<columnCount; i++){
                String colName=cursor.getColumnName(i);
                html.append("<th>"+colName+"</th>");
            }
            html.append("</tr>");

            while (cursor.moveToNext()){
                html.append("<tr>");
                for (int i=0; i<columnCount; i++){
                    String colValue=cursor.getString(i);
                    html.append("<td>"+colValue+"</td>");
                }
                html.append("</tr>");
            }
        }
        html.append("</table>");
        return html.toString();
    }

    /**
     * Which returns html String for the given qyery.
     * @param query query for the table.
     * @return which returns the table form of html for the given query.
     */
    public String getHtmlString(String query){
        SQLiteDatabase database=getReadableDatabase();
        Cursor cursor=database.rawQuery(query, null);
        String html=getHtmlString(cursor);
        cursor.close();
        database.close();
        return html;
    }

    public String getHtmlStringFromTable(String tableName)
    {
        return getHtmlString("select * from " + tableName);
    }
}
