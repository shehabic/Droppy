package com.shehabic.droppy_samples;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.shehabic.droppy.DroppyClickCallbackInterface;
import com.shehabic.droppy.DroppyMenuCustomItem;
import com.shehabic.droppy.DroppyMenuPopup;
import com.shehabic.droppy.DroppyMenuItem;
import com.shehabic.droppy.animations.DroppyFadeInAnimation;
import com.shehabic.droppy.animations.DroppyScaleAnimation;

/**
 * Created by shehabic on 3/21/15.
 */
public class MainActivity extends ActionBarActivity implements DroppyMenuPopup.OnDismissCallback, DroppyClickCallbackInterface {

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
        droppyBuilder.addMenuItem(new DroppyMenuItem("First Item"))
            .addMenuItem(new DroppyMenuItem("Second Item"))
            .addSeparator()
            .setOnDismissCallback(this)
            .setOnClick(this)
            .addMenuItem(new DroppyMenuItem("Third Item", R.drawable.ic_launcher))
            .setPopupAnimation(new DroppyFadeInAnimation())
            .triggerOnAnchorClick(false);

        DroppyMenuCustomItem sBarItem = new DroppyMenuCustomItem(R.layout.slider);
        droppyBuilder.addMenuItem(sBarItem)
            .setOnClick(this);

        droppyMenu = droppyBuilder.build();
    }

    private void initDroppyMenuFromXml(Button btn)
    {
        DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(this, btn);
        DroppyMenuPopup droppyMenu = droppyBuilder.fromMenu(R.menu.droppy)
            .triggerOnAnchorClick(false)
            .setOnClick(this)
            .setOnDismissCallback(this)
            .setPopupAnimation(new DroppyScaleAnimation())
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

    @Override
    public void call()
    {
        Toast.makeText(this, "Menu dismissed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void call(View v, int id)
    {
        String idText;

        switch (id) {
            case R.id.droppy1:  idText = "Droppy Item 1"; break;
            case R.id.droppy2:  idText = "Droppy Item 2"; break;
            case R.id.droppy3:  idText = "Droppy Item 3"; break;
            case R.id.droppy4:  idText = "Droppy Item 4"; break;
            case R.id.droppy5:  idText = "Droppy Item 5"; break;
            case R.id.droppy6:  idText = "Droppy Item 6"; break;
            case R.id.droppy7:  idText = "Droppy Item 7"; break;
            case R.id.droppy8:  idText = "Droppy Item 8"; break;
            case R.id.droppy9:  idText = "Droppy Item 9"; break;
            // .
            // .
            // .
            // .
            case R.id.droppy18: idText = "Droppy Item 18"; break;
            case R.id.droppy19: idText = "Droppy Item 19"; break;
            case R.id.droppy20: idText = "Droppy Item 20"; break;
            case R.id.droppy21: idText = "Droppy Item 21"; break;
            case R.id.droppy22: idText = "Droppy Item 22"; break;
            case R.id.droppy23: idText = "Droppy Item 23"; break;

            default:
                idText = String.valueOf(id);
        }

        Toast.makeText(this, "Tapped on item with id: " + idText, Toast.LENGTH_SHORT).show();
    }
}
