package com.madhack.eidehua.apeye;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    public static ArrayList<String> categories = new ArrayList<String>();
    public static ArrayList<ArrayList<String>> api_data = new ArrayList<ArrayList<String>>();
    //First We Declare Titles And Icons For Our Navigation Drawer List View
    //This Icons And Titles Are holded in an Array as you can see

    String TITLES[] = {"Home","Events","Mail","Shop","Travel"};
    String cats[]={"A","B","C","D","E","F","G","DAAWDAWD","ADawdwadawd","dadwadawdwa","wqeqew","adwadawd","dadda"};
   List<String> CATEGORIES = Arrays.asList(cats);
    int ICONS[] = {R.drawable.ic_search,R.drawable.ic_search,R.drawable.ic_action,R.drawable.ic_action,R.drawable.ic_action};

    //Similarly we Create a String Resource for the name and email in the header view
    //And we also create a int resource for profile picture in the header view

    String NAME = "AP EyE";
    String EMAIL = "ap@.com";
    int PROFILE = R.drawable.logo;

    private Toolbar toolbar;                              // Declaring the Toolbar Object

    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;                                  // Declaring DrawerLayout

    ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle

    //Recycler for Categories

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    /* Assinging the toolbar object ot the view
    and setting the the Action bar to our toolbar
     */
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        RecyclerView mRecyclerView_categories;                           // Declaring RecyclerView
        MyCategoryAdapter mAdapter_categories;                        // Declaring Adapter For Recycler View
        RecyclerView.LayoutManager mLayoutManager_categories;


        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View
        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size
        mAdapter = new MyAdapter(TITLES,ICONS,NAME,EMAIL,PROFILE);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture
        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView
        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager
        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager
        //System.out.println(CATEGORIES.toString());

        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(this,Drawer,toolbar,R.string.openDrawer,R.string.closeDrawer){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }



        }; // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();               // Finally we set the drawer toggle sync State

        //Connect recycler to layout manager and adapter (for display) for the categories
        mRecyclerView_categories = (RecyclerView) findViewById(R.id.my_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView_categories.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager_categories = new LinearLayoutManager(this);
        mRecyclerView_categories.setLayoutManager(mLayoutManager_categories);
        // specify an adapter (see also next example)
        mAdapter_categories = new MyCategoryAdapter();
        mRecyclerView_categories.setAdapter(mAdapter_categories);
        try {
            File filesDir = getFilesDir();

            ParseJson.parse(this.getApplicationContext(), categories, api_data, filesDir);
            for(int i = 0; i < categories.size(); i++){
                addItem(categories.get(i), mAdapter_categories);
//            LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View v = vi.inflate(R.layout.dynamic_category, null);
//
//            // fill in any details dynamically here
//            TextView textView = (TextView) v.findViewById(R.id.text1);
//            textView.setText(CATEGORIES[i]);
//
//            // insert into main view
//            ViewGroup insertPoint = (ViewGroup) findViewById(R.id.main_linear_layout);    //where we want to insert into
//            insertPoint.addView(v);
            }
        } catch (FileNotFoundException | JSONException e) {
            e.printStackTrace();
        }


    }

    private void addItem(String item, MyCategoryAdapter a) {
        a.mCategories.add(item);
        a.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}