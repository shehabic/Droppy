package com.shehabic.droppy;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shehabic on 2/28/15.
 */
public class DroppyMenu {
    private WindowManager mWindowManager;
    private Context mContext;
    private View anchor;
    private List<DroppyMenuItemInterface> menuItems = new ArrayList<DroppyMenuItemInterface>();
    private View mContentView;
    private View mPopupView;
    private DroppyClickCallbackInterface droppyClickCallbackInterface;
    private int popupMenuLayoutResourceId;
    private int windowWidth = 0;
    private int windowHeight = 0;
    private FrameLayout modalWindow;
    private View.OnTouchListener mTouchInterceptor;
    private Drawable mBackground;

    private static final int[] ABOVE_ANCHOR_STATE_SET = new int[] {
        -300215
    };

    /**
     * Set a callback for all touch events being dispatched to the popup
     * window.
     */
    public void setTouchInterceptor(View.OnTouchListener l) {
        mTouchInterceptor = l;
    }

    DroppyMenu(
        Context mContext,
        View parentMenuItem,
        List<DroppyMenuItemInterface> menuItem,
        DroppyClickCallbackInterface droppyClickCallbackInterface,
        int popupMenuLayoutResourceId
    ) {
        this.mContext = mContext;
        this.anchor = parentMenuItem;
        this.menuItems = menuItem;
        this.droppyClickCallbackInterface = droppyClickCallbackInterface;
        if (popupMenuLayoutResourceId == -1) {
            popupMenuLayoutResourceId = R.layout.droppy_menu;
        }
        this.popupMenuLayoutResourceId = popupMenuLayoutResourceId;
        measureScreenDimensions();
        mWindowManager = (WindowManager)mContext.getSystemService(mContext.WINDOW_SERVICE);
        mBackground = mContext.getResources().getDrawable(R.drawable.bt_bg_popupmenu);
    }

    private Activity getActivity() {
        return (Activity) mContext;
    }

    public void measureScreenDimensions() {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        windowWidth = size.x;
        windowHeight = size.y;
    }

    public void addModal() {
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.leftMargin = 0;
        lp.topMargin = 0;
        modalWindow = new FrameLayout(mContext);
        modalWindow.setClickable(true);
        modalWindow.setLayoutParams(lp);
        modalWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        lp.topMargin -= getActivity().getWindow().getDecorView().getTop();
        getActivity().getWindow().addContentView(modalWindow, lp);
    }

    public void show() {
        addModal();
        render();
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        adjustDropDownPosition(lp, -20, 30);
        mContentView = new PopupViewContainer(mContext);
        ((ViewGroup) mContentView).addView(mPopupView);
        mContentView.setFocusable(true);
        mContentView.setClickable(true);
        getActivity().getWindow().addContentView(mContentView, lp);
        mContentView.requestFocus();
    }

    public void dismiss() {
        ((ViewGroup) mContentView.getParent()).removeView(mContentView);
        ((ViewGroup) modalWindow.getParent()).removeView(modalWindow);
    }

    public void notifyChange() {
        render(true);
    }

    void render() {
        render(false);
    }

    void render(boolean forceRender) {
        if (mPopupView == null || forceRender == true) {
            if (mPopupView != null && ((ViewGroup) mPopupView).getChildCount() > 0) {
                ((ViewGroup) mPopupView).removeAllViews();
            }
            mPopupView = LayoutInflater.from(mContext).inflate(popupMenuLayoutResourceId, null);
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mPopupView.setLayoutParams(lp);
            mContentView = mPopupView;
            int i = 0;
            for (DroppyMenuItemInterface droppyMenuItem : this.menuItems) {
                addMenuItemView(droppyMenuItem, i);
                i++;
            }
        }
        mPopupView.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWidth = mPopupView.getMeasuredWidth();
        mPopupHeight = mPopupView.getMeasuredHeight();
    }

