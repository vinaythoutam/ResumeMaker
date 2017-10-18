package com.example.vinay.resumebuilder;

/**
 * Created by Vinay on 17-10-2017.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vinay.resumebuilder.model.CardDetails;

import java.util.List;

public class NavActivityProfile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //add profile code
    String names[] = null;
    String dates[] = null;
    String types[] = null;
    String uDates[]= null;
    int ids[] = null;
    DatabaseHandler dbHandler;
    FloatingActionButton fab;
    ListView lv;
    ListViewAdapter lviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //add profiles <code></code>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Explode explode = new Explode();
            explode.setDuration(200);
            getWindow().setExitTransition(explode);
            getWindow().setEnterTransition(explode);
        }

        lv = (ListView) findViewById(R.id.lvCards);
        dbHandler = new DatabaseHandler(this);

        List<CardDetails> cd = dbHandler.getAllCardDetails();
        if (cd.size() > 0) {
            names = new String[cd.size()];
            dates = new String[cd.size()];
            types = new String[cd.size()];
            ids   = new int[cd.size()];
            uDates= new String[cd.size()];

            int i = 0;
            for (CardDetails d : cd) {
                names[i] = d.getCname();
                dates[i] = d.getDate();
                types[i] = d.getCtype();
                ids[i]=d.getCid();
                uDates[i] = d.getCudate();

                //Toast.makeText(getApplicationContext(), "hi"+d.getCname(), Toast.LENGTH_SHORT).show();
                i++;
            }
            lv = (ListView) findViewById(R.id.lvCards);
            lviewAdapter = new ListViewAdapter(this, names, dates, types,ids,uDates);

            lv.setAdapter(lviewAdapter);
        } else {
            Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT).show();

        }

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddProfileDialog();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                String name = names[position];
                int cid= ids[position];
                //Toast.makeText(AddProfilesActivity.this, "Hi"+ name, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
                intent.putExtra("GuestName", name);
                intent.putExtra("Cid",cid);
                finish();
                overridePendingTransition( 0, 0);
                startActivity(intent);
                overridePendingTransition( 0, 0);


            }
        });

    }

    //add profile code for dialog box
    public void showAddProfileDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.edit1);
        final Spinner spinner = (Spinner) dialogView.findViewById(R.id.spinner);
        //final EditText edt2=(EditText)findViewById(R.id.customET);


        dialogBuilder.setTitle("Resume Builder");
        dialogBuilder.setMessage("Enter your name");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
                String pName = edt.getText().toString();
                String pType = spinner.getSelectedItem().toString();
                CardDetails cardDetails = new CardDetails();
                cardDetails.setCname(pName);
                cardDetails.setCtype(pType);
                long n = dbHandler.addProfile(cardDetails);
                //Toast.makeText(AddProfilesActivity.this, "count"+n, Toast.LENGTH_SHORT).show();
                if (n > 0) {
                    Toast.makeText(getApplicationContext(), "Successfully added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), AddProfilesActivity.class);
                    finish();
                    overridePendingTransition( 0, 0);
                    startActivity(intent);
                    overridePendingTransition( 0, 0);
                } else {
                    Toast.makeText(getApplicationContext(), "Problem in adding", Toast.LENGTH_SHORT).show();

                }
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    void refreshActivity(){
        Intent intent = new Intent(getApplicationContext(),AddProfilesActivity.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
