package com.line.krishna.investmentapp.database;

import android.database.Cursor;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;

import au.com.bytecode.opencsv.CSVWriter;

public class ExportDatabaseToCSV {

    public static File exportDataBaseIntoCSV(Cursor curCSV) {

        File exportDir = new File(Environment.getExternalStorageDirectory(), "DbExport");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }
        File file = new File(exportDir, "csv_file_" + System.currentTimeMillis() + ".csv");

        try {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            csvWrite.writeNext(curCSV.getColumnNames());

            int columnCount = curCSV.getColumnCount();
            while (curCSV.moveToNext()) {
                String values[] = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    values[i] = curCSV.getString(i);
                }
                //Which column you want to export you can add over here...
                csvWrite.writeNext(values);
            }

            csvWrite.close();
            curCSV.close();
        } catch (Exception sqlEx) {
            Log.e("Error:", sqlEx.getMessage(), sqlEx);
        }
        return file;
    }
}