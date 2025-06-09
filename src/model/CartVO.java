package model;

public class CartVO {
    private int cartItemId; // 장바구니 ID
    private String memberId; // 회원 ID
    private int menuId; // 메뉴 ID
    private int quantity; // 수량
    private int price; // 가격
    private String menuName; // 메뉴 이름
    private int totalPrice; // 총 가격

    public CartVO() {
        super();
    }

    public CartVO(int cartItemId, String memberId, int menuId, int quantity, int price, String menuName,
            int totalPrice) {
        this.cartItemId = cartItemId;
        this.memberId = memberId;
        this.menuId = menuId;
        this.quantity = quantity;
        this.price = price;
        this.menuName = menuName;
        this.totalPrice = totalPrice;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CartVO [cartItemId=" + cartItemId + ", memberId=" + memberId + ", menuId=" + menuId + ", quantity="
                + quantity + ", price=" + price + "]";
    }

}
