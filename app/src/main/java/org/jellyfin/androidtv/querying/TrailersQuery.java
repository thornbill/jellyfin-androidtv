package org.jellyfin.androidtv.querying;

public class TrailersQuery {
    private String ItemId;

    public TrailersQuery(String itemId) {
        ItemId = itemId;
    }

    public String getItemId() {
        return ItemId;
    }

}
