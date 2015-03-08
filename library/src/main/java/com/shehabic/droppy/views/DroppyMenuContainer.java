package com.shehabic.droppy.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.shehabic.droppy.R;

/**
 * Created by shehabic on 3/6/15.
 */
public class DroppyMenuContainer extends LinearLayout
{
    public DroppyMenuContainer(Context context) {
        this(context, null);
    }

    public DroppyMenuContainer(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.droppyMenuStyle);
    }

    public DroppyMenuContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOrientation(VERTICAL);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DroppyMenuPopup, defStyleAttr, 0);
        ViewGroup.LayoutParams lp = getLayoutParams();
        int height = (int) a.getDimension(R.styleable.DroppyMenuContainer_android_layout_height, ViewGroup.LayoutParams.WRAP_CONTENT);
        int width = (int) a.getDimension(R.styleable.DroppyMenuContainer_android_layout_width, ViewGroup.LayoutParams.WRAP_CONTENT);

        if (lp == null) {
            lp = new ViewGroup.LayoutParams(width, height);
        } else {
            lp.width = width;
            lp.height = height;
        }
        setLayoutParams(lp);
    }
}
