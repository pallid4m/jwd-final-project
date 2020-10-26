package by.estore.controller.command;

public final class RouteHolder {

    private RouteHolder() {}

    public static final String MAIN_PAGE = "/main?command=main-page";
    public static final String ADMIN_PAGE = "/main?command=admin-page";
    public static final String USER_PAGE = "/main?command=user-page";
    public static final String CATAlOG_PAGE = "/main?command=catalog-page";
    public static final String PRODUCT_PAGE = "/main?command=product-page";
    public static final String CART_PAGE = "/main?command=cart-page";
    public static final String SIGN_IN_PAGE = "/main?command=sign-in-page";
    public static final String SIGN_UP_PAGE = "/main?command=sign-up-page";

//    public static final String USER_LIST = "/WEB-INF/jsp/admin/userList.jsp";
//    public static final String PRODUCT_LIST = "/WEB-INF/jsp/admin/productList.jsp";
//    public static final String ORDER_LIST = "/WEB-INF/jsp/admin/orderList.jsp";
    public static final String USER_LIST = "admin/userList.jsp";
    public static final String PRODUCT_LIST = "admin/productList.jsp";
    public static final String ORDER_LIST = "admin/orderList.jsp";
}
