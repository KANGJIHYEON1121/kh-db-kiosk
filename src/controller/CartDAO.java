package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.CartVO;

public class CartDAO {
    // SQL 문
    private String checkCartSQL = "SELECT C.CART_ITEM_ID, C.MEMBER_ID, C.MENU_ID, M.MENU_NAME, C.QUANTITY, C.PRICE, (C.QUANTITY * C.PRICE) AS TOTAL_PRICE FROM CART C JOIN MENU M ON C.MENU_ID = M.MENU_NO WHERE C.MEMBER_ID = ?";
    private String insertMenuSQL = "INSERT INTO CART (CART_ITEM_ID, MEMBER_ID, MENU_ID, QUANTITY, PRICE) VALUES (CART_SEQ.NEXTVAL, ?, ?, ?, ?)";
    private String deleteCartSQL = "DELETE FROM CART WHERE MEMBER_ID = ?";
    private String payCartSQL = "INSERT INTO SALES VALUES(SALES_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, SYSDATE)";
    private String showSalesSQL = "SELECT * FROM SALES ORDER BY SALE_DATE DESC";

    // 장바구니 입력
    public int insert(CartVO cartVO) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            con = DBUtil.getConnection();
            if (con == null) {
                System.out.println("DB 문제 발생");
                return -1;
            }
            pstmt = con.prepareStatement(insertMenuSQL);
            pstmt.setString(1, cartVO.getMemberId());
            pstmt.setInt(2, cartVO.getMenuId());
            pstmt.setInt(3, cartVO.getQuantity());
            pstmt.setInt(4, cartVO.getPrice());

            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("createStatement 오류발생");
        } finally {
            DBUtil.dbClose(con, pstmt);
        }
        return count;
    }

    // 장바구니 확인
    public ArrayList<CartVO> checkCart(String memberId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<CartVO> cartList = new ArrayList<>();
        try {
            con = DBUtil.getConnection();
            if (con == null) {
                System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
                return null;
            }
            pstmt = con.prepareStatement(checkCartSQL);
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int cartItemId = rs.getInt("CART_ITEM_ID");
                String member = rs.getString("MEMBER_ID");
                int menuId = rs.getInt("MENU_ID");
                int quantity = rs.getInt("QUANTITY");
                int price = rs.getInt("PRICE");
                String menuName = rs.getString("MENU_NAME");
                int totalPrice = rs.getInt("TOTAL_PRICE");

                CartVO cartVO = new CartVO(cartItemId, member, menuId, quantity, price, menuName, totalPrice);
                cartList.add(cartVO);
            }
        } catch (SQLException e) {
            System.out.println("장바구니 조회 오류발생");
        } finally {
            DBUtil.dbClose(con, pstmt, rs);
        }
        return cartList;
    }

    // 장바구니 결재
    public int payCart(String memberId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ArrayList<CartVO> cartList = checkCart(memberId);
        int count = 0;

        if (cartList == null || cartList.isEmpty()) {
            System.out.println("장바구니에 항목이 없습니다.");
            return 0;
        }

        try {
            con = DBUtil.getConnection();
            if (con == null) {
                System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
                return -1;
            }

            pstmt = con.prepareStatement(payCartSQL);
            for (CartVO data : cartList) {
                pstmt.setString(1, data.getMemberId());
                pstmt.setInt(2, data.getMenuId());
                pstmt.setString(3, data.getMenuName());
                pstmt.setInt(4, data.getQuantity());
                pstmt.setInt(5, data.getPrice());
                pstmt.setInt(6, data.getTotalPrice());
                count += pstmt.executeUpdate();
            }

            pstmt = con.prepareStatement(deleteCartSQL);
            pstmt.setString(1, memberId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("장바구니 조회 오류발생");
        } finally {
            DBUtil.dbClose(con, pstmt);
        }

        return count;
    }

    // 매출 확인
    public ArrayList<CartVO> showSales() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<CartVO> cartList = new ArrayList<>();

        try {
            con = DBUtil.getConnection();
            if (con == null) {
                System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
                return null;
            }

            pstmt = con.prepareStatement(showSalesSQL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int cartItemId = rs.getInt("SALE_ID");
                String memberId = rs.getNString("MEMBER_ID");
                int menuId = rs.getInt("MENU_ID");
                String menuName = rs.getString("MENU_NAME");
                int quantity = rs.getInt("QUANTITY");
                int price = rs.getInt("PRICE");
                int totalPrice = rs.getInt("TOTAL_PRICE");

                CartVO cvo = new CartVO(cartItemId, memberId, menuId, quantity, price, menuName, totalPrice);
                cartList.add(cvo);
            }
        } catch (SQLException e) {
            System.out.println("장바구니 조회 오류발생");
        } finally {
            DBUtil.dbClose(con, pstmt);
        }

        return cartList;
    }

    // 장바구니 비우기(초기화)
    public int clearCart(String memberId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int count = 0;

        try {
            con = DBUtil.getConnection();
            if (con == null) {
                System.out.println("DB 연결이 문제발생했습니다. 빨리조치를 진행하겠습니다.");
                return -1;
            }
            pstmt = con.prepareStatement(deleteCartSQL);
            pstmt.setString(1, memberId);
            count = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("장바구니 조회 오류발생");
        } finally {
            DBUtil.dbClose(con, pstmt);
        }

        return count;
    }
}
