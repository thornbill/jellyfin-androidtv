package org.jellyfin.androidtv.ui.presentation;

import android.graphics.drawable.Drawable;

import androidx.leanback.widget.FocusHighlight;
import androidx.leanback.widget.FocusHighlightHelper;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.RowPresenter;

import timber.log.Timber;

public class PositionableListRowPresenter extends CustomListRowPresenter implements IPositionablePresenter {

    private ListRowPresenter.ViewHolder viewHolder;

    public PositionableListRowPresenter() {
        super();
        setShadowEnabled(false);
    }

    public PositionableListRowPresenter(Drawable background, Integer padding) {
        super(background, padding);
        setShadowEnabled(false);
    }

    public PositionableListRowPresenter(Integer padding) {
        super(padding);
        setShadowEnabled(false);
    }

    @Override
    public boolean isUsingDefaultShadow() {
        return false;
    }

    @Override
    protected void onSelectLevelChanged(RowPresenter.ViewHolder holder) {
        //Do nothing - this removes the shadow on the out of focus rows of image cards
    }

    @Override
    protected void onBindRowViewHolder(RowPresenter.ViewHolder holder, Object item) {
        super.onBindRowViewHolder(holder, item);

        viewHolder = (ViewHolder) holder;
        if (viewHolder != null && viewHolder.getGridView() != null) {
            viewHolder.getGridView().setHorizontalSpacing(32);
            FocusHighlightHelper.setupBrowseItemFocusHighlight(
                    viewHolder.getBridgeAdapter(),
                    FocusHighlight.ZOOM_FACTOR_SMALL,
                    false
            );
        }
    }

    public void setPosition(int ndx) {
        Timber.d("Setting position to: %d", ndx);
        if (viewHolder != null && viewHolder.getGridView() != null) viewHolder.getGridView().setSelectedPosition(ndx);
    }

    public int getPosition() {
        return viewHolder != null && viewHolder.getGridView() != null ? viewHolder.getGridView().getSelectedPosition() : -1;
    }
}
