package org.jellyfin.androidtv.querying;

public class SpecialsQuery {
    private String ItemId;

    public SpecialsQuery(String itemId) {
        ItemId = itemId;
    }

    public String getItemId() {
        return ItemId;
    }

}
