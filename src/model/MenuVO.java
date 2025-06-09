package model;

public class MenuVO {
    private int menuNo; // 메뉴 번호
    private String menuName; // 메뉴 이름
    private int price; // 메뉴 가격

    public MenuVO() {
        super();
    }

    public MenuVO(int menuNo, String menuName, int price) {
        this.menuNo = menuNo;
        this.menuName = menuName;
        this.price = price;
    }

    public int getMenuNo() {
        return menuNo;
    }

    public void setMenuNo(int menuNo) {
        this.menuNo = menuNo;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MenuVO [menuNo=" + menuNo + ", menuName=" + menuName + ", price=" + price + "]";
    }

}
