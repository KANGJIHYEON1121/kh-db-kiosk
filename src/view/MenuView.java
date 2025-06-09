package view;

public class MenuView {
    public static void login() {
        // 로그인, 회원가입
        System.out.println("=========================================");
        System.out.println("                COFFEE CAFE");
        System.out.println("=========================================");
        System.out.println("          1.      로그인");
        System.out.println("          2.     회원가입");
        System.out.println("          3.       종료");
        System.out.println("=========================================");
        System.out.println();
        System.out.print("번호 선택 >> ");
    }

    public static void mainMenu() {
        // 메인 메뉴
        System.out.println("=========================================");
        System.out.println("                메인 메뉴");
        System.out.println("=========================================");
        System.out.println("          1.      메뉴 선택");
        System.out.println("          2.   장바구니 확인");
        System.out.println("          3.  장바구니 초기화");
        System.out.println("          4.      결제하기");
        System.out.println("          5.    관리자 메뉴");
        System.out.println("          6.   프로그램 종료");
        System.out.println("=========================================");
        System.out.println();
        System.out.print("번호 선택 >> ");
    }

    // 관리자 모드 메뉴
    public static void masterMenu() {

        System.out.println("=========================================");
        System.out.println("                메인 메뉴");
        System.out.println("=========================================");
        System.out.println("          1.    회원정보 확인");
        System.out.println("          2.    메뉴정보 확인");
        System.out.println("          3.     메뉴 추가");
        System.out.println("          4.     메뉴 수정");
        System.out.println("          5.     메뉴 삭제");
        System.out.println("          6.     매출 확인");
        System.out.println("          7.     사용자 모드");
        System.out.println();
        System.out.print("번호 선택 >> ");
    }
}
