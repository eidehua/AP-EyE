package com.madhack.eidehua.apeye;

import android.content.Context;

import org.json.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Edward on 4/19/2015.
 */
public class ParseJson {

    //Assumes: We have JSON in format: ARRAY of tuples.
    //Each tuple = JSONObject
    //Updates categories and data
    public static void parse(Context context, ArrayList<String> categories, ArrayList<ArrayList<String>> data, File filesDir) throws FileNotFoundException, JSONException {
        InputStream is = context.getResources().openRawResource(R.raw.allapidatajson);
            java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
            String z = s.hasNext() ? s.next() : "";

        //String content = new Scanner(new File(filesDir, filename)).useDelimiter("\\Z").next();
        String content = z;
        //System.out.println(content);
        JSONArray json_arr = new JSONArray(content);
        for(int i = 0; i < json_arr.length(); i++){
            JSONObject json_obj = json_arr.getJSONObject(i);
            ArrayList<String> new_tuple = new ArrayList<String>();

            //Tuple format: id, apiname, description, category, updatedDate
            new_tuple.add(json_obj.getString("id"));
            new_tuple.add(json_obj.getString("apiname"));
            new_tuple.add(json_obj.getString("description"));
            String category = json_obj.getString("category");
            if(!categories.contains(category))
                categories.add(category);
            new_tuple.add(category);
            new_tuple.add(json_obj.getString("updatedDate"));
            data.add(new_tuple);
            //System.out.println(categories.size());
        }
        Collections.sort(categories);
    }
}
