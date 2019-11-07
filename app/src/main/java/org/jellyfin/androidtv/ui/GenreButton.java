package org.jellyfin.androidtv.ui;

import android.content.Context;
import android.graphics.Typeface;
import androidx.appcompat.widget.AppCompatTextView;

public class GenreButton extends AppCompatTextView {
    public GenreButton(Context context, Typeface font, int size, String text, String itemType) {
        super(context);
        setTypeface(font);
        setTextSize(size);
        setText(text);
    }
}
