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
public class DroppyMenuContainerView extends LinearLayout {
    public DroppyMenuContainerView(Context context) {
        this(context, null);
    }

    public DroppyMenuContainerView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.droppyMenuStyle);
    }

    public DroppyMenuContainerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOrientation(VERTICAL);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DroppyMenuPopupView, defStyleAttr, 0);
        ViewGroup.LayoutParams lp = getLayoutParams();
        int height = a.getLayoutDimension(R.styleable.DroppyMenuContainerView_android_layout_height, ViewGroup.LayoutParams.WRAP_CONTENT);
        int width = a.getLayoutDimension(R.styleable.DroppyMenuContainerView_android_layout_width, ViewGroup.LayoutParams.WRAP_CONTENT);

        if (lp == null) {
            lp = new ViewGroup.LayoutParams(width, height);
        } else {
            lp.width = width;
            lp.height = height;
        }
        setLayoutParams(lp);
        a.recycle();
    }
}
