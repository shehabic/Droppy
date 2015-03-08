package com.shehabic.droppy_samples;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.shehabic.droppy.DroppyClickCallbackInterface;
import com.shehabic.droppy.DroppyMenuPopup;
import com.shehabic.droppy.DroppyMenuCustomView;
import com.shehabic.droppy.DroppyMenuItem;

public class MainActivity extends ActionBarActivity {

    DroppyMenuPopup droppyMenu;
    Button btn;
    Button btn2;
    Button btn3;

    protected int seekbarValue = 0;

    protected void showDroppyMenu()
    {
        droppyMenu.show();
        SeekBar sBar = (SeekBar) droppyMenu.getMenuView().findViewById(R.id.seekBar1);
        sBar.setProgress(seekbarValue);
        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekbarValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDroppyMenu(btn);
                showDroppyMenu();
            }
        });

        btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDroppyMenu(btn2);
                showDroppyMenu();
            }
        });

        btn3 = (Button) findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDroppyMenuFromXml(btn3);
            }
        });
    }

    private void initDroppyMenu(Button btn)
    {
        DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(this, btn);
        droppyBuilder.addMenuItem(new DroppyMenuItem("test1"))
            .addMenuItem(new DroppyMenuItem("test2"))
            .addSeparator()
            .addMenuItem(new DroppyMenuItem("test3", R.drawable.ic_launcher))
            .triggerOnAnchorClick(false);

        DroppyMenuCustomView sBarItem = new DroppyMenuCustomView(R.layout.slider);
        droppyBuilder.addMenuItem(sBarItem);

        droppyBuilder.setOnClick(new DroppyClickCallbackInterface() {
            @Override
            public void call(View v, int id) {
                Log.d("Clicked on ", String.valueOf(id));
            }
        });
        droppyMenu = droppyBuilder.build();
    }

    private void initDroppyMenuFromXml(Button btn)
    {
        DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(this, btn);
        DroppyMenuPopup droppyMenu = droppyBuilder.fromMenu(R.menu.droppy)
            .triggerOnAnchorClick(false)
            .setOnClick(new DroppyClickCallbackInterface() {
                @Override
                public void call(View v, int id) {
                    Log.d("Id:", String.valueOf(id));
                }
            })
            .build();
        droppyMenu.show();
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

