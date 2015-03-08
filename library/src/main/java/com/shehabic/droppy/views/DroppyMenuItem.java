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
public class DroppyMenuItem extends LinearLayout {

    public DroppyMenuItem(Context context) {
        this(context, null);
    }

    public DroppyMenuItem(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.droppyMenuItemStyle);
    }

    public DroppyMenuItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DroppyMenuItem, defStyleAttr, 0);

        final Drawable defaultDrawable = getResources().getDrawable(R.drawable.default_menu_item_background);
        final float defaultMinWidth = getResources().getDimension(R.dimen.default_menu_item_minWidth);
        final float defaultMinHeight = getResources().getDimension(R.dimen.default_menu_item_minHeight);
        final boolean defaultIsClickable = getResources().getBoolean(R.bool.default_menu_item_clickable);

        float minWidth = a.getDimension(R.styleable.DroppyMenuItem_android_minWidth, defaultMinWidth);
        float minHeight = a.getDimension(R.styleable.DroppyMenuItem_android_minHeight, defaultMinHeight);

        ViewGroup.LayoutParams lp = getLayoutParams();
        setMinimumWidth((int) minWidth);
        setMinimumHeight((int) minHeight);
        int width = a.getInteger(R.styleable.DroppyMenuItem_android_layout_height, ViewGroup.LayoutParams.WRAP_CONTENT);
        int height = a.getInteger(R.styleable.DroppyMenuItem_android_layout_width, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(width, height);
        } else {
            lp.width = width;
            lp.height = height;
        }
        setClickable(a.getBoolean(R.styleable.DroppyMenuItem_android_clickable, defaultIsClickable));
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(a.getInteger(R.styleable.DroppyMenuItem_android_gravity, Gravity.CENTER_VERTICAL));
        int paddingTop = a.getInteger(R.styleable.DroppyMenuItem_android_paddingTop, (int) getResources().getDimension(R.dimen.default_menu_item_paddingTop));
        int paddingBottom = a.getInteger(R.styleable.DroppyMenuItem_android_paddingBottom, (int) getResources().getDimension(R.dimen.default_menu_item_paddingBottom));
        int paddingLeft = a.getInteger(R.styleable.DroppyMenuItem_android_paddingLeft, (int) getResources().getDimension(R.dimen.default_menu_item_paddingLeft));
        int paddingRight = a.getInteger(R.styleable.DroppyMenuItem_android_paddingRight, (int) getResources().getDimension(R.dimen.default_menu_item_paddingRight));
        setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);

        Drawable background = a.getDrawable(R.styleable.DroppyMenuItem_android_background);
        if (background != null) {
            setBackgroundDrawable(background);
        } else {
            setBackgroundDrawable(defaultDrawable);
        }

        this.setLayoutParams(lp);
    }
}
