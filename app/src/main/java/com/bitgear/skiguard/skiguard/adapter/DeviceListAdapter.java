package com.bitgear.skiguard.skiguard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bitgear.skiguard.skiguard.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by ila on 22.5.17..
 */

public class DeviceListAdapter extends BaseAdapter {

    private Context mContext;
    List<String> mCatNames;

    public DeviceListAdapter(Context context) {
        mContext = context;
        randomizeCatNames();
    }

    public void randomizeCatNames() {
        mCatNames = Arrays.asList(getCatNamesResource());
        Collections.shuffle(mCatNames);
    }

    private String[] getCatNamesResource() {
        return mContext.getResources().getStringArray(R.array.cat_names);
    }




    @Override
    public int getCount() {
        return mCatNames.size();
    }

    @Override
    public Object getItem(int position) {
        return mCatNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).
                    inflate(R.layout.device_list_row, parent, false);
        }

        // get current item to be displayed
        String currentItem = (String) getItem(position);

        // get the TextView for item name and item description
        TextView textViewItemName = (TextView)
                convertView.findViewById(R.id.text_view_item_name);
        TextView textViewItemDescription = (TextView)
                convertView.findViewById(R.id.textLastSeenAgo);

        //sets the text for item name and item description from the current item object
        textViewItemName.setText(currentItem);
        textViewItemDescription.setText(currentItem + " " + currentItem);

        // returns the view for the current row
        return convertView;
    }
}
