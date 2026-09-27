import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 조립: Dao를 먼저 만들고 Service에 넣어 준다
        LostItemDao dao = new LostItemDao();
        LostItemService service = new LostItemService(dao);

        while (true) {
            System.out.println();
            System.out.println("=== 분실물 센터 ===");
            System.out.println("1. 습득물 등록");
            System.out.println("2. 전체 목록");
            System.out.println("3. 번호로 찾기");
            System.out.println("4. 주인 찾아감 처리");
            System.out.println("0. 종료");
            System.out.print("선택: ");
            int menu = Integer.parseInt(scanner.nextLine());

            if (menu == 1) {
                System.out.print("물건명: ");
                String itemName = scanner.nextLine();
                System.out.print("발견 장소: ");
                String foundPlace = scanner.nextLine();
                service.register(itemName, foundPlace);
            } else if (menu == 2) {
                service.printAll();
            } else if (menu == 3) {
                System.out.print("번호: ");
                int id = Integer.parseInt(scanner.nextLine());
                service.printOne(id);
            } else if (menu == 4) {
                System.out.print("번호: ");
                int id = Integer.parseInt(scanner.nextLine());
                service.remove(id);
            } else if (menu == 0) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else {
                System.out.println("메뉴에 없는 번호입니다.");
            }
        }
        scanner.close();
    }
}