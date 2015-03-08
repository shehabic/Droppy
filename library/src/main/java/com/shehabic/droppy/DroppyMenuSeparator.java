package com.shehabic.droppy;

import android.content.Context;
import android.view.View;
/**
 * Created by shehabic on 2/28/15.
 */
public class DroppyMenuSeparator extends DroppyMenuItemAbstract {

    public DroppyMenuSeparator() {
        type = TYPE_MENU_SEPARATOR;
        setId(-1);
        setClickable(false);
    }

    @Override
    public View render(Context context) {
        if (renderedView == null) {
            renderedView = new com.shehabic.droppy.views.DroppyMenuSeparator(context);
        }

        return renderedView;
    }
}
