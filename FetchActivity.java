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
import java.io.InputStream;
import java.util.List;

import butterknife.Bind;

public class FetchActivity extends AppCompatActivity {

    private ListView listViewObj;
    private ItemArrayAdapter itemArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch);
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

        row.addView(makeTableRowWithText("Project", fixedColumnWidths[0]));
        row.addView(makeTableRowWithText("NC Description", fixedColumnWidths[1]));
        row.addView(makeTableRowWithText("No. Of NC's", fixedColumnWidths[2]));
        row.addView(makeTableRowWithText("Age(in Days)", fixedColumnWidths[3]));
        row.addView(makeTableRowWithText("Action To Be Taken", fixedColumnWidths[4]));
        row.addView(makeTableRowWithText("NC Category", fixedColumnWidths[5]));

        header.addView(row);

        //header (fixed horizontally)


        InputStream inputStream = getResources().openRawResource(R.raw.iqa_data);
        CsvFileReader csvFile = new CsvFileReader(inputStream);
        List<String[]>  iqaList = csvFile.read();



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
        //Use below code only for demo purpose
        row = new TableRow(this);
        row.setLayoutParams(wrapWrapTableRowParams);
        row.setGravity(Gravity.CENTER);
        row.setBackgroundColor(Color.LTGRAY);
        row.setLayoutParams(lp);
        row.addView(makeTableRowWithText("THA_CRC_JAN15_FB_IMPL_CRM (000000000017623)", scrollableColumnWidths[1]));
        row.addView(makeTableRowWithText("Click for NC Description", scrollableColumnWidths[2]));
        row.addView(makeTableRowWithText("2", scrollableColumnWidths[2]));
        row.addView(makeTableRowWithText("33", scrollableColumnWidths[3]));
        row.addView(makeTableRowWithText("Prepare Plan Of Action", scrollableColumnWidths[4]));
        row.addView(makeTableRowWithText("New", scrollableColumnWidths[5]));
        row.setClickable(true);
        row.setBackgroundResource(R.drawable.shape);
        row.setOnClickListener(new View.OnClickListener() {


            public void onClick(View arg0) {
                Intent myIntent = new Intent(arg0.getContext(), IqaDetailActivity.class);
                startActivity(myIntent);
                arg0.setBackgroundColor(Color.GRAY);

            }
        });

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


