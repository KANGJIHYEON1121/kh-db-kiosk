package model;

public class MemberVO {
    private String memberId;
    private String memberPw;
    private String name;
    private String phone;
    private char isAdmin;

    public MemberVO() {
        super();
    }

    public MemberVO(String memberId, String memberPw, String name, String phone, char isAdmin) {
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.name = name;
        this.phone = phone;
        this.isAdmin = isAdmin;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberPw() {
        return memberPw;
    }

    public void setMemberPw(String memberPw) {
        this.memberPw = memberPw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public char getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(char isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "MemberVO [memberId=" + memberId + ", memberPw=" + memberPw + ", name=" + name + ", phone=" + phone
                + ", isAdmin=" + isAdmin + "]";
    }

}
