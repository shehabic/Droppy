package com.shehabic.droppy;

import android.content.Context;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shehabic on 2/28/15.
 */
public class DroppyMenu {
    Context ctx;
    View parentMenuItem;
    List<DroppyMenuItemInterface> menuItems = new ArrayList<DroppyMenuItemInterface>();
    View popupMenu;

    DroppyMenu(Context ctx, View parentMenuItem)
    {
        this.ctx = ctx;
        this.parentMenuItem = parentMenuItem;
    }

    DroppyMenu(Context ctx, View parentMenuItem, List<DroppyMenuItemInterface> menuItem)
    {
        this.ctx = ctx;
        this.parentMenuItem = parentMenuItem;
        this.menuItems = menuItem;
    }

    public void show()
    {

    }

    public static class Builder
    {
        private Context ctx;
        private View parentMenuItem;
        private List<DroppyMenuItemInterface> menuItems = new ArrayList<DroppyMenuItemInterface>();

        public Builder(Context ctx, View parentMenuItem)
        {
            this.ctx = ctx;
            this.parentMenuItem = parentMenuItem;
        }

        public Builder addMenuItem(DroppyMenuItemInterface droppyMenuItem)
        {
            menuItems.add(droppyMenuItem);

            return this;
        }

        public Builder addSeparator()
        {
            menuItems.add(new DroppyMenuSeparator());

            return this;
        }

        public DroppyMenu build()
        {
            return new DroppyMenu(ctx, parentMenuItem, menuItems);
        }
    }
}
