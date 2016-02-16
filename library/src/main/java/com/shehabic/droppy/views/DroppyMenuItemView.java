package com.shehabic.droppy.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.shehabic.droppy.R;

/**
 * Created by shehabic on 3/7/15.
 */
public class DroppyMenuItemView extends LinearLayout {

    public DroppyMenuItemView(Context context) {
        this(context, null);
    }

    public DroppyMenuItemView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.droppyMenuItemStyle);
    }

    public DroppyMenuItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DroppyMenuItemView, defStyleAttr, 0);

        final Drawable defaultDrawable = getResources().getDrawable(R.drawable.default_menu_item_background);
        final float defaultMinWidth = getResources().getDimension(R.dimen.default_menu_item_minWidth);
        final float defaultMinHeight = getResources().getDimension(R.dimen.default_menu_item_minHeight);
        final boolean defaultIsClickable = getResources().getBoolean(R.bool.default_menu_item_clickable);

        float minWidth = a.getDimension(R.styleable.DroppyMenuItemView_android_minWidth, defaultMinWidth);
        float minHeight = a.getDimension(R.styleable.DroppyMenuItemView_android_minHeight, defaultMinHeight);

        ViewGroup.LayoutParams lp = getLayoutParams();
        setMinimumWidth((int) minWidth);
        setMinimumHeight((int) minHeight);
        int width = a.getLayoutDimension(R.styleable.DroppyMenuItemView_android_layout_width, ViewGroup.LayoutParams.MATCH_PARENT);
        int height = a.getLayoutDimension(R.styleable.DroppyMenuItemView_android_layout_height, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(width, height);
        } else {
            lp.width = width;
            lp.height = height;
        }
        setClickable(a.getBoolean(R.styleable.DroppyMenuItemView_android_clickable, defaultIsClickable));
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(a.getInteger(R.styleable.DroppyMenuItemView_android_gravity, Gravity.CENTER_VERTICAL));
        int paddingTop = (int) a.getDimension(R.styleable.DroppyMenuItemView_android_paddingTop, (int) getResources().getDimension(R.dimen.default_menu_item_paddingTop));
        int paddingBottom = (int) a.getDimension(R.styleable.DroppyMenuItemView_android_paddingBottom, (int) getResources().getDimension(R.dimen.default_menu_item_paddingBottom));
        int paddingLeft = (int) a.getDimension(R.styleable.DroppyMenuItemView_android_paddingLeft, (int) getResources().getDimension(R.dimen.default_menu_item_paddingLeft));
        int paddingRight = (int) a.getDimension(R.styleable.DroppyMenuItemView_android_paddingRight, (int) getResources().getDimension(R.dimen.default_menu_item_paddingRight));
        setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);

        Drawable background = a.getDrawable(R.styleable.DroppyMenuItemView_android_background);
        if (background != null) {
            setBackgroundDrawable(background);
        } else {
            setBackgroundDrawable(defaultDrawable);
        }

        this.setLayoutParams(lp);
        a.recycle();
    }
}
