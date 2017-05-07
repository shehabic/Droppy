package com.shehabic.droppy;

import android.content.Context;
import android.view.View;

/**
 * Created by shehabic on 2/28/15.
 */
public interface DroppyMenuItemInterface {
    /**
     * Get type of droppy_menu item (e.g. droppy_menu, droppy_separator .... more type to be defined later)
     *
     * @return String
     */
    int getType();

    /**
     * @param context
     * @return View rendered/inflated view
     */
    View render(Context context);

    /**
     * @param id Ideally the position in the list
     * @return DroppyMenuItemInterface
     */
    DroppyMenuItemInterface setId(int id);

    /**
     * @param isClickable
     * @return DroppyMenuItemInterface
     */
    DroppyMenuItemInterface setClickable(boolean isClickable);

    /**
     * @return int
     */
    int getId();

    /**
     * @return boolean
     */
    boolean isClickable();

    /**
     * @return View
     */
    View getView();
}
