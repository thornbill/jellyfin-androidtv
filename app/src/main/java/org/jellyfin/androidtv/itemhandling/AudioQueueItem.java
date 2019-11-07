package org.jellyfin.androidtv.itemhandling;

import org.jellyfin.apiclient.model.dto.BaseItemDto;

public class AudioQueueItem extends BaseRowItem {
    public AudioQueueItem(int index, BaseItemDto item) {
        super(index, item);
        this.staticHeight = true;
    }
}
