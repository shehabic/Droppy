package com.shehabic.droppy.animations;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.shehabic.droppy.DroppyMenuPopup;
import com.shehabic.droppy.views.DroppyMenuPopupView;

public class DroppyFadeInAnimation implements DroppyAnimation {

    private static int ANIMATION_DURATION = 200;

    @Override
    public void animateHide(final DroppyMenuPopup popup, final DroppyMenuPopupView popupView, final View anchor, final boolean itemSelected) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
        alphaAnimation.setDuration(ANIMATION_DURATION);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                popup.hideAnimationCompleted(itemSelected);
            }

        });
        popupView.startAnimation(alphaAnimation);
    }

    @Override
    public void animateShow(final DroppyMenuPopupView popup, final View anchor) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
        alphaAnimation.setDuration(ANIMATION_DURATION);
        alphaAnimation.setFillAfter(true);
        popup.startAnimation(alphaAnimation);
    }
}
