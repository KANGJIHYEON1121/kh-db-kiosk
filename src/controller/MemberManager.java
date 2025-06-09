package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.MemberVO;

public class MemberManager {
    public static Scanner scan = new Scanner(System.in);

    // 회원 목록
    public void memberList() throws Exception {
        MemberDAO md = new MemberDAO();
        System.out.println("회원 전체 리스트");
        ArrayList<MemberVO> memberList = md.selectALL();

        if (memberList == null) {
            System.out.println("회원리스트 오류 발생");
            return;
        } else if (memberList.size() == 0) {
            System.out.println("회원 리스트 내용이 없습니다.");
            return;
        }

        for (MemberVO data : memberList) {
            System.out.println(data.toString());
        }
    }

    // 로그인 기능
    public MemberVO memberLogin() throws Exception {
        MemberDAO md = new MemberDAO();

        System.out.println("=== 로그인 ===");
        System.out.print("ID : ");
        String id = scan.nextLine();
        System.out.print("PW : ");
        String pw = scan.nextLine();

        MemberVO loginUser = md.login(id, pw);
        if (loginUser == null) {
            System.out.println("로그인 실패");
            return null;
        }

        return loginUser;
    }

    // 회원가입 기능
    public void memberJoin() throws Exception {
        MemberDAO md = new MemberDAO();
        MemberVO mvo = new MemberVO();

        System.out.println("=== 회원가입 ===");
        System.out.print("ID 입력 : ");
        mvo.setMemberId(scan.nextLine());

        // id 중복확인
        if (md.checkId(mvo.getMemberId())) {
            System.out.println("이미 존재하는 ID 입니다. 다른 ID를 입력해주세요.");
            return;
        }

        System.out.print("PW 입력: ");
        mvo.setMemberPw(scan.nextLine());
        System.out.print("이름 입력: ");
        mvo.setName(scan.nextLine());
        System.out.print("전화번호 입력: ");
        mvo.setPhone(scan.nextLine());
        mvo.setIsAdmin('N');

        int result = md.memberJoin(mvo);
        if (result > 0) {
            System.out.println("회원가입 성공");
        } else {
            System.out.println("회원가입 실패");
        }
    }
}
