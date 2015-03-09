package com.shehabic.droppy;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shehabic.droppy.views.DroppyMenuItemIcon;
import com.shehabic.droppy.views.DroppyMenuItemTitle;

/**
 * Created by shehabic on 2/28/15.
 */
public class DroppyMenuItem extends DroppyMenuItemAbstract {

    private Drawable iconDrawable;
    protected com.shehabic.droppy.views.DroppyMenuItem renderedView;

    void initMenuItem(String title, int iconResourceId)
    {
        this.title = title;
        if (iconResourceId > 0) {
            this.icon = iconResourceId;
        }
    }

    public DroppyMenuItem(String title)
    {
        initMenuItem(title, -1);
    }

    public DroppyMenuItem(String title, int iconResourceId)
    {
        initMenuItem(title, iconResourceId);

    }

    public void setIcon(Drawable iconDrawable)
    {
        this.iconDrawable = iconDrawable;
    }

    @Override
    public View render(Context context) {
        
        renderedView = new com.shehabic.droppy.views.DroppyMenuItem(context);

        if (this.icon != -1) {
            DroppyMenuItemIcon droppyMenuItemIcon = new DroppyMenuItemIcon(context);
            droppyMenuItemIcon.setImageResource(this.icon);
        } else if (this.iconDrawable != null) {
            DroppyMenuItemIcon droppyMenuItemIcon = new DroppyMenuItemIcon(context);
            droppyMenuItemIcon.setImageDrawable(iconDrawable);
            renderedView.addView(droppyMenuItemIcon);
        }

        DroppyMenuItemTitle droppyMenuItemTitle = new DroppyMenuItemTitle(context);
        droppyMenuItemTitle.setText(this.title);
        renderedView.addView(droppyMenuItemTitle);

        return renderedView;
    }
}
