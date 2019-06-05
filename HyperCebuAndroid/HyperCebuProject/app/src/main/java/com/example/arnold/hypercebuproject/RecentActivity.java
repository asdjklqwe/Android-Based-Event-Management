package com.example.arnold.hypercebuproject;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class RecentActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private ActionBarDrawerToggle toggle;
    private ListView lv_recentevent;
    private DrawerLayout drawer;
    private String card_no, card_amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lv_recentevent = (ListView) findViewById(R.id.lv_recentEvent);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Bundle extras = getIntent().getExtras();
        card_no = extras.getString("card_no");
        card_amount = extras.getString("Card_Amount");

        View headerView = navigationView.getHeaderView(0);
        TextView navViewer = (TextView) headerView.findViewById(R.id.tvNavViewer);
        navViewer.setText("Card No.: "+card_no+"\nCard Amount: ₱"+card_amount);

        getJSON();
    }
    private void showRecentActivity(String json) {
        final ArrayList<HashMap<String, String>> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            String s_name,s_location, s_description, s_deleted, s_finished;
            for (int i = 1; i < result.length(); i++) {
                JSONObject jobp = result.getJSONObject(i);
                s_name = jobp.getString(Config.TAG_EVENTNAME);
                s_location = jobp.getString(Config.TAG_LOCATION);
                s_description = jobp.getString(Config.TAG_DESCRIPTION);
                s_deleted = jobp.getString(Config.TAG_DELETED);
                s_finished = jobp.getString(Config.TAG_FINISHED);
                HashMap<String, String> data = new HashMap<>();

                if (s_deleted.equals("0") && s_finished.equals("1")) {
                    data.put(Config.TAG_EVENTNAME, s_name);
                    data.put(Config.TAG_LOCATION, s_location);
                    data.put(Config.TAG_DESCRIPTION, s_description);
                    list.add(data);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final ListAdapter adapter = new SimpleAdapter(
                RecentActivity.this, list, R.layout.activity_recent_event_holder,
                new String[]{Config.TAG_EVENTNAME, Config.TAG_LOCATION, Config.TAG_DESCRIPTION},
                new int[]{R.id.tv_ename, R.id.tv_description, R.id.tv_location
                        });
        lv_recentevent.setAdapter(adapter);
    }
    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RecentActivity.this, null, "Loading Recent Events...", false, false);
            }

            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showRecentActivity(s);
            }

            protected String doInBackground(Void... v) {
                RequestHandler rh = new RequestHandler();
                String res = rh.sendGetRequest(Config.ALLEVENT_URL);
                return res;
            }
        }
        GetJSON gj = new GetJSON();
        gj.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void displaySelectedActivity(int id){
        Intent navigationIntent = null;

        switch (id){
            case R.id.nav_event:
                navigationIntent = new Intent(this, EventActivity.class);
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
    public void backButtonHandler(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(RecentActivity.this);
        alertDialog.setTitle("Leave application?");
        alertDialog.setMessage("Are you sure you want to leave the application?");

        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                Intent logoutIntent = new Intent(RecentActivity.this, MainActivity.class);
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
