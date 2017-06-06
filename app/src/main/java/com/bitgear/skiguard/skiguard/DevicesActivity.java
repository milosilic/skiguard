package com.bitgear.skiguard.skiguard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.bitgear.skiguard.dao.Device;
import com.bitgear.skiguard.skiguard.adapter.DeviceListAdapter;
import com.orhanobut.logger.Logger;

public class DevicesActivity extends NavigationDrawer  {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mRecyclerView;
    private DeviceListAdapter mCatNamesRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("onCreate");
        deviceSwipeList();
//        setContentView(R.layout.activity_devices);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logger.d("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.d("resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.d("onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.d("onDestroy");
    }

    public void setupToolbar() {
        getLayoutInflater().inflate(R.layout.activity_devices, frameLayout);
    }


    private void deviceSwipeList(){
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mRecyclerView = (ListView) findViewById(R.id.eventListView);
        setupAdapter();
        setupItemListener();


        mSwipeRefreshLayout.setColorSchemeColors(R.color.colorAccent, R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setupAdapter();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2500);
            }
        });    }


    private void refreshContent() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void setupAdapter() {
        mCatNamesRecyclerViewAdapter = new DeviceListAdapter(this);
        mRecyclerView.setAdapter(mCatNamesRecyclerViewAdapter);
    }

    private void setupItemListener(){
        if ( mRecyclerView != null){
            mRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(DevicesActivity.this, DeviceDetailsActivity.class);

                    Logger.d( mCatNamesRecyclerViewAdapter.getItem(position));
                    intent.putExtra("id_device", (String) ((Device)mCatNamesRecyclerViewAdapter.getItem(position)).getId().toString());
                    startActivity(intent);
                }
            });
        }
    }


}
