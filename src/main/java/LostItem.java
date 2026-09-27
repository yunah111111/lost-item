public class LostItem {
    private int id;
    private String itemName;
    private String foundPlace;

    public LostItem(int id, String itemName, String foundPlace) {
        this.id = id;
        this.itemName = itemName;
        this.foundPlace = foundPlace;
    }

    public int getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public String getFoundPlace() {
        return foundPlace;
    }

    // 번호는 Dao가 저장할 때 정해 주므로 setter가 필요합니다.
    public void setId(int id) {
        this.id = id;
    }

}