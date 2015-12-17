package com.line.krishna.investmentapp.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Krishna on 12/12/2015.
 */
public class Util {

    private static final String PREFERENCE = "Preference";

    public static final String DATE_FORMAT="dd-MM-yyyy";
    public static final String LINE_ID = "LINE_ID";

    public static String getDateString(Date date, String dateFormat){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(dateFormat, Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    public static int getDefaultLineId(Context context){
        SharedPreferences preferences= getUtilSharedPreference(context);
        return preferences.getInt(LINE_ID, 0);
    }

    public static boolean setDefaultLineId(Context context, int lineId){
        SharedPreferences preferences=getUtilSharedPreference(context);
        return preferences.edit().putInt(LINE_ID, lineId).commit();
    }

    private static SharedPreferences getUtilSharedPreference(Context context) {
        return context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
    }
}
