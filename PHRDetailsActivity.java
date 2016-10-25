package com.TechM.QSpace;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;
import android.widget.Toast;

import java.io.InputStream;
import java.util.List;

import butterknife.Bind;

public class PHRDetailsActivity extends AppCompatActivity {

    private ListView listViewObj;
    private ItemArrayAdapter itemArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phr);
        //ButterKnife.bind(this);


        TableRow.LayoutParams wrapWrapTableRowParams = new     TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        int[] fixedColumnWidths = new int[]{20, 20, 20, 20, 20, 20};
        int[] scrollableColumnWidths = new int[]{30, 30, 30, 30, 30, 30};
        //int fixedRowHeight = 60;
        int fixedHeaderHeight = 65;

        TableRow row = new TableRow(this);
        //header (fixed vertically)
        TableLayout header = (TableLayout) findViewById(R.id.table_header);
        row.setLayoutParams(wrapWrapTableRowParams);
        row.setGravity(Gravity.CENTER);

        row.addView(makeTableRowWithText("Project Name", fixedColumnWidths[0]));
        row.addView(makeTableRowWithText("Project ID", fixedColumnWidths[1]));
        row.addView(makeTableRowWithText("Submit Status", fixedColumnWidths[2]));
        row.addView(makeTableRowWithText("PHI%", fixedColumnWidths[3]));
        row.addView(makeTableRowWithText("Prism", fixedColumnWidths[4]));
        row.addView(makeTableRowWithText("Risk RAG", fixedColumnWidths[5]));

        header.addView(row);

        //header (fixed horizontally)


        InputStream inputStream = getResources().openRawResource(R.raw.phr_data);
        CsvFileReader csvFile = new CsvFileReader(inputStream);
        List<String[]>  phrList = csvFile.read();



        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        lp.setMargins(2,2, 2, 2);

          /*  row = new TableRow(this);
            row.setLayoutParams(wrapWrapTableRowParams);
            row.setGravity(Gravity.CENTER);
            row.setBackgroundColor(Color.LTGRAY);
        row.setLayoutParams(lp);
            row.addView(makeTableRowWithText("Kotak Wallet Testing FB (000000000023929)", scrollableColumnWidths[1]));
            row.addView(makeTableRowWithText("Click for NC Description", scrollableColumnWidths[2]));
            row.addView(makeTableRowWithText("1", scrollableColumnWidths[2]));
            row.addView(makeTableRowWithText("10", scrollableColumnWidths[3]));
            row.addView(makeTableRowWithText("Prepare Plan Of Action", scrollableColumnWidths[4]));
            row.addView(makeTableRowWithText("New", scrollableColumnWidths[5]));
            row.setClickable(true);
            row.setOnClickListener(new View.OnClickListener() {


                public void onClick(View arg0) {
                    Intent myIntent = new Intent(arg0.getContext(), IqaDetailActivity.class);
                    startActivity(myIntent);
                    arg0.setBackgroundColor(Color.GRAY);

                }
            });

            header.addView(row);*/

        row = new TableRow(this);
        row.setLayoutParams(wrapWrapTableRowParams);
        row.setGravity(Gravity.CENTER);
        row.setBackgroundColor(Color.LTGRAY);
        row.setLayoutParams(lp);
        row.addView(makeTableRowWithText("CSI CAM", scrollableColumnWidths[1]));
        row.addView(makeTableRowWithText("000000000005645", scrollableColumnWidths[2]));
        row.addView(makeTableRowWithText("Pending with PM", scrollableColumnWidths[2]));
        row.addView(makeTableRowWithText("100%", scrollableColumnWidths[3]));
        row.addView(makeTableRowWithText("Green", scrollableColumnWidths[4]));
        row.addView(makeTableRowWithText("LOW", scrollableColumnWidths[5]));
        row.setClickable(false);
        row.setBackgroundResource(R.drawable.shape);
        row.setOnClickListener(new View.OnClickListener() {


            public void onClick(View arg0) {
                Intent myIntent = new Intent(arg0.getContext(), DetailedPHRActivity.class);
                startActivity(myIntent);
                arg0.setBackgroundColor(Color.GRAY);

            }
        });

        header.addView(row);
//2nd row
        row = new TableRow(this);
        row.setLayoutParams(wrapWrapTableRowParams);
        row.setGravity(Gravity.CENTER);
        row.setBackgroundColor(Color.LTGRAY);
        row.setLayoutParams(lp);
        row.addView(makeTableRowWithText("CSI Service", scrollableColumnWidths[1]));
        row.addView(makeTableRowWithText("000000000010770", scrollableColumnWidths[2]));
        row.addView(makeTableRowWithText("Pending with PM", scrollableColumnWidths[2]));
        row.addView(makeTableRowWithText("100%", scrollableColumnWidths[3]));
        row.addView(makeTableRowWithText("Green", scrollableColumnWidths[4]));
        row.addView(makeTableRowWithText("LOW", scrollableColumnWidths[5]));
        row.setClickable(true);
        row.setBackgroundResource(R.drawable.shape);
        /*row.setOnClickListener(new View.OnClickListener() {


            public void onClick(View arg0) {
                Intent myIntent = new Intent(arg0.getContext(), DetailedPHRActivity.class);
                startActivity(myIntent);
                arg0.setBackgroundColor(Color.GRAY);

            }
        });*/

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
        //recyclableTextView.setHeight(fixedHeightInPixels);
        return recyclableTextView;
    }


    public void close(){

        finish();

    }
}


