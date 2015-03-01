package com.shehabic.droppy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
/**
 * Created by shehabic on 2/28/15.
 */
public class DroppyMenuSeparator extends DroppyMenuItemAbstract {

    public DroppyMenuSeparator() {
        initSeparator(R.layout.droppy_separator);
    }

    public DroppyMenuSeparator(int customSeparatorResourceId) {
        initSeparator(customSeparatorResourceId);
    }

    protected void initSeparator(int separatorLayoutResourceId) {
        customViewResourceId = separatorLayoutResourceId;
        type = TYPE_MENU_SEPARATOR;
        setId(-1);
        setClickable(false);
    }

    @Override
    public View render(Context context) {
        if (renderedView == null) {
            renderedView = LayoutInflater.from(context).inflate(this.customViewResourceId, null);
        }

        return renderedView;
    }
}
