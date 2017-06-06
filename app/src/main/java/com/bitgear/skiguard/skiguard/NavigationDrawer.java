package com.bitgear.skiguard.skiguard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.bitgear.skiguard.dao.DaoMaster;
import com.bitgear.skiguard.dao.DaoSession;
import com.bitgear.skiguard.dao.Device;
import com.bitgear.skiguard.dao.DeviceDao;
import com.bitgear.skiguard.dao.History;
import com.bitgear.skiguard.dao.HistoryDao;
import com.bitgear.skiguard.dao.Lift;
import com.bitgear.skiguard.dao.LiftDao;
import com.bitgear.skiguard.dao.Piste;
import com.bitgear.skiguard.dao.PisteDao;
import com.orhanobut.logger.Logger;

import org.greenrobot.greendao.database.Database;

import java.util.ArrayList;
import java.util.List;

public class NavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public FrameLayout frameLayout;
    private static String[] devices = {"Milos Ilic", "Teodora Ilc", "Irina Ilic"};
    private static String[] sn = {"3043049334", "5656565644", "7878787878"};
    private static String[] device_color = {"#EC2C2C", "#5CA3E2", "#EC2C2C"};
    private static String[] pisteName = {"Karaman greben", "Pancicev vrh", "Masinac"};
    private static String[] zica = {"Zica Pancic", "Zica Krst", "Zica Gvozdac"};
    private static int[] id_track_change = {100, 200, 300};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        frameLayout = (FrameLayout) findViewById(R.id.container);

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

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"users-db"); //The users-db here is the name of our database.
        Database db = helper.getWritableDb();
        DaoSession daoSession = new DaoMaster(db).newSession();

        if ( isSqliteEmpty(daoSession)){
            for (int i = 0; i< devices.length;i++){
                insertSampleData(daoSession, i);
            }
        }

        setupToolbar();


    }

    private boolean isSqliteEmpty(DaoSession daoSession) {
        DeviceDao deviceDao = daoSession.getDeviceDao();
        List<Device> listDevice = deviceDao.loadAll();
        return listDevice.size() == 0;
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
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
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

        if (id == R.id.nav_devices) {
            // Handle the camera action
            Intent intent = new Intent(this, DevicesActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_history) {
            Intent intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_rate_us) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * implementation in child activities
     */
    public void setupToolbar(){

    }

    public void insertSampleData(DaoSession daoSession, Integer id ) {
//        User device = new User();
//        device.setEmail("John Doe");
//        UserDao personDao = daoSession.getUserDao();
//        personDao.insertOrReplace(device);

        Piste piste  = new Piste();
        //track.setId(1L);
        piste.setName(pisteName[id]);
        ArrayList<String> categoryList = new ArrayList<String>();
        categoryList.add("#EC2C2C");
        categoryList.add("#5CA3E2");

        piste.setCategoryList(categoryList);
        //promeniti naravno, treba da bude lista koordinata
        piste.setBoundaryList(categoryList);

        PisteDao trackDao = daoSession.getPisteDao();
        long trackId = trackDao.insertOrReplace(piste);
        Logger.d("track: " + trackId);

        Lift lift = new Lift();
        lift.setName(zica[id]);
        LiftDao liftDao = daoSession.getLiftDao();
        ArrayList<String> boundaryList = new ArrayList<String>();
        lift.setBoundaryList(boundaryList);


        long liftId = liftDao.insertOrReplace(lift);
        Logger.d("lift: " + liftId);

        History history = new History();
        history.setBattery(234);
        history.setId_device(1);
        history.setId_track_change(id_track_change[id]);
        history.setPiste(piste);
        history.setLat((float) 5);
        history.setLng((float) 23.32);

        HistoryDao historyDao = daoSession.getHistoryDao();
        long update = historyDao.insertOrReplace(history);
        Logger.d(update);


        Device device = new Device();
        device.setColor(device_color[id]);
        device.setLast_update(history.getId());
        device.setHistory(history);
        device.setName(devices[id]);
        device.setSerial_number(sn[id]);
        DeviceDao deviceDao = daoSession.getDeviceDao();
        deviceDao.insertOrReplace(device);
    }
}
