package com.TechM.QSpace;

/**
 * Created by Sharma on 20/10/2016.
 */

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;



import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;
import java.io.InputStream;
import java.util.List;

import butterknife.Bind;

public class DetailedPHRActivity extends AppCompatActivity {

    private ListView listViewObj;
    private ItemArrayAdapter itemArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailedphr);


        TableRow.LayoutParams wrapWrapTableRowParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        int[] fixedColumnWidths = new int[]{30, 30, 30, 30, 30,30,30};
        int[] scrollableColumnWidths = new int[]{30, 30, 30, 30, 30,30,30};
        int fixedRowHeight = 60;
        int fixedHeaderHeight = 65;

        TableRow row = new TableRow(this);
        //header (fixed vertically)
        TableLayout header = (TableLayout) findViewById(R.id.table_header);
        row.setLayoutParams(wrapWrapTableRowParams);
        row.setGravity(Gravity.CENTER);

        row.addView(makeTableRowWithText("Project", fixedColumnWidths[0]));
        row.addView(makeTableRowWithText("Submit Status", fixedColumnWidths[1]));
        row.addView(makeTableRowWithText("Customer-Perception ", fixedColumnWidths[2]));
        row.addView(makeTableRowWithText("Budget / Financial ", fixedColumnWidths[3]));
        row.addView(makeTableRowWithText("Engineering Quality ", fixedColumnWidths[4]));
        row.addView(makeTableRowWithText("Risk", fixedColumnWidths[4]));
        row.addView(makeTableRowWithText("Resource", fixedColumnWidths[4]));


        header.addView(row);

        //header (fixed horizontally)


        InputStream inputStream = getResources().openRawResource(R.raw.iqa_data);
        CsvFileReader csvFile = new CsvFileReader(inputStream);
        List<String[]> iqaList = csvFile.read();


        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        lp.setMargins(2,2, 2, 2);
        //TextView fixedView = makeTableRowWithText("Record " + i,   scrollableColumnWidths[0], fixedRowHeight);

        //header.addView(fixedView);
        row = new TableRow(this);
        row.setLayoutParams(wrapWrapTableRowParams);
        row.setGravity(Gravity.CENTER);
        row.setBackgroundColor(Color.LTGRAY);
        row.setLayoutParams(lp);
        row.addView(makeTableRowWithText("CSI CAM", scrollableColumnWidths[1]));
        row.addView(makeTableRowWithText("Pending with PM", scrollableColumnWidths[2]));
        row.addView(makeTableRowWithText("GREEN", scrollableColumnWidths[2]));
        row.addView(makeTableRowWithText("GREEN", scrollableColumnWidths[3]));
        row.addView(makeTableRowWithText("GREEN", scrollableColumnWidths[4]));
        row.addView(makeTableRowWithText("GREEN", scrollableColumnWidths[5]));
        row.addView(makeTableRowWithText("GREEN", scrollableColumnWidths[6]));
        row.setBackgroundResource(R.drawable.shape);


        header.addView(row);



    }
    //util method
    private TextView recyclableTextView;
    public TextView makeTableRowWithText(String text, int widthInPercentOfScreenWidth) {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        recyclableTextView = new TextView(this);
        recyclableTextView.setText(text);
        recyclableTextView.setTextColor(Color.BLACK);
        recyclableTextView.setTextSize(15);
        recyclableTextView.setWidth(widthInPercentOfScreenWidth * screenWidth / 100);
       // recyclableTextView.setBackgroundResource(R.);
        //recyclableTextView.setHeight(fixedHeightInPixels);
        return recyclableTextView;
    }



}
