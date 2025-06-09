package controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import model.MenuVO;

public class MenuManager {
    public static Scanner scan = new Scanner(System.in);

    // 메뉴 목록 출력
    public void menuList() throws Exception {
        MenuDAO md = new MenuDAO();
        System.out.println("메뉴 전체 리스트");
        ArrayList<MenuVO> menuList = md.selectALL();

        if (menuList.size() == 0) {
            System.out.println("메뉴 리스트 내용이 없습니다.");
            return;
        } else if (menuList == null) {
            System.out.println("메뉴 리스트 오류 발생");
            return;
        }

        for (MenuVO data : menuList) {
            System.out.println(data.toString());
        }
    }

    // 메뉴 추가
    public void insertMenu() throws Exception {
        MenuDAO md = new MenuDAO();
        MenuVO mvo = new MenuVO();

        String menuName; // 메뉴 이름
        int price; // 메뉴 가격

        System.out.println("메뉴 추가 입력");
        System.out.print("메뉴이름 : ");
        menuName = scan.nextLine();
        boolean menuNameCheck = Pattern.matches("^[가-힣]*$", menuName);
        if (!menuNameCheck) {
            System.out.println("메뉴 이름을 한글로 작성해주세요");
            return;
        }
        System.out.print("메뉴가격 : ");
        try {
            price = Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력해주세요.");
            return;
        }

        mvo.setMenuName(menuName);
        mvo.setPrice(price);

        md.insert(mvo);

        System.out.println();
        menuList();
        System.out.println();
    }

    public void selectMenu() throws Exception {
        MenuDAO md = new MenuDAO();
        MenuVO mvo = new MenuVO();

        int no; // 메뉴 번호

        System.out.println("메뉴 선택");
        menuList();
        System.out.print("메뉴번호 : ");
        try {
            no = Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력해주세요.");
            return;
        }

        mvo.setMenuNo(no);

        md.insert(mvo);

        System.out.println();
        menuList();
        System.out.println();
    }

    // 메뉴 수정
    public void updateMenu() throws Exception {
        MenuDAO md = new MenuDAO();
        MenuVO mvo = new MenuVO();

        int menuNo; // 메뉴 번호
        String menuName; // 메뉴 이름
        int price; // 메뉴 가격

        System.out.println("메뉴 수정 입력");
        menuList(); // 메뉴 리스트 보여주기
        System.out.print("수정 메뉴번호 : ");
        try {
            menuNo = Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("번호만 입력 해주세요");
            return;
        }

        System.out.println();
        System.out.println("새로운 메뉴 정보 입력");
        System.out.print("수정 메뉴이름 : ");
        menuName = scan.nextLine();
        boolean menuNameCheck = Pattern.matches("^[가-힣]*$", menuName);
        if (!menuNameCheck) {
            System.out.println("메뉴 이름을 한글로 작성해주세요");
            return;
        }
        System.out.print("수정 메뉴가격 : ");
        try {
            price = Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력해주세요.");
            return;
        }

        mvo.setMenuNo(menuNo);
        mvo.setMenuName(menuName);
        mvo.setPrice(price);

        int count = md.update(mvo);
        if (count == 0) {
            System.out.println("메뉴 수정 오류 발생");
            return;
        } else {
            System.out.println("메뉴 수정 완료");
        }

        System.out.println();
        menuList();
        System.out.println();
    }

    // 메뉴 삭제
    public void deleteMenu() throws Exception {
        MenuDAO md = new MenuDAO();
        MenuVO mvo = new MenuVO();

        int menuNo; // 메뉴 번호

        System.out.println("메뉴 삭제 입력");
        menuList(); // 메뉴 리스트 보여주기
        System.out.print("삭제 메뉴번호 : ");
        try {
            menuNo = Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("번호만 입력 해주세요");
            return;
        }

        mvo.setMenuNo(menuNo);

        int count = md.delete(mvo);
        if (count == 0) {
            System.out.println("메뉴 삭제 오류 발생");
            return;
        } else {
            System.out.println("메뉴 삭제 완료");
        }

        System.out.println();
        menuList();
        System.out.println();
    }

}
