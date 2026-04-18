package models.records;

public class ItemCrate {
    private Integer itemId;
    private Integer collectionId;

    public ItemCrate() {}

    public ItemCrate(Integer itemId, Integer collectionId) {
        this.itemId = itemId;
        this.collectionId = collectionId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }
}
