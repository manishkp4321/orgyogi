package testcase;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import pagetest.HomePage;
import ui.AbstractAutoUITest;
@Slf4j
public class BuyingProductVerificationTest  extends AbstractAutoUITest {

@Test
public void loginTest() {
log.info("Login into the OrgYogi Web Application");
    getPage(HomePage.class)
            .login()
            .verify_successful_login()
            .click_home_link();
}

@Test
    public void searchAndProductItem() {
    log.info("Search the item and Buying the product");
    getPage(HomePage.class)
            .enter_serach_item("Guava (1 Kg)")
            .verify_product_search("Guava")
            .verify_item_price()
            .enter_item_qty("3")
          //  .click_add_to_cart()
            .click_buy_now();
}
@Test
    public void verifySubTotal() {
    log.info("Verifying the Sub Total and Cart Total Amount");
    getPage(HomePage.class)
            .verify_item_subtotal(3)
            .verify_cart_total(3);
}

@Test
    public void placeOrder() {
    log.info("Placing an Order Finally");
    getPage(HomePage.class)
            .click_on_proceed_to_check_out()
            .verify_check_out_page()
            .click_on_place_order();
}


}
