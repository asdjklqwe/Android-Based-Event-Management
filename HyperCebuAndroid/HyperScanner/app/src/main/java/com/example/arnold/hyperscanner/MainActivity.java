package com.example.arnold.hyperscanner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //View Objects
    private Button buttonScan,buttonSave;
    private TextView textViewEvent, textViewType, textViewPrice ,textViewTicketNo, textViewDOP,textViewQrCode;
    private String [] resultt;
    //qr code scanner object
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View objects
        buttonScan = (Button) findViewById(R.id.buttonScan);
        buttonSave = (Button) findViewById(R.id.buttonSave);
        textViewEvent = (TextView) findViewById(R.id.textViewEvent);
        textViewType = (TextView) findViewById(R.id.textViewType);
        textViewPrice = (TextView) findViewById(R.id.textViewPrice);
        textViewTicketNo = (TextView) findViewById(R.id.textViewTicketNo);
        textViewDOP = (TextView) findViewById(R.id.textViewDatePurchase);
        textViewQrCode = (TextView) findViewById(R.id.textViewQrCode);

        //intializing scan object
        qrScan = new IntentIntegrator(this);

        //attaching onclick listener
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrScan.initiateScan();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textViewTicketNo.length() == 0 || textViewQrCode.length() == 0) {
                } else {
                    updateTicket_Details(textViewTicketNo.getText().toString(),textViewQrCode.getText().toString());
                }
                //Toast.makeText(MainActivity.this, textViewTicketNo.getText().toString() +" "+textViewQrCode.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "QR Code Not Found", Toast.LENGTH_LONG).show();
            } else {
                resultt = result.getContents().split(",");
                UsedTicket_Details(resultt[3], resultt[4]);
                //Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void updateTicket_Details(String s_ticketno, String s_qrcode){
        final String Ticket_No  = s_ticketno.trim();
        final String QR_Code  = s_qrcode.trim();

        class GetJSON extends AsyncTask<Void,Void,String> {
            ProgressDialog progressDialog;
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(MainActivity.this,"Verifying ...","Wait...",false,false);
            }

            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this,"Verified",Toast.LENGTH_LONG).show();
                textViewEvent.setText("");
                textViewType.setText("");
                textViewPrice.setText("");
                textViewTicketNo.setText("");
                textViewDOP.setText("");
                textViewQrCode.setText("");
            }

            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_TICKET_NO,Ticket_No);
                params.put(Config.KEY_QR_CODE,QR_Code);
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.UPDATE_STATUS_TICKET_DETAILS_URL,params);
                return res;
            }
        }
        GetJSON gj = new GetJSON();
        gj.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void TicketDetails(String Result){
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(Result);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            JSONObject jo = result.getJSONObject(0);
            String status = jo.getString(Config.TAG_STATUS);
            //Toast.makeText(MainActivity.this, status, Toast.LENGTH_LONG).show();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US); //yyyy-MM-dd HH:mm:ss
            Date currentdate = new Date();
            Date endDate = sdf.parse(resultt[5]);

            if (status.equals("1")) {
                Toast.makeText(MainActivity.this, "Used Ticket", Toast.LENGTH_LONG).show();
            } else if (currentdate.after(endDate)){
                Toast.makeText(MainActivity.this, "Expired Ticket", Toast.LENGTH_LONG).show();
            } else {
                textViewEvent.setText(resultt[0]);
                textViewType.setText(resultt[1]);
                textViewPrice.setText(resultt[2]);
                textViewTicketNo.setText(resultt[3]);
                textViewQrCode.setText(resultt[4]);
                //Toast.makeText(this, resultt[5], Toast.LENGTH_SHORT).show();
                textViewDOP.setText(resultt[6]);
            }
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void UsedTicket_Details(String s_ticketno, String s_qrcode){
        final String Ticket_No  = s_ticketno.trim();
        final String QR_Code  = s_qrcode.trim();

        class GetJSON extends AsyncTask<Void,Void,String> {
            protected void onPreExecute() {
                super.onPreExecute();
            }

            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                TicketDetails(result);
            }

            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_TICKET_NO,Ticket_No);
                params.put(Config.KEY_QR_CODE,QR_Code);
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.USED_TICKET_DETAILS_URL,params);
                return res;
            }
        }
        GetJSON gj = new GetJSON();
        gj.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
