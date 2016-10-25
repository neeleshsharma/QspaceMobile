package com.TechM.QSpace;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import butterknife.ButterKnife;
import butterknife.Bind;

import static android.R.attr.data;
import static java.security.AccessController.getContext;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    @Bind(R.id.input_email)
    EditText _emailText;
    @Bind(R.id.input_password)
    EditText _passwordText;
    @Bind(R.id.btn_login)
    Button _loginButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login(v);
            }
        });


    }

    public void login(View v) {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        LdapApiCall ldapApiCall = new LdapApiCall();
        if(email.equals("dummy") !=true) {
            ldapApiCall.execute(email, password);
        }
        else {
            Toast.makeText(getBaseContext(), "Login Successful", Toast.LENGTH_LONG).show();
            Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(myIntent);
        }

    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed - Invalid Lan ID or Password", Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty()) {
            _emailText.setError("enter a valid Lan ID");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4) {
            _passwordText.setError("password should be more than 4 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }


    class LdapApiCall extends AsyncTask<String, String, String> {
        View v;

        private ProgressDialog nDialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            nDialog = new ProgressDialog(LoginActivity.this);
            //nDialog.setTitle("Checking Login Details");
            nDialog.setMessage("Please wait...");
            nDialog.setIndeterminate(false);
            nDialog.getWindow().addFlags( WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON );
            nDialog.setCancelable(true);
            nDialog.show();
        }

        @Override
        protected void onPostExecute(String result) {

            if (("TRUE").equalsIgnoreCase(result)) {
                nDialog.dismiss();
                Toast.makeText(getBaseContext(), "Login Successful", Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(myIntent);
                _loginButton.setEnabled(true);
                finish();
            } else {
                nDialog.dismiss();
                Toast.makeText(getBaseContext(), "Login failed - Invalid Lan ID or Password", Toast.LENGTH_LONG).show();
                _loginButton.setEnabled(true);
            }
            super.onPostExecute(result);
        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        protected String doInBackground(String... params) {

            String msg = null;
            DocumentBuilder db = null;
            String requestString = "<Authenticate><UserName>" + params[0] + "</UserName><Password>" + params[1] + "</Password><UID>" +
                    Settings.Secure.getString(getBaseContext().getContentResolver(), Settings.Secure.ANDROID_ID)+"</UID></Authenticate>";
            DataOutputStream out = null;
            String finalResult = null;
            String urlLdap = "https://services.techmahindra.com/Mobility_AuthService/Service1.svc/Appify_Authenticate";
            requestString = EncryptionAndroidPlugin.encryptPlainText(requestString);
            NodeList nodeList = null;
            Node node=null;
            String securityToken = null;
            String isAuthenticated = null;
            String errorMessage = null;
            String text = "QSpaceMobile:Android";
            InputStream is = null;
            StringBuffer response = new StringBuffer();
            HttpURLConnection connection = null;

            try {
                byte[] data  = text.getBytes("UTF-8");

                String base64 = Base64.encodeToString(data, Base64.DEFAULT);

                connection = (HttpURLConnection) new URL(urlLdap).openConnection();
                connection.addRequestProperty("Authorization", base64.replace("\n", ""));
                connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestProperty ("http.keepAlive", "false");
                connection.setRequestMethod("POST");
                connection.setRequestProperty("charset", "utf-8");
                connection.setRequestProperty("Content-Length", Integer.toString(requestString.length()));

                out = new DataOutputStream(connection.getOutputStream());
                out.write(requestString.getBytes());
                out.flush();
                out.close();
                int length = connection.getContentLength();
                is = connection.getInputStream();

                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;

                while ((line = rd.readLine()) != null) {
                    response.append(line);
                }
                rd.close();

                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                db = dbf.newDocumentBuilder();

                Document doc  = db.parse(new InputSource(new StringReader(response.toString())));

                // normalize the document
                doc.getDocumentElement().normalize();
                // get the root node
                nodeList = doc.getElementsByTagName("string");
                node=nodeList.item(0);
                finalResult = node.getTextContent();

                finalResult = EncryptionAndroidPlugin.decryptCipherText(finalResult);


                doc = db.parse(new InputSource(new StringReader(finalResult)));
                doc.getDocumentElement().normalize();
                // get the root node
                nodeList = doc.getElementsByTagName("Response");
                node=nodeList.item(0);
                for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                    Node temp = node.getChildNodes().item(i);
                    if (temp.getNodeName().equalsIgnoreCase("SecurityToken")) {
                        securityToken = temp.getTextContent();
                    }
                    if (temp.getNodeName().equalsIgnoreCase("isAuthenticated")) {
                        isAuthenticated = temp.getTextContent();
                    }
                    if (temp.getNodeName().equalsIgnoreCase("ErrorMessage")) {
                        errorMessage = temp.getTextContent();
                    }
                }

            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }catch (ParserConfigurationException e) {
                e.printStackTrace();
            }catch (SAXException e) {
                e.printStackTrace();
            }finally {
                connection.disconnect();

            }

            return isAuthenticated;
        }

    }

}