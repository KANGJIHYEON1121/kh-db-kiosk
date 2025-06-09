package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.MemberVO;

public class MemberDAO {
    // SQL 문
    private String selectSQL = "SELECT * FROM MEMBER ORDER BY MEMBER_ID ASC";
    private String insertMemberSQL = "INSERT INTO MEMBER (MEMBER_ID, MEMBER_PW, NAME, PHONE, IS_ADMIN) VALUES (?, ?, ?, ?, 'N')";
    private String checkIdSQL = "SELECT MEMBER_ID FROM MEMBER WHERE MEMBER_ID = ?";
    private String loginSQL = "SELECT * FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_PW = ?";

    // 회원 목록
    public ArrayList<MemberVO> selectALL() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<MemberVO> memberList = new ArrayList<MemberVO>();

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
                String memberId = rs.getString("MEMBER_ID");
                String memberPw = rs.getString("MEMBER_PW");
                String name = rs.getString("NAME");
                String phone = rs.getString("PHONE");
                char isAdmin = rs.getString("IS_ADMIN").charAt(0);

                MemberVO memberVO = new MemberVO(memberId, memberPw, name, phone, isAdmin);
                memberList.add(memberVO);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            // DB 연결 해제
            DBUtil.dbClose(con, pstmt, rs);
        }
        return memberList;
    }

    // 로그인
    public MemberVO login(String id, String pw) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MemberVO mvo = null;

        try {
            // DB 연결 확인
            con = DBUtil.getConnection();
            if (con == null) {
                System.out.println("DB 문제 발생");
                return null;
            }

            pstmt = con.prepareStatement(loginSQL); // pstmt 변수에 loginSQL 쿼리문을 객체로 변환
            pstmt.setString(1, id);
            pstmt.setString(2, pw);

            rs = pstmt.executeQuery(); // SQL 실행 메서드

            if (rs.next()) {
                mvo = new MemberVO();
                mvo.setMemberId(rs.getString("MEMBER_ID"));
                mvo.setMemberPw(rs.getString("MEMBER_PW"));
                mvo.setName(rs.getString("NAME"));
                mvo.setPhone(rs.getString("PHONE"));
                mvo.setIsAdmin(rs.getString("IS_ADMIN").charAt(0));
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            // DB 연결 해제
            DBUtil.dbClose(con, pstmt, rs);
        }
        return mvo;
    }

    // 회원가입
    public int memberJoin(MemberVO memberVO) {
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

            pstmt = con.prepareStatement(insertMemberSQL); // pstmt 변수에 insertMemberSQL 쿼리문을 객체로 변환
            pstmt.setString(1, memberVO.getMemberId());
            pstmt.setString(2, memberVO.getMemberPw());
            pstmt.setString(3, memberVO.getName());
            pstmt.setString(4, memberVO.getPhone());

            count = pstmt.executeUpdate(); // SQL 실행 메서드

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            // DB 연결 해제
            DBUtil.dbClose(con, pstmt);
        }
        return count;
    }

    // ID 중복 확인
    public boolean checkId(String id) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean exists = false;

        try {
            // DB 연결 확인
            con = DBUtil.getConnection();
            if (con == null) {
                System.out.println("DB 문제 발생");
                return false;
            }

            pstmt = con.prepareStatement(checkIdSQL); // pstmt 변수에 checkIdSQL 쿼리문을 객체로 변환
            pstmt.setString(1, id);

            rs = pstmt.executeQuery(); // SQL 실행 메서드
            if (rs.next()) {
                exists = true;
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            // DB 연결 해제
            DBUtil.dbClose(con, pstmt, rs);
        }
        return exists;
    }
}
