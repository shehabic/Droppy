package com.shehabic.droppy;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by shehabic on 2/28/15.
 */
public class DroppyMenuItem extends DroppyMenuItemAbstract {

    void initMenuItem(String title, int iconResourceId)
    {
        this.title = title;
        if (iconResourceId > 0) {
            this.icon = iconResourceId;
        }
        this.customViewResourceId = R.layout.droppy_menu_item;
    }

    public DroppyMenuItem(String title)
    {
        initMenuItem(title, -1);
    }

    public DroppyMenuItem(String title, int iconResourceId)
    {
        initMenuItem(title, iconResourceId);

    }

    @Override
    public View render(Context context) {
        super.render(context);

        ((TextView) this.renderedView.findViewById(R.id.title)).setText(this.title);

        if (this.icon != -1) {
            ((ImageView) this.renderedView.findViewById(R.id.icon)).setImageResource(this.icon);
        }

        return renderedView;
    }
}
