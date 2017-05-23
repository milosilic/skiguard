package com.bitgear.skiguard.skiguard;

import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.bitgear.skiguard.skiguard.adapter.ExpandableListDataPump;
import com.bitgear.skiguard.skiguard.adapter.TrackHistoryAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HistoryActivity extends NavigationDrawer {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupHistoryList();
        //@see
    }

    public void setupToolbar() {
        getLayoutInflater().inflate(R.layout.activity_history, frameLayout);
    }

    private void setupHistoryList(){
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new TrackHistoryAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener(){

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();

            }
        });
    }

}
