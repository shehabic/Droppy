package com.shehabic.droppy;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

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
    DroppyClickCallbackInterface droppyClickCallbackInterface;
    int popupMenuLayoutResourceId;

    DroppyMenu(
        Context ctx,
        View parentMenuItem,
        List<DroppyMenuItemInterface> menuItem,
        DroppyClickCallbackInterface droppyClickCallbackInterface,
        int popupMenuLayoutResourceId
    ) {
        this.ctx = ctx;
        this.parentMenuItem = parentMenuItem;
        this.menuItems = menuItem;
        this.droppyClickCallbackInterface = droppyClickCallbackInterface;
        if (popupMenuLayoutResourceId == -1) {
            popupMenuLayoutResourceId = R.layout.droppy_menu;
        }
        this.popupMenuLayoutResourceId = popupMenuLayoutResourceId;
    }

    public void show()
    {
        render();
        popupMenu.setVisibility(View.GONE);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(0, 0);
        popupMenu.setLayoutParams(lp);
        popupMenu.setVisibility(View.VISIBLE);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ((Activity) ctx).addContentView(popupMenu, params);
    }

    public void notifyChange()
    {
        render(true);
    }

    void render()
    {
        render(false);
    }

    void render(boolean forceRender)
    {
        if (popupMenu == null || forceRender == true) {
            if (popupMenu != null && ((ViewGroup) popupMenu).getChildCount() > 0) {
                ((ViewGroup) popupMenu).removeAllViews();
            }
            popupMenu = LayoutInflater.from(ctx).inflate(popupMenuLayoutResourceId, null);
            int i = 0;
            for (DroppyMenuItemInterface droppyMenuItem: this.menuItems) {
                addMenuItemView(droppyMenuItem, i);
                i++;
            }

        }
    }

    void callOnClick(final View v, final int id) {
        if (this.droppyClickCallbackInterface != null) {
            droppyClickCallbackInterface.call(v, id);
        }
    }

    void addMenuItemView(DroppyMenuItemInterface menuItem, final int id)
    {
        final View menuItemView = menuItem.render(ctx);
        if (menuItem.getId() == -1) {
            menuItem.setId(id);
        }
        if (menuItem.isClickable()) {
            menuItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callOnClick(v, id);
                }
            });
        }
        ((ViewGroup) popupMenu.findViewById(R.id.droppyMenu)).addView(menuItemView);
    }

    public static class Builder
    {
        private Context ctx;
        private View parentMenuItem;
        private List<DroppyMenuItemInterface> menuItems = new ArrayList<DroppyMenuItemInterface>();
        private DroppyClickCallbackInterface callbackInterface;

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

        public Builder setOnClick(DroppyClickCallbackInterface droppyClickCallbackInterface1)
        {
            callbackInterface = droppyClickCallbackInterface1;

            return this;
        }

        public DroppyMenu build()
        {
            return new DroppyMenu(ctx, parentMenuItem, menuItems, callbackInterface, -1);
        }
    }
}
