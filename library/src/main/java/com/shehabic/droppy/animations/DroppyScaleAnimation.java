package com.shehabic.droppy.animations;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.shehabic.droppy.DroppyMenuPopup;
import com.shehabic.droppy.views.DroppyMenuPopupView;

public class DroppyScaleAnimation implements DroppyAnimation
{
    private static int ANIMATION_DURATION = 200;

    @Override
    public void animateHide(final DroppyMenuPopup popup, final DroppyMenuPopupView popupView, final View anchor, final boolean itemSelected)
    {
        ScaleAnimation scaleAnimation
            = new ScaleAnimation(1f, 0f, 1f, 0f, Animation.RELATIVE_TO_SELF, 0.25f, Animation.RELATIVE_TO_SELF, 1f);
        scaleAnimation.setDuration(ANIMATION_DURATION);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener()
        {
            @Override public void onAnimationStart(Animation animation) { }
            @Override public void onAnimationRepeat(Animation animation) { }
            @Override
            public void onAnimationEnd(Animation animation)
            {
                popup.hideAnimationCompleted(itemSelected);
            }

        });
        popupView.startAnimation(scaleAnimation);
    }

    @Override
    public void animateShow(final DroppyMenuPopupView popup, final View anchor)
    {
        ScaleAnimation scaleAnimation
            = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.25f, Animation.RELATIVE_TO_SELF, 1f);
        scaleAnimation.setDuration(ANIMATION_DURATION);
        scaleAnimation.setFillAfter(true);
        popup.startAnimation(scaleAnimation);
    }
}
