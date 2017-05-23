package com.bitgear.skiguard.skiguard;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;


public class HistoryActivity extends NavigationDrawer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //@see
    }

    public void setupToolbar() {
        getLayoutInflater().inflate(R.layout.activity_history, frameLayout);
    }

}
