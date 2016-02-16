package com.shehabic.droppy.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.shehabic.droppy.R;

/**
 * Created by shehabic on 3/6/15.
 */
public class DroppyMenuPopupView extends ScrollView
{
    public DroppyMenuPopupView(Context context) {
        this(context, null);
    }

    public DroppyMenuPopupView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.droppyPopupStyle);
    }

    public DroppyMenuPopupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final Drawable defaultDrawable = getResources().getDrawable(R.drawable.default_popup_background);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DroppyMenuPopupView, defStyleAttr, 0);
        Drawable background = a.getDrawable(R.styleable.DroppyMenuPopupView_android_background);
        int height = a.getLayoutDimension(R.styleable.DroppyMenuPopupView_android_layout_height, ViewGroup.LayoutParams.WRAP_CONTENT);
        int width = a.getLayoutDimension(R.styleable.DroppyMenuPopupView_android_layout_width, ViewGroup.LayoutParams.WRAP_CONTENT);
        ViewGroup.LayoutParams lp = getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(width, height);
        } else {
            lp.width = width;
            lp.height = height;
        }
        this.setLayoutParams(lp);

        if (background != null) {
            setBackgroundDrawable(background);
        } else {
            setBackgroundDrawable(defaultDrawable);
        }
        a.recycle();
    }
}
