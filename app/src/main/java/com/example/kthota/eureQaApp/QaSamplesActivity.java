package com.example.kthota.eureQaApp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class QaSamplesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qa_samples);

        final ListView listview = (ListView) findViewById(R.id.list_view);
        String[] values = new String[] {
                "Detect Multiple Fingers",
                "Touch Down",
                "Image View",
                "Text View",
                "Scroll View",
                "Double Tap",
                "Drag And Drop Activity"};

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                System.out.println("Item Name :: "+item);

                Intent intent;
                switch(item){
                    case    "Detect Multiple Fingers":
                        intent = new Intent(QaSamplesActivity.this, MultiFingersActivity.class);
                        startActivity(intent);
                        break;

                    case    "Touch Down":
                        intent = new Intent(QaSamplesActivity.this, TouchDownActivity.class);
                        startActivity(intent);
                        break;

                    case    "Image View":
                        intent = new Intent(QaSamplesActivity.this, ImageActivity.class);
                        startActivity(intent);
                        break;

                    case    "Text View":
                        intent = new Intent(QaSamplesActivity.this, TextViewActivity.class);
                        startActivity(intent);
                        break;

                    case    "Scroll View":
                        intent = new Intent(QaSamplesActivity.this, ScrollingActivity.class);
                        startActivity(intent);
                        break;

                    case    "Double Tap":
                        intent = new Intent(QaSamplesActivity.this, DoubleTapActivity.class);
                        startActivity(intent);
                        break;

                    case    "Drag And Drop Activity":
                        intent = new Intent(QaSamplesActivity.this, DragAndDropActivity.class);
                        startActivity(intent);
                        break;
                }
                /*view.animate().setDuration(2000).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                list.remove(item);
                                adapter.notifyDataSetChanged();
                                view.setAlpha(1);
                            }
                        });*/
            }

        });
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {
        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
        public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }
        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }
        @Override
        public boolean hasStableIds() {
            return true;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            /// Get the Item from ListView
            View view = super.getView(position, convertView, parent);
            ViewGroup.LayoutParams params = view.getLayoutParams();
            params.height = 200;
            view.setLayoutParams(params);

            TextView tv = (TextView) view.findViewById(android.R.id.text1);
            // Set the text size 25 dip for ListView each item
            tv.setTextColor(Color.parseColor("#009688"));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,25);
            tv.setTypeface(null, Typeface.BOLD);
            // Return the view
            return view;
        }
    }
}