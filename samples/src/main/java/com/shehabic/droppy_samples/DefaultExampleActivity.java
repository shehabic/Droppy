package com.shehabic.droppy_samples;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import android.widget.ListView;

import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class DefaultExampleActivity

    extends Activity {

    protected ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);
        initListView();
    }

    private void initListView() {
        listView = (ListView) findViewById(R.id.activity_selector);
        final List<Class> classes = new ArrayList<>();
        final List<String> items = new ArrayList<>();
        classes.add(MainActivity.class);
        items.add("ActionBar Normal Activity");

        classes.add(ActivityWithFragment.class);
        items.add("Fragment Activity");

        classes.add(FullscreenActivity.class);
        items.add("Fullscreen Activity");

        ActivitySelectorAdapter adapter = new ActivitySelectorAdapter(this, items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(DefaultExampleActivity.this, classes.get(position));
                startActivity(i);
            }
        });
    }

    class ActivitySelectorAdapter extends BaseAdapter
    {
        protected List<String> items;
        protected Context ctx;

        public ActivitySelectorAdapter(Context ctx, List<String> items)
        {
            this.ctx = ctx;
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return (long) position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                // inflate the layout
                LayoutInflater inflater = ((Activity) ctx).getLayoutInflater();
                convertView = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);
            }

            TextView textViewItem = (TextView) convertView.findViewById(android.R.id.text1);
            textViewItem.setText(items.get(position));
            textViewItem.setTag(getItemId(position));

            return convertView;
        }
    }
}

