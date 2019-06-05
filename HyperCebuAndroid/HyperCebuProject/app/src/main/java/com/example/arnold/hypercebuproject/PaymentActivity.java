package com.example.arnold.hypercebuproject;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class PaymentActivity extends AppCompatActivity {

    private ImageView QrImage;
    private Button purchased,savedAcopy;
    private EditText set_hypercardno;
    private TextView stv_ename, stv_elocation, stv_estartdate, stv_eenddate, stv_price, stv_tvCardNo;
    private String EditTextValue;
    public final static int QRcodeWidth = 500 ;
    private Bitmap bitmap ;
    private Bundle intent;
    private String card_no,card_amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("One moment...");
        progressDialog.show();

        intent = getIntent().getExtras();

        QrImage = (ImageView)findViewById(R.id.imageView);
        stv_ename = (TextView) findViewById(R.id.tv_ename);
        stv_ename.setText(intent.getString("tvEventTitle"));
        stv_elocation = (TextView) findViewById(R.id.tv_elocation);
        stv_elocation.setText(intent.getString("tvEventLocation"));
        stv_estartdate = (TextView) findViewById(R.id.tv_estartdate);
        stv_estartdate.setText(intent.getString("tvEventStartDate"));
        stv_eenddate = (TextView) findViewById(R.id.tv_eenddate);
        stv_eenddate.setText(intent.getString("tvEventEndDate"));
        stv_tvCardNo = (TextView) findViewById(R.id.tvCardNo);
        stv_price = (TextView) findViewById(R.id.tv_price);
        stv_price.setText(" "+intent.getString("type")+" ₱ "+intent.getString("price"));

        intent.getString("ticket_no");
        card_no = intent.getString("card_no");

        set_hypercardno = (EditText)findViewById(R.id.et_hypercardno);
        set_hypercardno.setText(card_no);
        set_hypercardno.setEnabled(false);

        purchased = (Button)findViewById(R.id.btn_purchased);
        progressDialog.dismiss();

        purchased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(PaymentActivity.this);
                alertDialog.setTitle(intent.getString("tvEventTitle"));
                alertDialog.setMessage("Confirm Transaction");

                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getJSONCard_Account(set_hypercardno.getText().toString());
                    }
                });

                alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();
            }
        });
