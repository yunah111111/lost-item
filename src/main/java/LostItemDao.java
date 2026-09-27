import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LostItemDao {
    // 메모리 저장소와 다음 번호
    private List<LostItem> store = new ArrayList<>();
    private int nextId = 1;

    // 1. item에 번호(nextId)를 붙이고 store에 추가한 뒤 nextId를 1 늘린다
    public void insert(LostItem item) {
//        item.setId(nextId);
//        store.add(item);
//        ++nextId;
        String sql = """
                insert into lost_item
                (item_name, found_place) values(?, ?)
                """;

        try (Connection conn = DBConfig.getConnection()) {
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, item.getItemName());
                pstmt.setString(2, item.getFoundPlace());

//                int rs = pstmt.executeUpdate();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 2. store 전체를 반환한다
    public List<LostItem> findAll() {
        String sql = """
                SELECT id, item_name, found_place FROM lost_item ORDER BY id
                """;

        try (Connection conn = DBConfig.getConnection()) {
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    LostItem item = new LostItem(
                            rs.getInt("id"),
                            rs.getString("item_name"),
                            rs.getString("found_place")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return store;
    }

    // 3. store에서 id가 같은 항목을 찾아 반환한다. 없으면 null을 반환한다
    public LostItem findById(int id) {
//        for (LostItem item : store) {
//            if (item.getId() == id) {
//                return item;
//            }
//        }
        String sql = """
                SELECT id, item_name, found_place FROM lost_item WHERE id = ?
                """;

        try (Connection conn = DBConfig.getConnection()) {
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    LostItem item = new LostItem(
                            rs.getInt("id"),
                            rs.getString("item_name"),
                            rs.getString("found_place")
                    );
                    return item;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // 4. id가 같은 항목을 store에서 제거하고 true를 반환한다. 없으면 false를 반환한다
    public boolean delete(int id) {
//        for(LostItem item : store) {
//            if(item.getId() == id) {
//                store.remove(item);
//                return true;
//            }
//        }
        String sql = """
                DELETE FROM lost_item WHERE id = ?
                """;

        try (Connection conn = DBConfig.getConnection()) {
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);

                int rs = pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}