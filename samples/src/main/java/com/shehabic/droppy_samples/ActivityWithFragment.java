package com.shehabic.droppy_samples;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

import com.shehabic.droppy.DroppyClickCallbackInterface;
import com.shehabic.droppy.DroppyMenuCustomItem;
import com.shehabic.droppy.DroppyMenuItem;
import com.shehabic.droppy.DroppyMenuPopup;


public class ActivityWithFragment extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_with_fragment);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new PlaceholderFragment())
                .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_with, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        DroppyMenuPopup droppyMenu;
        Button btn;
        int seekbarValue = 0;


        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_activity_with, container, false);
            initButtons(rootView);
            return rootView;
        }

        protected void initButtons(View rootView)
        {
            btn = (Button) rootView.findViewById(R.id.menuButton);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initDroppyMenu(btn);
                    showDroppyMenu();
                }
            });
        }

        private void initDroppyMenu(Button btn)
        {
            DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(btn.getContext(), btn);
            droppyBuilder.addMenuItem(new DroppyMenuItem("test1"))
                .addMenuItem(new DroppyMenuItem("test2"))
                .addSeparator()
                .addMenuItem(new DroppyMenuItem("test3", R.drawable.ic_launcher))
                .triggerOnAnchorClick(false);

            DroppyMenuCustomItem sBarItem = new DroppyMenuCustomItem(R.layout.slider);
            droppyBuilder.addMenuItem(sBarItem);

            droppyBuilder.setOnClick(new DroppyClickCallbackInterface() {
                @Override
                public void call(View v, int id) {
                    Log.d("Clicked on ", String.valueOf(id));
                }
            });
            droppyMenu = droppyBuilder.build();
        }

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
    }
}
