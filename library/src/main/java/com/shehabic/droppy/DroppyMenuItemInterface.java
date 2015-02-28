package com.shehabic.droppy;

import android.content.Context;
import android.view.View;

/**
 * Created by shehabic on 2/28/15.
 */
public interface DroppyMenuItemInterface {
    /**
     * Get type of menu item (e.g. menu, separator .... more type to be defined later)
     *
     * @return String
     */
    public int getType();

    /**
     * @param context
     *
     * @return View rendered/inflated view
     */
    public View render(Context context);

    /**
     * @param id Ideally the position in the list
     *
     * @return DroppyMenuItemInterface
     */
    public DroppyMenuItemInterface setId(int id);

    /**
     * @param isClickable
     *
     * @return DroppyMenuItemInterface
     */
    public DroppyMenuItemInterface setClickable(boolean isClickable);

    /**
     * @return int
     */
    public int getId();

    /**
     * @return boolean
     */
    public boolean isClickable();
}