    void callOnClick(final View v, final int id) {
        if (this.droppyClickCallbackInterface != null) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mPopupView.getLayoutParams();

            droppyClickCallbackInterface.call(v, id);
            dismiss();
        }
    }

    void addMenuItemView(DroppyMenuItemInterface menuItem, final int id) {
        final View menuItemView = menuItem.render(mContext);
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
        ((ViewGroup) mPopupView.findViewById(R.id.droppyMenu)).addView(menuItemView);
    }

    private int mPopupWidth;
    private int mPopupHeight;

    private int[] mDrawingLocation = new int[2];
    private int[] mScreenLocation = new int[2];
    private Rect mTempRect = new Rect();
    private boolean mAllowScrollingAnchorParent = true;
    private boolean mClipToScreen = true;
    private boolean mAboveAnchor;

    protected int statusBarHeight = -1;

    protected Point getScreenSize() {
        Point size = new Point();
        ((Activity) anchor.getContext()).getWindowManager().getDefaultDisplay().getSize(size);
        return size;
    }
    protected boolean isTranslucentStatusBar() {
        Window w = ((Activity) anchor.getContext()).getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        int flags = lp.flags;
        if ((flags & WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS) == WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS) {
            return true;
        }
        return false;
    }

    public int getStatusBarHeight() {
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

    private void adjustDropDownPosition(FrameLayout.LayoutParams params) {
        adjustDropDownPosition(params, 0, 0);
    }
    private void adjustDropDownPosition(FrameLayout.LayoutParams params, int xOffset, int yOffset) {
        Point p = getAnchorCoordinates();
        int finalX = p.x + xOffset;
        final int anchorHeight = anchor.getHeight();
        int finalY = p.y + anchorHeight;
        Point screen = getScreenSize();
        int rightMargin = screen.x - (finalX + mPopupView.getMeasuredWidth());
        if (rightMargin < 0) {
            finalX = screen.x - (mPopupWidth + xOffset);
        }

        if (finalY + mPopupHeight > screen.y && p.y > mPopupHeight) {
            finalY = p.y - mPopupHeight - (-1 * yOffset);
        }

        params.leftMargin = Math.max(0, finalX);
        params.topMargin = Math.max(0, finalY);

        params.gravity = Gravity.LEFT | Gravity.TOP;
    }


    private class PopupViewContainer extends FrameLayout {
        private static final String TAG = "PopupWindow.PopupViewContainer";

        public PopupViewContainer(Context context) {
            super(context);
        }

        @Override
        protected int[] onCreateDrawableState(int extraSpace) {
            return super.onCreateDrawableState(extraSpace);
        }

        @Override
        public boolean dispatchKeyEvent(KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                if (getKeyDispatcherState() == null) {
                    return super.dispatchKeyEvent(event);
                }

                if (event.getAction() == KeyEvent.ACTION_DOWN
                    && event.getRepeatCount() == 0) {
                    KeyEvent.DispatcherState state = getKeyDispatcherState();
                    if (state != null) {
                        state.startTracking(event, this);
                    }
                    return true;
                } else if (event.getAction() == KeyEvent.ACTION_UP) {
                    KeyEvent.DispatcherState state = getKeyDispatcherState();
                    if (state != null && state.isTracking(event) && !event.isCanceled()) {
                        dismiss();
                        return true;
                    }
                }
                return super.dispatchKeyEvent(event);
            } else {
                return super.dispatchKeyEvent(event);
            }
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent ev) {
            if (mTouchInterceptor != null && mTouchInterceptor.onTouch(this, ev)) {
                return true;
            }
            return super.dispatchTouchEvent(ev);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            final int x = (int) event.getX();
            final int y = (int) event.getY();

            if ((event.getAction() == MotionEvent.ACTION_DOWN)
                && ((x < 0) || (x >= getWidth()) || (y < 0) || (y >= getHeight()))) {
                dismiss();
                return true;
            } else if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                dismiss();
                return true;
            } else {
                return super.onTouchEvent(event);
            }
        }

        @Override
        public void sendAccessibilityEvent(int eventType) {
            // clinets are interested in the content not the container, make it event source
            if (mContentView != null) {
                mContentView.sendAccessibilityEvent(eventType);
            } else {
                super.sendAccessibilityEvent(eventType);
            }
        }
    }


    public static class Builder {
        private Context ctx;
        private View parentMenuItem;
        private List<DroppyMenuItemInterface> menuItems = new ArrayList<DroppyMenuItemInterface>();
        private DroppyClickCallbackInterface callbackInterface;

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

        public DroppyMenu build() {
            return new DroppyMenu(ctx, parentMenuItem, menuItems, callbackInterface, -1);
        }
    }

}
