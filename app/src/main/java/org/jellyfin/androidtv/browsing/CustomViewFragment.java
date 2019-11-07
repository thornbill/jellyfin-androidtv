package org.jellyfin.androidtv.browsing;

import android.os.Bundle;

import org.jellyfin.androidtv.TvApp;

public class CustomViewFragment extends BrowseFolderFragment {
    protected String includeType;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        includeType = getActivity().getIntent().getStringExtra("IncludeType");
        TvApp.getApplication().getLogger().Debug("Item type: "+includeType);
        showViews = false;

        super.onActivityCreated(savedInstanceState);
    }
}