//        savedAcopy = (Button) findViewById(R.id.btn_saveAcopy);
//        savedAcopy.setVisibility(View.INVISIBLE);
//        savedAcopy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                savedAcopy.setVisibility(View.INVISIBLE);
//                Bitmap bitmap = takeScreenshot();
//                saveBitmap(bitmap);
//                startActivity(new Intent(PaymentActivity.this,PurchaseActivity.class));
//                finish();
//            }
//        });

    }

    Bitmap TextToImageEncode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();
        int bitMatrixHeight = bitMatrix.getHeight();
        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];
        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;
            for (int x = 0; x < bitMatrixWidth; x++) {
                pixels[offset + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(R.color.QRCodeBlackColor):getResources().getColor(R.color.QRCodeWhiteColor);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);
        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }

    private String s_amount, s_card_no, cardno;
    private void CardAccount(String Result){
        cardno = set_hypercardno.getText().toString();
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(Result);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            if(set_hypercardno.length() == 0) {
                Toast.makeText(getApplicationContext(),"Enter Your Hyper Card No",Toast.LENGTH_LONG).show();
            } else {
                JSONObject jo = result.getJSONObject(0);
                s_card_no = jo.getString(Config.TAG_CARD_NO);
                s_amount = jo.getString(Config.TAG_AMOUNT);
                if (!cardno.equals(s_card_no)) {
                    Toast.makeText(getApplicationContext(), "Invalid Hyper Card No", Toast.LENGTH_SHORT).show();
                } else if (cardno.equals(s_card_no)) {
                    if(s_amount.equals("0")){
                        Toast.makeText(getApplicationContext(), "Your Current Balance is 0", Toast.LENGTH_SHORT).show();
                    } else {
                        String[] priceArray = stv_price.getText().toString().split(" ");
                        int cardamount = Integer.parseInt(s_amount);
                        int ticketprice = Integer.parseInt(priceArray[3]);
                        if (ticketprice > cardamount){
                            Toast.makeText(getApplicationContext(), "Your Current Balance is not enough", Toast.LENGTH_SHORT).show();
                        } else {
                            int newCard_Amount = cardamount - ticketprice;
//                          Toast.makeText(get ApplicationContext(), "Php " + newCard_Amount, Toast.LENGTH_LONG).show();
                            updateCard_Account(cardno,""+newCard_Amount);
                            updateTicket_Details(intent.getString("ticket_no"),intent.getString("qrcode"));
                            int qty = Integer.parseInt(intent.getString("quantity"));
                            updateTicket_Quantity(intent.getString("ticket_no"),intent.getString("type"),""+(qty - 1));

                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                            String currentDateandTime = sdf.format(new Date());

                            updateTicket_History(intent.getString("tvEventTitle"),intent.getString("tvEventLocation"),intent.getString("type"),""+intent.getString("price"),intent.getString("ticket_no"),intent.getString("tvEventEndDate"),intent.getString("qrcode"),cardno);
                            EditTextValue = intent.getString("tvEventTitle")+","+intent.getString("type")+","+intent.getString("price")+","+intent.getString("ticket_no")+","+intent.getString("qrcode")+","+intent.getString("tvEventEndDate")+","+currentDateandTime;
                            //+" "+intent.getString("tvEventEndDate")
                            try {
                                bitmap = TextToImageEncode(EditTextValue);
                                QrImage.setImageBitmap(bitmap);
                            } catch (WriterException e) {
                                e.printStackTrace();
                            }

                            stv_tvCardNo.setVisibility(View.INVISIBLE);
                            set_hypercardno.setEnabled(false);
                            set_hypercardno.setVisibility(View.INVISIBLE);
                            purchased.setEnabled(false);
                            purchased.setVisibility(View.INVISIBLE);
                        }
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
//    public Bitmap takeScreenshot() {
//        View rootView = findViewById(android.R.id.content).getRootView();
//        rootView.setDrawingCacheEnabled(true);
//        return rootView.getDrawingCache();
//    }
//
//    public void saveBitmap(Bitmap bitmap) {
//        File imagePath = new File(Environment.getExternalStorageDirectory() + "/HyperCebuTicket.png");
//        FileOutputStream fos;
//        try {
//            fos = new FileOutputStream(imagePath);
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
//            fos.flush();
//            fos.close();
//        } catch (IOException e) {
//            Log.e("GREC", e.getMessage(), e);
//        }
//    }

    private void getJSONCard_Account(String cardno){
        final String CardNo  = cardno;
        class GetJSON extends AsyncTask<Void,Void,String> {
            protected void onPreExecute() {
                super.onPreExecute();
            }

            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                CardAccount(result);
            }

            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_CARD_NO,CardNo);
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.CARD_ACCOUNT_URL,params);
                return res;
            }
        }
        GetJSON gj = new GetJSON();
        gj.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void updateCard_Account(String cardno, String cardamount){
        final String CardNo  = cardno;
        final String CardAmount  = cardamount;

        class GetJSON extends AsyncTask<Void,Void,String> {
            protected void onPreExecute() {
                super.onPreExecute();
            }

            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                CardAccount(result);
            }

            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_CARD_NO,CardNo);
                params.put(Config.KEY_AMOUNT,CardAmount);
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.UPDATE_CARD_ACCOUNT_URL,params);
                return res;
            }
        }
        GetJSON gj = new GetJSON();
        gj.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
    private void updateTicket_Details(String s_ticketno, String s_qrcode){
        final String Ticket_No  = s_ticketno;
        final String QR_Code  = s_qrcode;

        class GetJSON extends AsyncTask<Void,Void,String> {
            protected void onPreExecute() {
                super.onPreExecute();
            }

            protected void onPostExecute(String result) {
                super.onPostExecute(result);
            }

            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_TICKET_NO,Ticket_No);
                params.put(Config.KEY_QR_CODE,QR_Code);
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.UPDATE_TICKET_DETAILS_URL,params);
                return res;
            }
        }
        GetJSON gj = new GetJSON();
        gj.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void updateTicket_History(String s_event,String s_location ,String s_type, String s_price, String s_tno, String s_enddate, String s_qrcode, String s_hypercard){
        final String Event  = s_event;
        final String location  = s_location;
        final String Type  = s_type;
        final String Price  = s_price;
        final String t_no  = s_tno;
        final String enddate = s_enddate;
        final String QrCode  = s_qrcode;
        final String Hypercard  = s_hypercard;


        class GetJSON extends AsyncTask<Void,Void,String> {
            protected void onPreExecute() {
                super.onPreExecute();
            }

            protected void onPostExecute(String result) {
                super.onPostExecute(result);
            }

            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_TICKET_EVENT_HISTORY,Event);
                params.put(Config.KEY_TICKET_EVENT_LOCATION_HISTORY,location);
                params.put(Config.KEY_TICKET_TYPE_HISTORY,Type);
                params.put(Config.KEY_TICKET_PRICE_HISTORY,Price);
                params.put(Config.KEY_TICKET_NO_HISTORY,t_no);
                params.put(Config.KEY_TICKET_ENDDATE,enddate);
                params.put(Config.KEY_TICKET_QRCODE_HISTORY,QrCode);
                params.put(Config.KEY_TICKET_HYPERCARD_HISTORY,Hypercard);
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.INSERT_TICKET_HISTORY_URL,params);
                return res;
            }
        }
        GetJSON gj = new GetJSON();
        gj.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void updateTicket_Quantity(String ticketno, String type, String qty){
        final String  s_ticketno = ticketno;
        final String s_type  = type;
        final String s_qty  = qty;

        class GetJSON extends AsyncTask<Void,Void,String> {
            protected void onPreExecute() {
                super.onPreExecute();
            }

            protected void onPostExecute(String result) {
                super.onPostExecute(result);
            }

            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_TICKET_NO,s_ticketno);
                params.put(Config.KEY_TICKET_TYPE,s_type);
                params.put(Config.KEY_TICKET_QTY,s_qty);
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.UPDATE_TICKET_QUANTITY,params);
                return res;
            }
        }
        GetJSON gj = new GetJSON();
        gj.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onBackPressed() {
            backButtonHandler();
    }

    public void backButtonHandler(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(PaymentActivity.this);
        alertDialog.setTitle("Leave application?");
        alertDialog.setMessage("Are you sure you want to leave the application?");

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent logoutIntent = new Intent(PaymentActivity.this, PurchaseActivity.class);
                startActivity(logoutIntent);
                finish();
            }
        });

        alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }
}

