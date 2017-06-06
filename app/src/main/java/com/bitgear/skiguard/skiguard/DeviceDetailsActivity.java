package com.bitgear.skiguard.skiguard;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.bitgear.skiguard.dao.DaoMaster;
import com.bitgear.skiguard.dao.DaoSession;
import com.bitgear.skiguard.dao.Device;
import com.bitgear.skiguard.dao.DeviceDao;
import com.bitgear.skiguard.skiguard.adapter.DeviceCardsAdapter;
import com.orhanobut.logger.Logger;

import org.greenrobot.greendao.database.Database;

import java.util.ArrayList;
import java.util.List;

public class DeviceDetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DeviceCardsAdapter adapter;
    private List<Album> albumList;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        if ( savedInstanceState == null) {
            name = getIntent().getStringExtra("id_device");
        }else {
            name = savedInstanceState.getString("id_device");
        }

        ///
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adapter = new DeviceCardsAdapter(this, albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();

        /// DeviceDetails populateScreen

        populateScreen(name);

    }

    private void populateScreen(String name) {

        Device device = null;
        if ( name != null ) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "users-db"); //The users-db here is the name of our database.
            Database db = helper.getWritableDb();
            DaoSession daoSession = new DaoMaster(db).newSession();
            DeviceDao deviceDao = daoSession.getDeviceDao();
            List<Device> listDevice = deviceDao.queryBuilder().where(DeviceDao.Properties.Id.eq(Integer.parseInt(name))).list();
            device = listDevice.get(0);

        }

        if ( device != null){
            TextView deviceName = (TextView) findViewById(R.id.childName);
            if ( deviceName != null){
                deviceName.setText(device.getName());
            }
            TextView serialNumberTextView = (TextView) findViewById(R.id.serialNumberTextView);
            if ( serialNumberTextView != null){
                serialNumberTextView.setText(device.getSerial_number());
            }
            TextView expiresOnTextView = (TextView) findViewById(R.id.expiresOn);
            if ( expiresOnTextView != null){
                expiresOnTextView.setText(device.getLast_update().toString());
            }

        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.d("ode u stop");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("id_device", name);
        Logger.d("ode u onSave");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Logger.d("ode u onRestore");
    }

    public void deviceSetupClick(View view) {
        Intent intent = new Intent(this, DeviceSetupActivity.class);
        intent.putExtra("id_device", getIntent().getStringExtra("id_device"));
        startActivity(intent);
    }


    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {

        Album a = new Album("Location","Pancicev vrh");
        albumList.add(a);

        a = new Album("Battery", "50%");
        albumList.add(a);

        a = new Album("Last Update", "3 minutes ago");

        albumList.add(a);

        adapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }


    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }



}
