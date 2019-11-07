package org.jellyfin.androidtv.base;

import android.view.KeyEvent;

public interface IKeyListener {
    boolean onKeyUp(int key, KeyEvent event);
}
