package com.example.kthota.eureQaApp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        final ListView listview = (ListView) findViewById(R.id.list_view);
        String[] values = new String[] {"Android","IPhone","Windows Mobile","Blackberry",
                "WebOS","Ubuntu","Windows 7","Max OS X","Windows XP","Windows 8","Windows 8.1","Windows 10"};

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }

        ScrollingActivity.StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);
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
            tv.setTextColor(Color.BLACK);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,25);
//            tv.setTypeface(null, Typeface.BOLD);
            // Return the view
            return view;
        }
    }
}
