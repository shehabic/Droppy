package com.shehabic.droppy;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Point;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.shehabic.droppy.animations.DroppyAnimation;
import com.shehabic.droppy.views.DroppyMenuContainerView;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class DroppyMenuPopup {
    protected Context mContext;
    protected View anchor;
    protected List<DroppyMenuItemInterface> menuItems = new ArrayList<DroppyMenuItemInterface>();
    protected View mContentView;
    protected com.shehabic.droppy.views.DroppyMenuPopupView mPopupView;
    protected DroppyMenuContainerView droppyMenuContainer;
    protected DroppyClickCallbackInterface droppyClickCallbackInterface;
    protected int popupMenuLayoutResourceId;
    protected FrameLayout modalWindow;
    protected int mPopupWidth;
    protected int mPopupHeight;
    protected int statusBarHeight = -1;
    protected OnDismissCallback mOnDismissCallback;
    protected int offsetX;
    protected int offsetY;
    protected DroppyAnimation popupAnimation;

    public void setOffsetY(int offsetY)
    {
        this.offsetY = offsetY;
    }

    public void setOffsetX(int offsetX)
    {
        this.offsetX = offsetX;
    }

    public interface OnDismissCallback {
        public void call();
    }

    protected DroppyMenuPopup(
        Context mContext,
        View parentMenuItem,
        List<DroppyMenuItemInterface> menuItem,
        DroppyClickCallbackInterface droppyClickCallbackInterface,
        boolean addTriggerOnAnchorClick,
        int popupMenuLayoutResourceId,
        OnDismissCallback onDismissCallback
    ) {
        this.mContext = mContext;
        this.anchor = parentMenuItem;
        this.menuItems = menuItem;
        this.droppyClickCallbackInterface = droppyClickCallbackInterface;
        this.popupMenuLayoutResourceId = popupMenuLayoutResourceId;
        this.mOnDismissCallback = onDismissCallback;
        if (addTriggerOnAnchorClick) {
            anchor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show();
                }
            });
        }
    }

    public View getMenuView() {
        return mPopupView;
    }

    public DroppyMenuItemInterface getMenuItemById(int id) {
        for (DroppyMenuItemInterface menuItem : menuItems) {
            if (menuItem.getId() == id) {
                return menuItem;
            }
        }

        return null;
    }

    protected void addModal() {
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.leftMargin = 0;
        lp.topMargin = 0;
        modalWindow = new FrameLayout(mContext);
        modalWindow.setClickable(true);
        modalWindow.setLayoutParams(lp);
        modalWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(false);
            }
        });
        lp.topMargin -= getActivity(mContext).getWindow().getDecorView().getTop();
        getActivity(mContext).getWindow().addContentView(modalWindow, lp);
    }

    public void show() {
        addModal();
        render();
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        adjustDropDownPosition(lp, offsetX, offsetY);
        mContentView = new PopupViewContainer(mContext);
        detachPopupView();
        ((ViewGroup) mContentView).addView(mPopupView);
        mContentView.setFocusable(true);
        mContentView.setClickable(true);
        getActivity(mContext).getWindow().addContentView(mContentView, lp);
        mContentView.requestFocus();
        if (popupAnimation != null) {
            popupAnimation.animateShow(mPopupView, anchor);
        }
    }

    protected void detachPopupView() {
        if (mPopupView.getParent() != null) {
            try {
                ((ViewGroup) mPopupView.getParent()).removeView(mPopupView);
            } catch (Exception e) {

            }
        }
    }

    public void hideAnimationCompleted(boolean itemSelected)
    {
        dismissPopup(itemSelected);
    }

    public void dismiss(boolean itemSelected) {
        if (popupAnimation != null) {
            popupAnimation.animateHide(this, mPopupView, anchor, itemSelected);

            return;
        }
        dismissPopup(itemSelected);
    }

    protected void dismissPopup(boolean itemSelected)
    {
        ((ViewGroup) mContentView.getParent()).removeView(mContentView);
        ((ViewGroup) modalWindow.getParent()).removeView(modalWindow);

        if (!itemSelected && this.mOnDismissCallback != null) {
            mOnDismissCallback.call();
            this.mOnDismissCallback = null;
        }
    }

    protected void render() {
        render(false);
    }

    protected void render(boolean forceRender) {
        if (mPopupView == null || forceRender) {
            if (mPopupView != null && ((ViewGroup) mPopupView).getChildCount() > 0) {
                ((ViewGroup) mPopupView).removeAllViews();
            }
            mPopupView = new com.shehabic.droppy.views.DroppyMenuPopupView(mContext);
            droppyMenuContainer = new DroppyMenuContainerView(mContext);
            mPopupView.addView(droppyMenuContainer);
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mPopupView.setLayoutParams(lp);
            mContentView = mPopupView;
            int i = 0;
            for (DroppyMenuItemInterface droppyMenuItem : this.menuItems) {
                addMenuItemView(droppyMenuItem, i);
                if (droppyMenuItem.isClickable()) {
                    i++;
                }
            }
        }
        mPopupView.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWidth = mPopupView.getMeasuredWidth();
        mPopupHeight = mPopupView.getMeasuredHeight();
    }

    protected void callOnClick(final View v, final int id) {
        if (this.droppyClickCallbackInterface != null) {
            droppyClickCallbackInterface.call(v, id);
            dismiss(true);
        }
    }

    protected void addMenuItemView(DroppyMenuItemInterface menuItem, final int id) {
        final View menuItemView = menuItem.render(mContext);

        if (menuItem.isClickable()) {

            menuItemView.setId(id);
            if (menuItem.getId() == -1) {
                menuItem.setId(id);
            }
            final int clickableItemId = menuItem.getId();

            menuItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callOnClick(v, clickableItemId);
                }
            });
        }
        droppyMenuContainer.addView(menuItemView);
    }

    protected Point getScreenSize() {
        Point size = new Point();
        getActivity(anchor.getContext()).getWindowManager().getDefaultDisplay().getSize(size);

        return size;
    }

    protected boolean isTranslucentStatusBar() {
        Window w = getActivity(anchor.getContext()).getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        int flags = lp.flags;

        return (flags & WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS) == WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
    }

    protected int getStatusBarHeight() {
        if (statusBarHeight == -1 && isTranslucentStatusBar()) {
            statusBarHeight = 0;
        } else if (statusBarHeight == -1) {
            int result = 0;
            int resourceId = anchor.getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = anchor.getContext().getResources().getDimensionPixelSize(resourceId);
            }
            statusBarHeight = result;
        }
        return statusBarHeight;
    }

    protected Point getAnchorCoordinates() {
        int[] coords = new int[2];
        anchor.getLocationOnScreen(coords);

        return new Point(coords[0], coords[1] - getStatusBarHeight());
    }

    protected void setPopupAnimation(DroppyAnimation popupAnimation)
    {
        this.popupAnimation = popupAnimation;
    }

    protected void adjustDropDownPosition(FrameLayout.LayoutParams params, int xOffset, int yOffset) {
        Point anchorPosition = getAnchorCoordinates();
        int finalX = anchorPosition.x + xOffset;
        final int anchorHeight = anchor.getHeight();
        int finalY = anchorPosition.y + anchorHeight;
        Point screen = getScreenSize();
        int rightMargin = screen.x - (finalX + mPopupView.getMeasuredWidth());
        if (rightMargin < 0) {
            finalX = screen.x - (mPopupWidth + xOffset);
        }

        if ((finalY + mPopupHeight) > screen.y /*&& p.y > mPopupHeight*/) {
            finalY = anchorPosition.y - mPopupHeight - (-1 * yOffset);
        }

        params.leftMargin = Math.max(0, finalX);
        params.topMargin = Math.max(0, finalY);
        params.gravity = Gravity.LEFT | Gravity.TOP;


        int maxDistanceAbove = anchorPosition.y;
        int maxDistanceBelow = screen.y - anchorHeight - anchorPosition.y - offsetY;
        boolean popupAboveAnchor = maxDistanceAbove > maxDistanceBelow;
        boolean noRoomAbove = popupAboveAnchor && (anchorPosition.y < mPopupHeight);
        boolean noRoomBelow = !popupAboveAnchor && (mPopupHeight > (maxDistanceBelow));
        if (noRoomAbove || noRoomBelow) {
            if (popupAboveAnchor) {
                params.height = maxDistanceAbove;
                params.topMargin = 0;
            } else {
                params.height = maxDistanceBelow;
                params.topMargin = anchorHeight + anchorPosition.y;
            }
        }
    }

    protected class PopupViewContainer extends FrameLayout {
        public PopupViewContainer(Context context) {
            super(context);
        }
    }

    protected static Activity getActivity(Context context) {

        if (context instanceof Activity) {
            return (Activity) context;
        } else if (context instanceof ContextWrapper) {
            return getActivity(((ContextWrapper) context).getBaseContext());
        }

        return null;
    }

    public static class Builder {
        protected Context ctx;
        protected View parentMenuItem;
        protected List<DroppyMenuItemInterface> menuItems = new ArrayList<DroppyMenuItemInterface>();
        protected DroppyClickCallbackInterface callbackInterface;
        protected boolean triggerOnAnchorClick = true;
        protected OnDismissCallback onDismissCallback;
        protected int offsetX = -20;
        protected int offsetY = 25;
        protected DroppyAnimation droppyAnimation;

        public Builder(Context ctx, View parentMenuItem) {
            this.ctx = ctx;
            this.parentMenuItem = parentMenuItem;
        }

        public Builder addMenuItem(DroppyMenuItemInterface droppyMenuItem) {
            menuItems.add(droppyMenuItem);

            return this;
        }

        public Builder addSeparator() {
            menuItems.add(new DroppyMenuSeparator());

            return this;
        }

        public Builder setOnClick(DroppyClickCallbackInterface droppyClickCallbackInterface1) {
            callbackInterface = droppyClickCallbackInterface1;

            return this;
        }

        public Builder setXOffset(int xOffset)
        {
            this.offsetX = xOffset;

            return this;
        }

        public Builder setYOffset(int yOffset)
        {
            this.offsetY = yOffset;

            return this;
        }

        public Builder triggerOnAnchorClick(boolean onAnchorClick) {
            triggerOnAnchorClick = onAnchorClick;
            return this;
        }

        public Builder setOnDismissCallback(OnDismissCallback onDismissCallback) {
            this.onDismissCallback = onDismissCallback;
            return this;
        }

        public Builder setPopupAnimation(DroppyAnimation droppyAnimation)
        {
            this.droppyAnimation = droppyAnimation;
            return this;
        }

        public Builder fromMenu(int menuResourceId) {
            Menu menu = newMenuInstance(ctx);
            MenuInflater menuInflater = new MenuInflater(ctx);
            menuInflater.inflate(menuResourceId, menu);
            int lastGroupId = menu.getItem(0).getGroupId();
            for (int i = 0; i < menu.size(); i++) {
                MenuItem mItem = menu.getItem(i);
                DroppyMenuItem dMenuItem = new DroppyMenuItem(mItem.getTitle().toString());

                if (mItem.getIcon() != null) {
                    dMenuItem.setIcon(mItem.getIcon());
                }

                if (mItem.getItemId() > 0) {
                    dMenuItem.setId(mItem.getItemId());
                }

                if (mItem.getGroupId() != lastGroupId) {
                    menuItems.add(new DroppyMenuSeparator());
                    lastGroupId = mItem.getGroupId();
                }

                menuItems.add(dMenuItem);
            }

            return this;
        }

        protected Menu newMenuInstance(Context context) {
            try {
                Class<?> menuBuilderClass = Class.forName("com.android.internal.view.menu.MenuBuilder");
                Constructor<?> constructor = menuBuilderClass.getDeclaredConstructor(Context.class);

                return (Menu) constructor.newInstance(context);
            } catch (Exception e) {

            }

            return null;
        }

        public DroppyMenuPopup build() {
            DroppyMenuPopup popup =  new DroppyMenuPopup(ctx, parentMenuItem, menuItems, callbackInterface, triggerOnAnchorClick, -1, onDismissCallback);
            popup.setOffsetX(offsetX);
            popup.setOffsetY(offsetY);
            popup.setPopupAnimation(droppyAnimation);

            return popup;
        }
    }
}


