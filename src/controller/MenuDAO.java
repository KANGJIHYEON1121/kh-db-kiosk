package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.MenuVO;

public class MenuDAO {
    // SQL 문
    private String selectSQL = "SELECT * FROM MENU ORDER BY MENU_NO ASC";
    private static String selectByNoSQL = "SELECT * FROM MENU WHERE MENU_NO = ?";
    private String insertMenuSQL = "INSERT INTO MENU VALUES(MENU_SEQ.NEXTVAL, ?, ?)";
    private String updateMenuSQL = "UPDATE MENU SET MENU_NAME = ?, PRICE = ? WHERE MENU_NO = ?";
    private String deleteMenuSQL = "DELETE FROM MENU WHERE MENU_NO = ?";

    // 메뉴 목록
    public ArrayList<MenuVO> selectALL() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<MenuVO> menuList = new ArrayList<MenuVO>();

        try {
            // DB 연결 확인
            con = DBUtil.getConnection();
            if (con == null) {
                System.out.println("DB 문제 발생");
                return null;
            }

            pstmt = con.prepareStatement(selectSQL); // pstmt 변수에 selectSQL 쿼리문을 객체로 변환
            rs = pstmt.executeQuery(); // SQL 실행 메서드

            while (rs.next()) {
                int menuNo = rs.getInt("MENU_NO");
                String menuName = rs.getString("MENU_NAME");
                int price = rs.getInt("PRICE");

                MenuVO menuVO = new MenuVO(menuNo, menuName, price);
                menuList.add(menuVO);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            // DB 연결 해제
            DBUtil.dbClose(con, pstmt, rs);
        }
        return menuList;
    }

    // 메뉴 번호로 메뉴 1개 조회
    public MenuVO selectByMenuNo(int menuNo) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MenuVO menuVO = null;

        try {
            con = DBUtil.getConnection();
            if (con == null) {
                System.out.println("DB 문제 발생");
                return null;
            }

            pstmt = con.prepareStatement(selectByNoSQL);
            pstmt.setInt(1, menuNo);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                String menuName = rs.getString("MENU_NAME");
                int price = rs.getInt("PRICE");
                menuVO = new MenuVO(menuNo, menuName, price);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.dbClose(con, pstmt, rs);
        }
        return menuVO;
    }

    // 메뉴 추가
    public int insert(MenuVO menuVO) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int count = 0;

        try {
            // DB 연결 확인
            con = DBUtil.getConnection();
            if (con == null) {
                System.out.println("DB 문제 발생");
                return -1;
            }

            pstmt = con.prepareStatement(insertMenuSQL); // pstmt 변수에 insertMenuSQL 쿼리문을 객체로 변환
            pstmt.setString(1, menuVO.getMenuName());
            pstmt.setInt(2, menuVO.getPrice());

            count = pstmt.executeUpdate(); // SQL 실행 메서드

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            // DB 연결 해제
            DBUtil.dbClose(con, pstmt);
        }
        return count;
    }

    // 메뉴 수정
    public int update(MenuVO menuVO) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int count = 0;

        try {
            // DB 연결 확인
            con = DBUtil.getConnection();
            if (con == null) {
                System.out.println("DB 문제 발생");
                return -1;
            }

            pstmt = con.prepareStatement(updateMenuSQL); // pstmt 변수에 updateMenuSQL 쿼리문을 객체로 변환
            pstmt.setString(1, menuVO.getMenuName());
            pstmt.setInt(2, menuVO.getPrice());
            pstmt.setInt(3, menuVO.getMenuNo());

            count = pstmt.executeUpdate(); // SQL 실행 메서드

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            // DB 연결 해제
            DBUtil.dbClose(con, pstmt);
        }
        return count;
    }

    // 메뉴 삭제
    public int delete(MenuVO menuVO) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int count = 0;

        try {
            // DB 연결 확인
            con = DBUtil.getConnection();
            if (con == null) {
                System.out.println("DB 문제 발생");
                return -1;
            }

            pstmt = con.prepareStatement(deleteMenuSQL); // pstmt 변수에 updateMenuSQL 쿼리문을 객체로 변환
            pstmt.setInt(1, menuVO.getMenuNo());

            count = pstmt.executeUpdate(); // SQL 실행 메서드

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            // DB 연결 해제
            DBUtil.dbClose(con, pstmt);
        }
        return count;
    }
}
