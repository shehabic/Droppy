package com.shehabic.droppy.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.shehabic.droppy.R;

/**
 * Created by shehabic on 3/8/15.
 */
public class DroppyMenuSeparator extends LinearLayout {
    public DroppyMenuSeparator(Context context) {
        this(context, null);
    }

    public DroppyMenuSeparator(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.droppyMenuSeparatorStyle);
    }

    public DroppyMenuSeparator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DroppyMenuItem, defStyleAttr, 0);

        final Drawable defaultSeparatorBackground = getResources().getDrawable(R.drawable.droppy_separator_background);
        final int defaultHeight = getResources().getDimensionPixelSize(R.dimen.default_menu_separator_height);
        ViewGroup.LayoutParams lp = getLayoutParams();
        int width = a.getLayoutDimension(R.styleable.DroppyMenuItem_android_layout_width, ViewGroup.LayoutParams.MATCH_PARENT);
        int height = a.getLayoutDimension(R.styleable.DroppyMenuItem_android_layout_height, defaultHeight);
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(width, height);
        } else {
            lp.width = width;
            lp.height = height;
        }
        setOrientation(LinearLayout.HORIZONTAL);

        Drawable background = a.getDrawable(R.styleable.DroppyMenuSeparator_android_background);
        if (background != null) {
            setBackgroundDrawable(background);
        } else {
            setBackgroundDrawable(defaultSeparatorBackground);
        }

        this.setLayoutParams(lp);
    }
}
