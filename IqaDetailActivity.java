package com.TechM.QSpace;

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

public class IqaDetailActivity extends AppCompatActivity {

    private ListView listViewObj;
    private ItemArrayAdapter itemArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iqadetail);


        TableRow.LayoutParams wrapWrapTableRowParams = new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        int[] fixedColumnWidths = new int[]{30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
        int[] scrollableColumnWidths = new int[]{30, 30, 30, 30, 30, 30,30, 30, 30, 30, 30};
        int fixedRowHeight = 60;
        int fixedHeaderHeight = 65;

        TableRow row = new TableRow(this);
        //header (fixed vertically)
        TableLayout header = (TableLayout) findViewById(R.id.table_header);
        row.setLayoutParams(wrapWrapTableRowParams);
        row.setGravity(Gravity.CENTER);

        row.addView(makeTableRowWithText("Project", fixedColumnWidths[0]));
        row.addView(makeTableRowWithText("NC Description", fixedColumnWidths[1]));
        row.addView(makeTableRowWithText("Actual Date", fixedColumnWidths[2]));
        row.addView(makeTableRowWithText("Age(In Days)", fixedColumnWidths[3]));
        row.addView(makeTableRowWithText("Immediate Action Plan", fixedColumnWidths[4]));
        row.addView(makeTableRowWithText("Corrective Action Plan", fixedColumnWidths[5]));
        row.addView(makeTableRowWithText("Expected Close Date", fixedColumnWidths[6]));
        row.addView(makeTableRowWithText("Resolution Date", fixedColumnWidths[7]));
        row.addView(makeTableRowWithText("Action To Be Taken", fixedColumnWidths[8]));
        row.addView(makeTableRowWithText("Focus Area", fixedColumnWidths[9]));
        row.addView(makeTableRowWithText("NC Category", fixedColumnWidths[10]));

        header.addView(row);

        //header (fixed horizontally)


        InputStream inputStream = getResources().openRawResource(R.raw.iqa_data);
        CsvFileReader csvFile = new CsvFileReader(inputStream);
        List<String[]> iqaList = csvFile.read();


        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        lp.setMargins(2,2, 2, 2);
            //TextView fixedView = makeTableRowWithText("Record " + i,   scrollableColumnWidths[0], fixedRowHeight);

            //header.addView(fixedView);
            row = new TableRow(this);
            row.setLayoutParams(wrapWrapTableRowParams);
            row.setGravity(Gravity.CENTER);
            row.setBackgroundColor(Color.LTGRAY);
            row.setLayoutParams(lp);
            row.addView(makeTableRowWithText("THA_CRC_JAN15_FB_IMPL_CRM (000000000017623)", scrollableColumnWidths[1]));
            row.addView(makeTableRowWithText("Software Project Plan is not reviewed and Approved", scrollableColumnWidths[2]));
            row.addView(makeTableRowWithText("09/09/2016", scrollableColumnWidths[2]));
            row.addView(makeTableRowWithText("33", scrollableColumnWidths[3]));
            row.addView(makeTableRowWithText("TechM has presented the Methodology for the entire project and seeking customer acceptance.", scrollableColumnWidths[4]));
            row.addView(makeTableRowWithText("Workplan has been prepared as per TechM (Approach & Methodology, SOW timelines)", scrollableColumnWidths[5]));
        row.addView(makeTableRowWithText("10/07/2016", scrollableColumnWidths[6]));
        row.addView(makeTableRowWithText("10/07/2016", scrollableColumnWidths[7]));
        row.addView(makeTableRowWithText("Update Plan Of Action", scrollableColumnWidths[8]));
        row.addView(makeTableRowWithText("Mgmt Methodology-Project (including QPM, Causal Analysis & Improvement) and the applicable Engg processes", scrollableColumnWidths[9]));
        row.addView(makeTableRowWithText("New", scrollableColumnWidths[10]));
        row.setBackgroundResource(R.drawable.shape);
            header.addView(row);
//2nd row
        row = new TableRow(this);
        row.setLayoutParams(wrapWrapTableRowParams);
        row.setGravity(Gravity.CENTER);
        row.setBackgroundColor(Color.LTGRAY);
        row.setLayoutParams(lp);
        row.addView(makeTableRowWithText("THA_CRC_JAN15_FB_IMPL_CRM (000000000017623)", scrollableColumnWidths[1]));
        row.addView(makeTableRowWithText("Traceability Matrix availability", scrollableColumnWidths[2]));
        row.addView(makeTableRowWithText("09/09/2016", scrollableColumnWidths[2]));
        row.addView(makeTableRowWithText("33", scrollableColumnWidths[3]));
        row.addView(makeTableRowWithText("NA", scrollableColumnWidths[4]));
        row.addView(makeTableRowWithText("testCase will be prepared when build starts", scrollableColumnWidths[5]));
        row.addView(makeTableRowWithText("09/28/2016", scrollableColumnWidths[6]));
        row.addView(makeTableRowWithText("09/28/2016", scrollableColumnWidths[7]));
        row.addView(makeTableRowWithText("Update Plan Of Action", scrollableColumnWidths[8]));
        row.addView(makeTableRowWithText("Mgmt Methodology-Project (including QPM, Causal Analysis & Improvement) and the applicable Engg processes", scrollableColumnWidths[9]));
        row.addView(makeTableRowWithText("New", scrollableColumnWidths[10]));
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
        //recyclableTextView.setHeight(fixedHeightInPixels);
        return recyclableTextView;
    }



}
