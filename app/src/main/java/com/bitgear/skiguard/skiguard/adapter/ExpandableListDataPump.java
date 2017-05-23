package com.bitgear.skiguard.skiguard.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ila on 23.5.17..
 */

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> monday = new ArrayList<String>();
        monday.add("Karaman greben");
        monday.add("Krst");
        monday.add("Krst");
        monday.add("Krst");
        monday.add("Karaman greban");

        List<String> football = new ArrayList<String>();
        football.add("Pancicev vr'");
        football.add("Pancicev vr'");
        football.add("Crna duboka");
        football.add("Pancicev vr'");
        football.add("Crvena Duboka'");

        List<String> basketball = new ArrayList<String>();
        basketball.add("Masinac");
        basketball.add("Krst");
        basketball.add("Krst");
        basketball.add("Masinac");
        basketball.add("Gobelja");

        expandableListDetail.put("Raja", monday);
        expandableListDetail.put("Gaja", football);
        expandableListDetail.put("Vlaja", basketball);

        return expandableListDetail;
    }


}
