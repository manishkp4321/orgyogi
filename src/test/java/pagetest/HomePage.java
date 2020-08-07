package pagetest;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

@Slf4j
public class HomePage extends OrgYogiAbstractPage<HomePage> {

    private String configUrl="baseUrl";
    private By singIn= By.xpath("//div[@class='tbay-login']//span[.='Log in']");
    private By loginTitle=By.xpath("//h2/span");
    private By userName=By.xpath("//input[@id='username']");
    private By passWord=By.xpath("//input[@id='password']");
    private By submitBtn=By.xpath("//input[@type='submit']");
    private By loginSuccesUserName=By.xpath("//div[contains(@class,'MyAccount-content')]//strong");
    private By searchInputBox=By.xpath("/descendant::input[@name='s'][3]");
    private By productTitle=By.xpath("//h1[@class='product_title entry-title']");
    private By qtyInputBox=By.xpath("//input[@type='number']");
    private By addToCartBtn=By.xpath("//button[@name='add-to-cart']");
    private By buyNowBtn=By.xpath("//button[@class='tbay-buy-now button']");
    private By itemPrice=By.xpath("//p[@class='price']//span[@class='woocommerce-Price-amount amount']");
    private By itemSubTotal=By.xpath("//span[@class='product-subtotal price']//span[@class='woocommerce-Price-amount amount']");
    private By clickOnProceedtoCheckOut=By.xpath("//div[@class=\"wc-proceed-to-checkout\"]//a");
    private By checkOutPage=By.xpath("clickOnProceedtoCheckOut");
    private By placeOrder=By.xpath("//button[@id='place_order']");

    @Step("Login 'OBC' application")
    public HomePage login(){
        log.info( "Login into the application" );
        load( "ORGYOGI", configUrl );
        handle_browser_exceptions();
        click_on_login();
        verify_login_page();
        enter_user_name( getConfigParamValue( "ORGYOGI", "userName" ) );
        enter_user_password( getConfigParamValue( "ORGYOGI", "passWord" ) );
        click_submit();
        return me();
    }

    public HomePage click_on_login() {
        log.info("Click on Sign in Button");
        return click(singIn);
    }

    public HomePage verify_login_page() {
        log.info("Verify the login Title");
        verify_element_by_text(loginTitle,"login to system");
        return me();
    }

    public HomePage enter_user_name(String userName) {
        log.info("Entering the UserName");
        return enter(this.userName, userName);
    }
    public HomePage enter_user_password(String passWord) {
        log.info("Entering the Password");
        return enter(this.passWord, passWord);
    }

    public HomePage click_submit() {
        log.info("Click on Submit");
        return click(this.submitBtn);
    }

    public HomePage verify_successful_login() {
        log.info("Verifying the successful login");
        String userName= getConfigParamValue("ORGYOGI", "userName" );
        String user[]=userName.split("@");
        verify_element_by_text(loginSuccesUserName,user[0]);
        return me();
    }

    public HomePage enter_serach_item(String itemName) {
        log.info("Entering the Search item:->"+ itemName);
        enter(searchInputBox,itemName);
        wait_until(3);
        enter_by_key();
        return me();
    }

    public HomePage verify_product_search(String itemName) {
        log.info("Verifying the product search:->"+ itemName);
        return verify_element_by_text_partial_match(this.productTitle,itemName);
    }

    public HomePage enter_item_qty(String itemQty) {
        log.info("Entering the item Qty:->"+ itemQty);
        return enter(this.qtyInputBox, itemQty);
    }

    public HomePage click_add_to_cart() {
        log.info("Click on add to cart");
        return click(this.addToCartBtn);
    }

    public HomePage click_buy_now() {
        log.info("click on buy now btn");
        return click(this.buyNowBtn);
    }

    public HomePage verify_item_price() {
        log.info("Verify the item price");
        return verify_element_by_text_partial_match(itemPrice,getConfigParamValue("ORGYOGI","ItemPrice"));
    }

    public HomePage verify_item_subtotal(int itemQty) {
        log.info("Verify the item subtotal");
        int itemPrice=Integer.parseInt(getConfigParamValue("ORGYOGI","ItemPrice"));
        String subTotal=String.valueOf(itemQty*itemPrice);
        verify_element_by_text_partial_match(itemSubTotal,subTotal);
        return me();
    }
    public HomePage verify_cart_total(int itemQty) {
        log.info("Verify the Cart Total");
        int itemPrice=Integer.parseInt(getConfigParamValue("ORGYOGI","ItemPrice"));
        String subTotal=String.valueOf((itemQty*itemPrice)+getConfigParamValue("ORGYOGI","ShippingPrice"));
        verify_element_by_text_partial_match(itemSubTotal,subTotal);
        return me();
    }

    public HomePage click_on_proceed_to_check_out() {
        log.info("Click on Proceed to check out");
        return click(clickOnProceedtoCheckOut);
    }

    public HomePage verify_check_out_page() {
        log.info("Verify the check out page title");
        return verify_element_by_text(checkOutPage,"Checkout");
    }

    public HomePage click_on_place_order() {
        log.info("Click on the place an order");
        return click(placeOrder);
    }
}
