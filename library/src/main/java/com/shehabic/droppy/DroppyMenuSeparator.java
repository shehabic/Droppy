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
        this.customViewResourceId = separatorLayoutResourceId;
        this.type = TYPE_MENU_SEPARATOR;
        this.setId(-1);
        this.setClickable(false);
    }

    @Override
    public View render(Context context) {
        if (this.renderedView == null) {
            this.renderedView = LayoutInflater.from(context).inflate(this.customViewResourceId, null);
        }

        return renderedView;
    }
}
