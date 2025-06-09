import java.sql.Connection;
import java.util.Scanner;

import controller.CartManager;
import controller.DBUtil;
import controller.MemberManager;
import controller.MenuManager;
import model.MemberVO;
import view.LoginChoice;
import view.MainMenuChoice;
import view.MasterMenu;
import view.MenuView;

public class JavaKioskMain {
    // 공용 스캐너
    public static Scanner scan = new Scanner(System.in);
    public static String loginUserId = null;
    public static boolean isAdmin = false;

    public static void main(String[] args) {
        // DB 연결 확인
        Connection con = DBUtil.getConnection();
        if (con == null) {
            System.out.println("DB 연결 오류");
            return;
        }

        int choice = 0; // 로그인 선택 번호
        boolean exitFlag = false; // while문 flag 값

        while (!exitFlag) {
            try {
                MenuView.login();
                MemberManager mm = new MemberManager();
                choice = Integer.parseInt(scan.nextLine());

                // UI 설계
                switch (choice) {
                    case LoginChoice.로그인:
                        MemberVO user = mm.memberLogin();
                        if (user == null) {
                            break; // 로그인 실패
                        }
                        loginUserId = user.getMemberId();
                        isAdmin = user.getIsAdmin() == 'Y';
                        if (isAdmin) {
                            System.out.println("관리자 로그인 성공");
                            System.out.printf("%s님 환영합니다.\n", user.getName());
                        } else {
                            System.out.printf("%s님 환영합니다.\n", user.getName());
                        }
                        mainMenu();
                        break;
                    case LoginChoice.회원가입:
                        mm.memberJoin();
                        break;
                    case LoginChoice.종료:
                        exitFlag = true;
                        break;
                }
            } catch (Exception e) {
                System.out.println("\n입력에 오류가 있습니다.\n프로그램을 다시 시작하세요.");
                exitFlag = true;
            }
        } // end of while

        System.out.println("프로그램 종료");
    } // end of main

    public static void mainMenu() {
        int choice = 0; // 메뉴 선택 번호
        boolean exitFlag = false; // while문 flag 값

        while (!exitFlag) {
            try {
                MenuView.mainMenu();
                CartManager cm = new CartManager();
                choice = Integer.parseInt(scan.nextLine());

                // UI 설계
                switch (choice) {
                    case MainMenuChoice.메뉴선택:
                        cm.insertMenu(loginUserId);
                        break;
                    case MainMenuChoice.장바구니확인:
                        cm.checkCart(loginUserId);
                        break;
                    case MainMenuChoice.결재하기:
                        cm.payCart(loginUserId);
                        break;
                    case MainMenuChoice.장바구니초기화:
                        cm.clearCart(loginUserId);
                        break;
                    case MainMenuChoice.관리자메뉴:
                        if (isAdmin) {
                            masterMenu();
                            break;
                        } else {
                            System.out.println("일반 유저는 권한이 없습니다.");
                        }
                        break;
                    case MainMenuChoice.프로그램종료:
                        exitFlag = true;
                        break;
                }
            } catch (Exception e) {
                System.out.println("\n입력에 오류가 있습니다.\n프로그램을 다시 시작하세요.");
                exitFlag = true;
            }
        } // end of while
    }

    public static void masterMenu() {
        int choice = 0; // 관리자 메뉴 선택 번호
        boolean exitFlag = false; // while문 flag 값

        while (!exitFlag) {
            try {
                MenuView.masterMenu();
                MenuManager mm = new MenuManager();
                MemberManager memm = new MemberManager();
                CartManager cm = new CartManager();
                choice = Integer.parseInt(scan.nextLine());

                // UI 설계
                switch (choice) {
                    case MasterMenu.회원정보확인:
                        memm.memberList();
                        break;
                    case MasterMenu.메뉴정보확인:
                        mm.menuList();
                        break;
                    case MasterMenu.메뉴추가:
                        mm.insertMenu();
                        break;
                    case MasterMenu.메뉴수정:
                        mm.updateMenu();
                        break;
                    case MasterMenu.메뉴삭제:
                        mm.deleteMenu();
                        break;
                    case MasterMenu.매출확인:
                        cm.showSales();
                        break;
                    case MasterMenu.사용자모드:
                        exitFlag = true;
                        break;
                }
            } catch (Exception e) {
                System.out.println("관리자 메뉴 오류 발생");
                exitFlag = true;
            }
        } // end of while
    }

}
