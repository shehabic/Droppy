package com.shehabic.droppy.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shehabic.droppy.R;

/**
 * Created by shehabic on 3/7/15.
 */
public class DroppyMenuItemIconView extends ImageView {

    public DroppyMenuItemIconView(Context context) {
        this(context, null);
    }

    public DroppyMenuItemIconView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.droppyMenuItemIconStyle);
    }

    public DroppyMenuItemIconView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final int defaultMaxWidth = (int) getResources().getDimension(R.dimen.default_menu_item_icon_maxWidth);
        final int defaultMaxHeight = (int) getResources().getDimension(R.dimen.default_menu_item_icon_maxHeight);
        final float defaultWeight = 0;
        final int defaultLayoutGravity = Gravity.START | Gravity.CENTER_VERTICAL;
        final int defaultMarginLeft = (int) getResources().getDimension(R.dimen.default_menu_item_icon_marginLeft);
        final int defaultMarginRight= (int) getResources().getDimension(R.dimen.default_menu_item_icon_marginRight);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DroppyMenuItemIconView, defStyleAttr, 0);
        int maxWidth = (int) a.getDimension(R.styleable.DroppyMenuItemIconView_android_maxWidth, defaultMaxWidth);
        int maxHeight = (int) a.getDimension(R.styleable.DroppyMenuItemIconView_android_maxHeight, defaultMaxHeight);
        int width = a.getLayoutDimension(R.styleable.DroppyMenuItemIconView_android_layout_width, ViewGroup.LayoutParams.WRAP_CONTENT);
        int height = a.getLayoutDimension(R.styleable.DroppyMenuItemIconView_android_layout_height, ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, height);
        lp.rightMargin = a.getDimensionPixelSize(R.styleable.DroppyMenuItemIconView_android_layout_marginRight, defaultMarginRight);
        lp.leftMargin = a.getDimensionPixelSize(R.styleable.DroppyMenuItemIconView_android_layout_marginLeft, defaultMarginLeft);
        lp.width = width;
        lp.height = height;
        lp.weight = a.getFloat(R.styleable.DroppyMenuItemIconView_android_layout_weight, defaultWeight);
        lp.gravity = a.getInteger(R.styleable.DroppyMenuItemIconView_android_layout_gravity, defaultLayoutGravity);

        setMaxHeight(maxWidth);
        setMaxHeight(maxHeight);
        setLayoutParams(lp);
        a.recycle();
    }
}
