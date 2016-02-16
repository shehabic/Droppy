package com.shehabic.droppy.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shehabic.droppy.R;

/**
 * Created by shehabic on 3/7/15.
 */
public class DroppyMenuItemTitleView extends TextView {

    public DroppyMenuItemTitleView(Context context) {
        this(context, null);
    }

    public DroppyMenuItemTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.droppyMenuItemTitleStyle);
    }

    public DroppyMenuItemTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final int defaultWidth = ViewGroup.LayoutParams.MATCH_PARENT;
        final int defaultMinWidth = (int) getResources().getDimension(R.dimen.default_menu_item_title_minWidth);
        final int defaultMinHeight = (int) getResources().getDimension(R.dimen.default_menu_item_title_minHeight);
        final float defaultWeight = 1;
        final int defaultColor = getResources().getColor(R.color.default_menu_item_title_textColor);
        final int defaultGravity = Gravity.CENTER_VERTICAL;
        final int defaultLayoutGravity = Gravity.END | Gravity.CENTER_VERTICAL;

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DroppyMenuItemTitleView, defStyleAttr, 0);
        int minWidth = (int) a.getDimension(R.styleable.DroppyMenuItemTitleView_android_minWidth, defaultMinWidth);
        int minHeight = (int) a.getDimension(R.styleable.DroppyMenuItemTitleView_android_minHeight, defaultMinHeight);
        int width = a.getLayoutDimension(R.styleable.DroppyMenuItemTitleView_android_layout_width, defaultWidth);
        int height = a.getLayoutDimension(R.styleable.DroppyMenuItemTitleView_android_layout_height, ViewGroup.LayoutParams.WRAP_CONTENT);
        int color = a.getColor(R.styleable.DroppyMenuItemTitleView_android_textColor, defaultColor);

        setGravity(a.getInt(R.styleable.DroppyMenuItemTitleView_android_gravity, defaultGravity));

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, height);
        lp.width = width;
        lp.height = height;
        lp.weight = a.getFloat(R.styleable.DroppyMenuItemTitleView_android_layout_weight, defaultWeight);
        lp.gravity = a.getInteger(R.styleable.DroppyMenuItemTitleView_android_layout_gravity, defaultLayoutGravity);

        setLayoutParams(lp);
        setMinHeight(minWidth);
        setMinHeight(minHeight);

        setTextColor(color);
        a.recycle();
    }
}
