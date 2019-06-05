package com.example.arnold.hypercebuproject;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EventActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;
    private TextView tvEventTitle, tvEventDescription, tvEventVenue, tvEventStartDate, tvEventEndDate,tvValidateEvent, tvFE;
    private ImageView ivEventbanner;
    private Button btnJoin;
    private final Handler handler = new Handler();
    private String card_no,card_amount;
    private String event1,location1,description1,startdate1,enddate1,starttime1,endtime1,deleted1, finished1,image1
                    ,event2,location2,description2,startdate2,enddate2,starttime2,endtime2,deleted2,finished2,image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        ivEventbanner = (ImageView) findViewById(R.id.ivEventBanner);
        tvEventTitle = (TextView) findViewById(R.id.tvEventTitle);
        tvEventVenue = (TextView) findViewById(R.id.tvEventVenue);
        tvEventStartDate = (TextView) findViewById(R.id.tvEventStartDate);
        tvEventEndDate = (TextView) findViewById(R.id.tvEventEndDate);
        tvEventDescription = (TextView) findViewById(R.id.tvEventDescription);
        tvFE  =  (TextView) findViewById(R.id.tvFE);
        tvValidateEvent =  (TextView) findViewById(R.id.tvValidateEvent);
        btnJoin = (Button) findViewById(R.id.btnJoin);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        Bundle extras = getIntent().getExtras();
        card_no = extras.getString("card_no");
        card_amount = extras.getString("Card_Amount");

        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        TextView navViewer = (TextView) headerView.findViewById(R.id.tvNavViewer);
        navViewer.setText("Card No.: "+card_no+"\nCard Amount: ₱"+card_amount);
        btnJoin.setOnClickListener(this);

        getEvent1();
        doTheAutoRefresh();
    }
    private void doTheAutoRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getEvent2();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (event2.equals(event1)){
                        } else {
                            getEvent1();
                        }
                    }
                },15000);
                doTheAutoRefresh();
            }
        }, 15000);
    }

    @Override
    public void onBackPressed() {
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

    public void displaySelectedActivity(int id){
        Intent navigationIntent = null;

        switch (id){
            case R.id.nav_event:
                navigationIntent = new Intent(this, RecentActivity.class);
                navigationIntent.putExtra("card_no",card_no);
                navigationIntent.putExtra("Card_Amount",card_amount);
                finish();
                break;
            case R.id.nav_ticket:
                navigationIntent = new Intent(this, TicketActivity.class);
                navigationIntent.putExtra("card_no",card_no);
                navigationIntent.putExtra("Card_Amount",card_amount);
                finish();
                break;
        }
        startActivity(navigationIntent);
        drawer.closeDrawer(GravityCompat.START);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.nav_logout){
            Intent logout = new Intent(this, LoginActivity.class);
            startActivity(logout);
            finish();
        }else{
            displaySelectedActivity(id);
        }
        return true;
    }

    private void getEventBanner(String image_url_path){
        ImageRequest imageRequest = new ImageRequest(image_url_path,

                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {

                        ivEventbanner.setImageBitmap(response);
                    }
                }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EventActivity.this, "Error", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });

        ImageHandler.getmInstance(EventActivity.this).addToRequestQue(imageRequest);
    }

    private void getEvent1() {
        String url = Config.EVENT_URL;

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("One moment...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                showJSON1(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EventActivity.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON1(String response){

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            JSONObject data = result.getJSONObject(0);

            event1 = data.getString(Config.KEY_EVENT);
            location1 = data.getString(Config.KEY_LOCATION);
            description1 = data.getString(Config.KEY_DESCRIPTION);
            startdate1 = data.getString(Config.KEY_STARTDATE);
            enddate1 = data.getString(Config.KEY_ENDDATE);
            starttime1 = data.getString(Config.KEY_STARTTIME);
            endtime1 = data.getString(Config.KEY_ENDTIME);
            deleted1 = data.getString(Config.KEY_DELETED);
            finished1 = data.getString(Config.KEY_FINISHED);
            image1 = data.getString("filename");
            String image_path = Config.IMAGE_PATH+""+image1;
            getEventBanner(image_path);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US); //yyyy-MM-dd HH:mm:ss
            Date currentdate = new Date();
            Date endDate = sdf.parse(enddate1);

                if (finished1.equals("1")) {
                    tvFE.setText("");
                    tvEventTitle.setText("NO FEATURED EVENT");
                    tvEventVenue.setText("");
                    tvEventDescription.setText("");
                    tvEventStartDate.setText("");
                    tvEventEndDate.setText("");
                    tvValidateEvent.setText("");
                    btnJoin.setVisibility(View.INVISIBLE);
                } else if(deleted1.equals("1")){
                    tvFE.setText("");
                    tvEventTitle.setText("NO FEATURED EVENT");
                    tvEventVenue.setText("");
                    tvEventDescription.setText("");
                    tvEventStartDate.setText("");
                    tvEventEndDate.setText("");
                    tvValidateEvent.setText("");
                    btnJoin.setVisibility(View.INVISIBLE);
                }else if(currentdate.after(endDate)){
                    tvFE.setText("");
                    tvEventTitle.setText("NO FEATURED EVENT");
                    tvEventVenue.setText("");
                    tvEventDescription.setText("");
                    tvEventStartDate.setText("");
                    tvEventEndDate.setText("");
                    tvValidateEvent.setText("");
                    btnJoin.setVisibility(View.INVISIBLE);
                }else {
                        tvEventTitle.setText(event1);
                        tvEventVenue.setText(location1);
                        tvEventDescription.setText(description1);
                        tvEventStartDate.setText(startdate1 + ", " + starttime1);
                        tvEventEndDate.setText(enddate1 + ", " + endtime1);
                    }

        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }


    }

        private void getEvent2() {
        String url = Config.EVENT_URL;

        StringRequest stringRequest = new StringRequest(url,   new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON2(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EventActivity.this, error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON2(String response){
        String newLocation, newDescription, newStartDate, newEndDate, newStartTime, newEndTime;

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result2 = jsonObject.getJSONArray(Config.JSON_ARRAY);
            JSONObject data = result2.getJSONObject(0);

            event2 = data.getString(Config.KEY_EVENT);
            location2 = data.getString(Config.KEY_LOCATION);
            description2 = data.getString(Config.KEY_DESCRIPTION);
            startdate2 = data.getString(Config.KEY_STARTDATE);
            enddate2 = data.getString(Config.KEY_ENDDATE);
            starttime2 = data.getString(Config.KEY_STARTTIME);
            endtime2 = data.getString(Config.KEY_ENDTIME);
            deleted2 = data.getString(Config.KEY_DELETED);
            finished2 = data.getString(Config.KEY_FINISHED);
            image2 = data.getString("filename");
            String image_path = Config.IMAGE_PATH+""+image2;
            getEventBanner(image_path);

            newLocation = location2;
            newDescription = description2;
            newStartDate = startdate2;
            newEndDate = enddate2;
            newStartTime = starttime2;
            newEndTime = endtime2;

            if(event2.equals(event1)){

            } else if(!location2.equals(newLocation) || !description2.equals(newDescription) || !startdate2.equals(newStartDate) || !enddate2.equals(newEndDate) || !starttime2.equals(newStartTime) || !enddate2.equals(newEndTime)){

                NotificationCompat.Builder mBuilder =
                        (NotificationCompat.Builder) new NotificationCompat.Builder(EventActivity.this)
                                .setSmallIcon(R.drawable.hyperlogo)
                                .setContentTitle("Updated Event Details");
                Intent notificationIntent = new Intent(EventActivity.this, EventActivity.class);
                notificationIntent.putExtra("Event: ", event2);
                notificationIntent.putExtra("Location: ", location2);
                PendingIntent contentIntent = PendingIntent.getActivity(EventActivity.this, 0, notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                mBuilder.setContentIntent(contentIntent);
                mBuilder.setAutoCancel(true);
                mBuilder.setLights(Color.BLUE, 500, 500);
                long[] pattern = {500, 500, 500, 500, 500, 500, 500, 500, 500};
                mBuilder.setVibrate(pattern);
                mBuilder.setStyle(new NotificationCompat.InboxStyle());
                Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
                if (alarmSound == null) {
                    alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
                    if (alarmSound == null) {
                        alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    }
                }
                // Add as notification
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mBuilder.setSound(alarmSound);
                manager.notify(1, mBuilder.build());

            }  else {
                NotificationCompat.Builder mBuilder =
                        (NotificationCompat.Builder) new NotificationCompat.Builder(EventActivity.this)
                                .setSmallIcon(R.drawable.hyperlogo)
                                .setContentTitle("New Event from Hyper Cebu")
                                .setContentText(event2+", "+location2);
                Intent notificationIntent = new Intent(EventActivity.this, EventActivity.class);
                notificationIntent.putExtra("Event: ", event2);
                notificationIntent.putExtra("Location: ", location2);
                PendingIntent contentIntent = PendingIntent.getActivity(EventActivity.this, 0, notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                mBuilder.setContentIntent(contentIntent);
                mBuilder.setAutoCancel(true);
                mBuilder.setLights(Color.BLUE, 500, 500);
                long[] pattern = {500, 500, 500, 500, 500, 500, 500, 500, 500};
                mBuilder.setVibrate(pattern);
                mBuilder.setStyle(new NotificationCompat.InboxStyle());
                Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
                if (alarmSound == null) {
                    alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
                    if (alarmSound == null) {
                        alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    }
                }
                // Add as notification
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mBuilder.setSound(alarmSound);
                manager.notify(1, mBuilder.build());
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US); //yyyy-MM-dd HH:mm:ss
            Date currentdate = new Date();
            Date endDate = sdf.parse(enddate2);

            if (finished2.equals("1")) {
                tvFE.setText("");
                tvEventTitle.setText("NO FEATURED EVENT");
                tvEventVenue.setText("");
                tvEventDescription.setText("");
                tvEventStartDate.setText("");
                tvEventEndDate.setText("");
                tvValidateEvent.setText("");
                btnJoin.setVisibility(View.INVISIBLE);
            } else if(deleted2.equals("1")){
                tvFE.setText("");
                tvEventTitle.setText("NO FEATURED EVENT");
                tvEventVenue.setText("");
                tvEventDescription.setText("");
                tvEventStartDate.setText("");
                tvEventEndDate.setText("");
                tvValidateEvent.setText("");
                btnJoin.setVisibility(View.INVISIBLE);
            }else if(currentdate.after(endDate)){
                tvFE.setText("");
                tvEventTitle.setText("NO FEATURED EVENT");
                tvEventVenue.setText("");
                tvEventDescription.setText("");
                tvEventStartDate.setText("");
                tvEventEndDate.setText("");
                tvValidateEvent.setText("");
                btnJoin.setVisibility(View.INVISIBLE);
                } else {
                    tvEventTitle.setText(event2);
                    tvEventVenue.setText(location2);
                    tvEventDescription.setText(description2);
                    tvEventStartDate.setText(startdate2 + ", " + starttime2);
                    tvEventEndDate.setText(enddate2 + ", " + endtime2);
                }

        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        Intent joinIntent = new Intent(this, PurchaseActivity.class);
        joinIntent.putExtra("card_no",card_no);
        startActivity(joinIntent);
    }

    public void backButtonHandler(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(EventActivity.this);
        alertDialog.setTitle("Leave application?");
        alertDialog.setMessage("Are you sure you want to leave the application?");

        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                Intent logoutIntent = new Intent(EventActivity.this, MainActivity.class);
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
