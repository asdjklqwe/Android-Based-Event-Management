package com.example.arnold.hypercebuproject;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TicketActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActionBarDrawerToggle toggle;
    private ListView lv_ticket_history;
    private String card_no, card_amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        lv_ticket_history = (ListView) findViewById(R.id.lv_ticket_history);
        lv_ticket_history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(TicketActivity.this, PurchasedTicket.class);
                @SuppressWarnings("unchecked")
                HashMap<String,String> data = (HashMap<String,String>)parent.getItemAtPosition(position);
                String event_name = data.get(Config.TAG_TICKET_EVENT_HISTORY);
                intent.putExtra(Config.TAG_TICKET_EVENT_HISTORY,event_name);
                String event_location = data.get(Config.TAG_TICKET_EVENT_LOCATION_HISTORY);
                intent.putExtra(Config.TAG_TICKET_EVENT_LOCATION_HISTORY,event_location);
                String ticket_type = data.get(Config.TAG_TICKET_TYPE_HISTORY);
                intent.putExtra(Config.TAG_TICKET_TYPE_HISTORY,ticket_type);
                String ticket_price = data.get(Config.TAG_TICKET_PRICE_HISTORY);
                intent.putExtra(Config.TAG_TICKET_PRICE_HISTORY,ticket_price);
                String ticket_qr = data.get(Config.TAG_TICKET_QRCODE_HISTORY);
                intent.putExtra(Config.TAG_TICKET_QRCODE_HISTORY,ticket_qr);
                String hcard_no = data.get(Config.TAG_TICKET_HYPERCARD_HISTORY);
                intent.putExtra(Config.TAG_TICKET_HYPERCARD_HISTORY,hcard_no);
                String ticket_enddate = data.get(Config.TAG_TICKET_ENDDATE);
                intent.putExtra(Config.TAG_TICKET_ENDDATE,ticket_enddate);
                String ticket_no = data.get(Config.TAG_TICKET_NO_HISTORY);
                intent.putExtra(Config.TAG_TICKET_NO_HISTORY,ticket_no);
                String date_of_purchased = data.get(Config.TAG_TICKET_DATEOFPURCHASED_HISTORY);
                intent.putExtra(Config.TAG_TICKET_DATEOFPURCHASED_HISTORY,date_of_purchased);
                startActivity(intent);
            }
        });

        Bundle extras = getIntent().getExtras();
        card_no = extras.getString("card_no");
        card_amount = extras.getString("Card_Amount");

        getTicket_History(card_no);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView navViewer = (TextView) headerView.findViewById(R.id.tvNavViewer);
        navViewer.setText("Card No.: "+card_no+"\nCard Amount: ₱"+card_amount);
    }
    private void TicketHistory(String json) {
        final ArrayList<HashMap<String, String>> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
                for (int i = 0; i < result.length(); i++) {
                    JSONObject jobp = result.getJSONObject(i);
                    HashMap<String, String> data = new HashMap<>();
                    data.put(Config.TAG_TICKET_EVENT_HISTORY, jobp.getString("ticket_event"));
                    data.put(Config.TAG_TICKET_EVENT_LOCATION_HISTORY, jobp.getString("ticket_event_location"));
                    data.put(Config.TAG_TICKET_TYPE_HISTORY, jobp.getString("ticket_type"));
                    data.put(Config.TAG_TICKET_PRICE_HISTORY, jobp.getString("ticket_price"));
                    data.put(Config.TAG_TICKET_NO_HISTORY, jobp.getString("ticket_no"));
                    data.put(Config.TAG_TICKET_ENDDATE, jobp.getString("ticket_enddate"));
                    data.put(Config.TAG_TICKET_QRCODE_HISTORY, jobp.getString("ticket_qrcode"));
                    data.put(Config.TAG_TICKET_HYPERCARD_HISTORY, jobp.getString("ticket_hypercard_no"));
                    data.put(Config.TAG_TICKET_DATEOFPURCHASED_HISTORY, jobp.getString("ticket_date_of_purchase"));
                    list.add(data);
                }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final ListAdapter adapter = new SimpleAdapter(
                TicketActivity.this, list, R.layout.activity_ticket_holder,
                new String[]{Config.TAG_TICKET_EVENT_HISTORY, Config.TAG_TICKET_EVENT_LOCATION_HISTORY , Config.TAG_TICKET_TYPE_HISTORY, Config.TAG_TICKET_PRICE_HISTORY, Config.TAG_TICKET_NO_HISTORY,
                        Config.TAG_TICKET_ENDDATE,Config.TAG_TICKET_HYPERCARD_HISTORY, Config.TAG_TICKET_DATEOFPURCHASED_HISTORY , Config. TAG_TICKET_QRCODE_HISTORY },
                new int[]{R.id.tv_ticket_event_holder, R.id.tv_ticket_event_location_holder, R.id.tv_ticket_type_holder, R.id.tv_ticket_price_holder, R.id.tv_ticket_no_Pholder,
                        R.id.tv_ticket_enddate_Pholder,R.id.tv_ticket_hypercardno_holder ,R.id.tv_ticket_dateofpurchased_holder, R.id.tv_ticket_qrcode_holder});
        lv_ticket_history.setAdapter(adapter);
    }
    private void getTicket_History(String s_cardno){
        final String CardNo  = s_cardno;
        class GetJSON extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TicketActivity.this, null, "Loading Ticket Purchased...", false, false);
            }

            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                loading.dismiss();
                TicketHistory(result);
            }

            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_TICKET_HYPERCARD_HISTORY,CardNo);
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.GET_TICKET_HISTORY_URL,params);
                return res;
            }
        }
        GetJSON gj = new GetJSON();
        gj.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            backButtonHandler();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void displaySelectedFragment(int id){
        Intent navIntent = null;

        switch (id){
            case R.id.nav_event:
                navIntent = new Intent(this, EventActivity.class);
                navIntent.putExtra("card_no",card_no);
                navIntent.putExtra("Card_Amount",card_amount);
                finish();
                break;
            case R.id.nav_ticket:
                navIntent = new Intent(this, TicketActivity.class);
                navIntent.putExtra("card_no",card_no);
                navIntent.putExtra("Card_Amount",card_amount);
                finish();
                break;
            case R.id.nav_logout:
                navIntent = new Intent(this, LoginActivity.class);
                finish();
                break;
        }
        startActivity(navIntent);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        displaySelectedFragment(id);
        return true;
    }

    public void backButtonHandler(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(TicketActivity.this);
        alertDialog.setTitle("Leave application?");
        alertDialog.setMessage("Are you sure you want to leave the application?");

        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                Intent logoutIntent = new Intent(TicketActivity.this, MainActivity.class);
                startActivity(logoutIntent);
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
