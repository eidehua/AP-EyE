package com.madhack.eidehua.apeye;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Edward on 4/18/2015.
 */
public class SpecificCategoryAdapter extends RecyclerView.Adapter<SpecificCategoryAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0;  // Declaring Variable to Understand which View is being worked on
    // IF the view under inflation and population is header or Item
    private static final int TYPE_ITEM = 1;

    public ArrayList<String> mTitles = new ArrayList<String>(); // String Array to store the passed titles Value from MainActivity.java
    public ArrayList<String> mDescriptions = new ArrayList<String>();
    public ArrayList<String> mNames = new ArrayList<String>();
    public ArrayList<String> mUpdated = new ArrayList<String>();

    // Creating a ViewHolder which extends the RecyclerView View Holder
    // ViewHolder are used to to store the inflated views in order to recycle them

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public View view;
        public TextView descriptionView;

        public ViewHolder(View itemView) {                 // Creating ViewHolder Constructor with View and viewType As a parameter
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text2); // Creating TextView object with the id of textView from item_row.xml
            view = itemView;
            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) { // This onclick function is created for every card view(that is surrounded by a linear layout)
                    View nextChild = ((ViewGroup)v).getChildAt(0);
                    Intent i = new Intent(v.getContext(), SpecificCategoryActivity.class);
                    CardView x = (CardView) nextChild;
                    nextChild = ((ViewGroup)x).getChildAt(0);   //child of card view is the text view I need
                    TextView z = (TextView) nextChild;
                    i.putExtra("Category", z.getText().toString());
                    v.getContext().startActivity(i);
                }
            });
            descriptionView = (TextView) itemView.findViewById(R.id.text3);
        }


    }


    SpecificCategoryAdapter() { // MyAdapter Constructor with titles and icons parameter
        // titles, icons, name, email, profile pic are passed from the main activity as we
        mTitles = new ArrayList<String>();                //have seen earlier
        //here we assign those passed values to the values we declared here
        //in adapter
        mDescriptions = new ArrayList<String>();
        mNames = new ArrayList<String>();
        mUpdated = new ArrayList<String>();

    }


    //Below first we ovverride the method onCreateViewHolder which is called when the ViewHolder is
    //Created, In this method we inflate the item_row.xml layout if the viewType is Type_ITEM or else we inflate header.xml
    // if the viewType is TYPE_HEADER
    // and pass it to the view holder

    @Override
    public SpecificCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_specific_category, parent, false); //Inflating the layout

        ViewHolder vhItem = new ViewHolder(v); //Creating ViewHolder and passing the object of type view
        return vhItem; // Returning the created object
    }

    //Next we override a method which is called when the item in a row is needed to be displayed, here the int position
    // Tells us item at which position is being constructed to be displayed and the holder id of the holder object tell us
    // which view type is being created 1 for item row
    @Override
    public void onBindViewHolder(SpecificCategoryAdapter.ViewHolder holder, int position) {
        // position by 1 and pass it to the holder while setting the text and image
        holder.textView.setText(mTitles.get(position)); // Setting the Text with the array of our Titles
        holder.descriptionView.setText(mDescriptions.get(position));
    }

    // This method returns the number of items present in the list
    @Override
    public int getItemCount() {
        return mTitles.size();
    }

}