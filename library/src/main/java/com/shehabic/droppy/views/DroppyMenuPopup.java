package com.shehabic.droppy.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.shehabic.droppy.R;

/**
 * Created by shehabic on 3/6/15.
 */
public class DroppyMenuPopup extends FrameLayout
{
    public DroppyMenuPopup(Context context) {
        this(context, null);
    }

    public DroppyMenuPopup(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.droppyPopupStyle);
    }

    public DroppyMenuPopup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final Drawable defaultDrawable = getResources().getDrawable(R.drawable.default_popup_background);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DroppyMenuPopup, defStyleAttr, 0);
        Drawable background = a.getDrawable(R.styleable.DroppyMenuPopup_android_background);
        int height = (int) a.getDimension(R.styleable.DroppyMenuPopup_android_layout_height, ViewGroup.LayoutParams.WRAP_CONTENT);
        int width = (int) a.getDimension(R.styleable.DroppyMenuPopup_android_layout_width, ViewGroup.LayoutParams.WRAP_CONTENT);
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
    }
}
