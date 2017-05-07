package com.shehabic.droppy.animations;

import android.view.View;

import com.shehabic.droppy.DroppyMenuPopup;
import com.shehabic.droppy.views.DroppyMenuPopupView;

public interface DroppyAnimation {

    void animateShow(final DroppyMenuPopupView popup, final View anchor);

    void animateHide(final DroppyMenuPopup popup, final DroppyMenuPopupView popupView, final View anchor, final boolean itemSelected);

}
