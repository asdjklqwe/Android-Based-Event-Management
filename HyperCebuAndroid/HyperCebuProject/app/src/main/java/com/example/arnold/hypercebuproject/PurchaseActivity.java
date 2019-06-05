package com.example.arnold.hypercebuproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class PurchaseActivity extends AppCompatActivity{

    private TextView tvEventTitle, tvEventLocation, tvEventStartDate, tvEventEndDate;
    private ListView lv_ticket;
    private String qrcode,ticket_no,type,price,quantity;
    private String event, location, description, startdate, enddate, starttime, endtime;
    private String card_no,card_amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        tvEventTitle = (TextView) findViewById(R.id.tvEventTitle);
        tvEventLocation = (TextView) findViewById(R.id.tvEventLocation);
        tvEventStartDate = (TextView) findViewById(R.id.tvEventStartDate);
        tvEventEndDate = (TextView) findViewById(R.id.tvEventEndDate);
        lv_ticket = (ListView) findViewById(R.id.lv_ticket_Details);

        Bundle extras = getIntent().getExtras();
        card_no = extras.getString("card_no");

        getEvent();
        getTicket();
    }

    private void getEvent() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("One moment...");
        progressDialog.show();

        String url = Config.EVENT_URL;
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PurchaseActivity.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response){

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            JSONObject data = result.getJSONObject(0);

            event = data.getString(Config.KEY_EVENT);
            location = data.getString(Config.KEY_LOCATION);
            description = data.getString(Config.KEY_DESCRIPTION);
            startdate = data.getString(Config.KEY_STARTDATE);
            enddate = data.getString(Config.KEY_ENDDATE);
            starttime = data.getString(Config.KEY_STARTTIME);
            endtime = data.getString(Config.KEY_ENDTIME);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        tvEventTitle.setText(event);
        tvEventLocation.setText(location);
        tvEventStartDate.setText(startdate);
        tvEventEndDate.setText(enddate);
    }

    public void getTicket() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Config.TICKET_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                final ArrayList<HashMap<String, String>> list = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("tickets");
                    for(int i = 0; i<array.length(); i++){
                        JSONObject o = array.getJSONObject(i);
                        if(o.getString("quantity").equals("0")){
                        }else if(o.getString("deleted").equals("1")){
                        } else {
                            HashMap<String, String> data = new HashMap<>();
                            data.put("ticket_no", o.getString("ticket_no"));
                            data.put("type", o.getString("type"));
                            data.put("price", o.getString("price"));
                            data.put("quantity", o.getString("quantity"));
                            list.add(data);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                final ListAdapter adapter = new SimpleAdapter(
                        PurchaseActivity.this,list, R.layout.activity_purchase_holder,
                        new String[]{"ticket_no","type","price","quantity"},
                        new int[]{R.id.tv_ticket_no_holder,R.id.tv_type_holder
                                ,R.id.tv_price_holder,R.id.tv_quantity_holder}){
                    @Override
                    public View getView(final int position, View convertView, ViewGroup parent){
                        View view = super.getView(position,convertView,parent);
                        View btn_accept =view.findViewById(R.id.btn_purchase_holder);
                        btn_accept.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                @SuppressWarnings("unchecked")
                                HashMap<String,String> booking =(HashMap<String, String>)lv_ticket.getItemAtPosition(position);
                                getJSONTicket_Details(booking.get("ticket_no"));
                                ticket_no = booking.get("ticket_no");
                                type = booking.get("type");
                                price = booking.get("price");
                                quantity = booking.get("quantity");
                            }
                        });
                        return view;
                    }
                };
                lv_ticket.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG). show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void TicketDetails(String Result){
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(Result);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            JSONObject jo = result.getJSONObject(0);
            qrcode = jo.getString(Config.TAG_QR_CODE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void getJSONTicket_Details(String ticket_No){
        final String Ticket_No  = ticket_No;
        class GetJSON extends AsyncTask<Void,Void,String> {
            protected void onPreExecute() {
                super.onPreExecute();
            }

            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                TicketDetails(result);
                Intent intent = new Intent(PurchaseActivity.this, PaymentActivity.class);
                intent.putExtra("tvEventTitle", tvEventTitle.getText().toString());
                intent.putExtra("tvEventLocation", tvEventLocation.getText().toString());
                intent.putExtra("tvEventStartDate", tvEventStartDate.getText().toString());
                intent.putExtra("tvEventEndDate", tvEventEndDate.getText().toString());
                intent.putExtra("ticket_no", ticket_no);
                intent.putExtra("type", type);
                intent.putExtra("price", price);
                intent.putExtra("quantity", quantity);
                intent.putExtra("qrcode", qrcode);
                intent.putExtra("card_no",card_no);
                startActivity(intent);
            }

            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_TICKET_NO,Ticket_No);
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.TICKET_DETAILS_URL,params);
                return res;
            }
        }
        GetJSON gj = new GetJSON();
        gj.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }


}
