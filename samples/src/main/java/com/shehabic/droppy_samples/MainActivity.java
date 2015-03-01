package com.shehabic.droppy_samples;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.shehabic.droppy.DroppyClickCallbackInterface;
import com.shehabic.droppy.DroppyMenu;
import com.shehabic.droppy.DroppyMenuItem;

public class MainActivity extends ActionBarActivity {

    DroppyMenu droppyMenu;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button);
        initDroppyMenu();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                droppyMenu.show();
            }
        });
    }

    private void initDroppyMenu()
    {
        DroppyMenu.Builder droppyBuilder = new DroppyMenu.Builder(this, btn);
        droppyBuilder.addMenuItem(new DroppyMenuItem("test1"));
        droppyBuilder.addMenuItem(new DroppyMenuItem("test2"));
        droppyBuilder.addSeparator();
        droppyBuilder.addMenuItem(new DroppyMenuItem("test3"));
        droppyBuilder.addMenuItem(new DroppyMenuItem("test4"));
        droppyBuilder.setOnClick(new DroppyClickCallbackInterface() {
            @Override
            public void call(View v, int id) {
                Log.d("Clicked on ", String.valueOf(id));
            }
        });
        droppyMenu = droppyBuilder.build();
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

