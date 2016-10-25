package com.TechM.QSpace;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.button_iqa)Button _iqaButton;
    @Bind(R.id.button_phr)Button _phrButton;
    private static final String TAG = "IQAActivity";
    private static final String TAG2 = "PHRDetailsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button_iqa = (Button) findViewById(R.id.button_iqa);
        Button button_phr = (Button) findViewById(R.id.button_phr);
        button_iqa.setOnClickListener(this);
        button_phr.setOnClickListener(this);

/*
        ButterKnife.bind(this);
        _iqaButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), FetchActivity.class);
                startActivity(myIntent);
                fetchIqaDetails();
            }
        });
*/
    }




    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_iqa:

                Intent myIntent = new Intent(v.getContext(), FetchActivity.class);
                startActivity(myIntent);
              //  fetchIqaDetails();
                break;
            case R.id.button_phr:
                Intent myIntent1 = new Intent(v.getContext(), PHRDetailsActivity.class);
                startActivity(myIntent1);
               // fetchphrDetails();
                break;


        }
    }
        public void fetchIqaDetails(){
        {
            Log.d(TAG, "IQA Button Screen");



            _iqaButton.setEnabled(false);

            final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Fetching data...");
            progressDialog.show();


            // TODO: Implement your own authentication logic here.

            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            // On complete call either onLoginSuccess or onLoginFailed
                            onLoginSuccess();
                            // onLoginFailed();
                            progressDialog.dismiss();
                        }
                    }, 3000);
        }
    }

    public void fetchphrDetails(){
        {
            Log.d(TAG2, "PHR Details Screen");



            _phrButton.setEnabled(false);

            final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Fetching data...");
            progressDialog.show();


            // TODO: Implement your own authentication logic here.

            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            // On complete call either onLoginSuccess or onLoginFailed
                            onLoginSuccess();
                            // onLoginFailed();
                            progressDialog.dismiss();
                        }
                    }, 3000);
        }
    }

    public void onLoginSuccess() {
        _iqaButton.setEnabled(true);
        _phrButton.setEnabled(true);
        //finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /*public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }*/
}
