package com.shehabic.droppy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by shehabic on 3/1/15.
 */
public class DroppyMenuCustomItem extends DroppyMenuItemAbstract {

    public DroppyMenuCustomItem(int customResourceId) {
        isClickable = false;
        type = TYPE_CUSTOM;
        customViewResourceId = customResourceId;
    }

    public DroppyMenuCustomItem(View customView) {
        isClickable = false;
        type = TYPE_CUSTOM;
        renderedView = customView;
    }

    @Override
    public View render(Context context) {
        if (renderedView == null) {
            renderedView = LayoutInflater.from(context).inflate(customViewResourceId, null);
        }

        return renderedView;
    }
}
