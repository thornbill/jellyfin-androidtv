package org.jellyfin.androidtv.presentation;

import android.graphics.Bitmap;
import androidx.leanback.app.BackgroundManager;

import com.squareup.picasso.Picasso;

import org.jellyfin.androidtv.TvApp;
import org.jellyfin.androidtv.imagehandling.PicassoBackgroundManagerTarget;

public class MyPicassoBackgroundManagerTarget extends PicassoBackgroundManagerTarget {

    public MyPicassoBackgroundManagerTarget(BackgroundManager backgroundManager) {
        super(backgroundManager);
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
        super.onBitmapLoaded(bitmap, loadedFrom);
        TvApp.getApplication().setCurrentBackground(bitmap);
    }
}
