import java.util.List;

public class LostItemService {
    // 직접 new 하지 않고 선언만 한다
    private LostItemDao dao;

    // 생성자로 Dao를 받아 필드에 저장 (생성자 주입)
    public LostItemService(LostItemDao dao) {
        this.dao = dao;
    }

    // 1. LostItem 객체를 만들어 dao.insert에 넘기고 "{물건명} 등록 완료" 출력
    //    번호는 아직 모르므로 0을 넣어 만든다. 번호는 Dao가 붙여 준다.
    public void register(String itemName, String foundPlace) {
        LostItem lostItem = new LostItem(0, itemName, foundPlace);
        dao.insert(lostItem);
        System.out.println("{" + itemName + "} 등록 완료");
    }

    // 2. dao.findAll로 목록을 받아 "--- 보관 중인 습득물 ---" 아래에 한 줄씩 출력
    //    목록이 비어 있으면 "보관 중인 습득물이 없습니다." 출력
    public void printAll() {
        List<LostItem> lostItems = dao.findAll();
        System.out.println("--- 보관 중인 습득물 ---");
        for (LostItem lostItem : lostItems) {
            System.out.println("[" + lostItem.getId() +"] " + lostItem.getItemName() + " | " + lostItem.getFoundPlace());
        }
    }

    // 3. dao.findById로 하나를 찾아 출력. null이면 "{번호}번 습득물이 없습니다." 출력
    public void printOne(int id) {
        if (dao.findById(id) == null) {
            System.out.println("{" + id + "}번 습득물이 없습니다.");
        } else {
            System.out.println("[" + dao.findById(id).getId() + "] " + dao.findById(id).getItemName() + " | " + dao.findById(id).getFoundPlace());
        }
    }

    // 4. dao.delete 결과가 true면 "{번호}번 습득물을 주인에게 돌려주었습니다."
    //    false면 "{번호}번 습득물이 없습니다." 출력
    public void remove(int id) {
        if (dao.delete(id)) {
            System.out.println("{" + id + "}번 습득물을 주인에게 돌려주었습니다.");
        } else {
            System.out.println("{" + id + "}번 습득물이 없습니다.");
        }
    }
}