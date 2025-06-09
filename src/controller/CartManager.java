package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.CartVO;
import model.MenuVO;

public class CartManager {
    public static Scanner scan = new Scanner(System.in);

    // 장바구니 확인
    public void checkCart(String memberId) throws Exception {
        CartDAO cd = new CartDAO();
        ArrayList<CartVO> cartList = cd.checkCart(memberId);

        if (cartList.isEmpty()) {
            System.out.println("장바구니가 비어 있습니다.");
            return;
        }

        System.out.println("=========================================");
        System.out.println("                장바구니");
        System.out.println("=========================================");
        for (CartVO data : cartList) {
            System.out.printf("메뉴: %s | 수량: %d | 가격: %d원 | 총액: %d원\n", data.getMenuName(), data.getQuantity(),
                    data.getPrice(), data.getTotalPrice());
        }
        int totalAmount = 0;
        for (CartVO data : cartList) {
            totalAmount += data.getTotalPrice();
        }
        System.out.printf("총 금액 : %d원 \n", totalAmount);
    }

    // 매출 확인
    public void showSales() throws Exception {
        CartDAO cd = new CartDAO();
        ArrayList<CartVO> saleList = cd.showSales();

        if (saleList == null || saleList.isEmpty()) {
            System.out.println("매출 내역이 없습니다.");
            return;
        }

        System.out.println("=========================================");
        System.out.println("                 매출 내역");
        System.out.println("=========================================");

        int totalValue = 0;
        for (CartVO data : saleList) {
            System.out.printf("회원: %s | 메뉴: %s | 수량: %d | 가격: %d원 | 총액: %d원\n",
                    data.getMemberId(), data.getMenuName(), data.getQuantity(),
                    data.getPrice(), data.getTotalPrice());
            totalValue += data.getTotalPrice();
        }

        System.out.println("-----------------------------------------");
        System.out.printf("총 매출 합계: %,d원\n", totalValue);
        System.out.println("=========================================");
    }

    // 장바구니 메뉴 추가
    public void insertMenu(String id) throws Exception {
        MenuDAO md = new MenuDAO();
        CartDAO cd = new CartDAO();
        MenuManager mm = new MenuManager();

        System.out.println("메뉴 번호 선택");
        mm.menuList();
        System.out.print("메뉴 번호 : ");
        int menuId = Integer.parseInt(scan.nextLine());

        MenuVO selectMenu = md.selectByMenuNo(menuId);
        if (selectMenu == null) {
            System.out.println("잘못된 메뉴 번호 입니다.");
            return;
        }

        System.out.print("수량입력 : ");
        int quantity = Integer.parseInt(scan.nextLine());

        CartVO cvo = new CartVO();
        cvo.setMemberId(id);
        cvo.setMenuId(menuId);
        cvo.setQuantity(quantity);
        cvo.setPrice(selectMenu.getPrice());

        int count = cd.insert(cvo);
        if (count > 0) {
            System.out.println("장바구니 추가완료");
        } else {
            System.out.println("장바구니 담기 실패");
        }
    }

    // 결재 기능
    public void payCart(String memberId) throws Exception {
        CartDAO cd = new CartDAO();
        ArrayList<CartVO> cartList = cd.checkCart(memberId);

        if (cartList.isEmpty()) {
            System.out.println("장바구니가 비어있어서 결재 할 수 없습니다.");
            return;
        }

        int totalAmount = 0;
        for (CartVO data : cartList) {
            totalAmount += data.getTotalPrice();
        }

        System.out.println("=========================================");
        System.out.printf("총 결제 금액: %,d원\n", totalAmount);

        // 장바구니 비우기
        int deleteCart = cd.payCart(memberId);
        if (deleteCart > 0) {
            System.out.println("결제가 완료되었습니다.");
        } else {
            System.out.println("결재 실패");
        }
        System.out.println("=========================================");
    }

    // 장바구니 비우기(초기화)
    public void clearCart(String memberId) throws Exception {
        CartDAO cd = new CartDAO();
        ArrayList<CartVO> cartList = cd.checkCart(memberId);

        if (cartList.isEmpty()) {
            System.out.println("장바구니가 비어있습니다.");
            return;
        }

        System.out.println("=========================================");
        // 장바구니 비우기
        int deleteCart = cd.payCart(memberId);
        if (deleteCart > 0) {
            System.out.println("장바구니 초기화 완료");
        } else {
            System.out.println("장바구니 초기화 실패");
        }
        System.out.println("=========================================");
    }

}