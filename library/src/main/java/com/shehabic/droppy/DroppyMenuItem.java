package com.shehabic.droppy;

import android.content.Context;
import android.view.View;

/**
 * Created by shehabic on 2/28/15.
 */
public class DroppyMenuItem extends DroppyMenuItemAbstract {


    public DroppyMenuItem(String title)
    {
        this.title = title;
    }

    public DroppyMenuItem(String title, int iconResourceId)
    {
        this.title = title;
        this.icon = iconResourceId;
    }

    public DroppyMenuItem(int customViewResourceId)
    {
        this.customViewResourceId = customViewResourceId;
    }


    @Override
    public View render(Context context) {
        return null;
    }

}
