package pagetest;

import org.openqa.selenium.By;
import ui.AbstractPage;

public class OrgYogiAbstractPage<T extends OrgYogiAbstractPage<T>> extends AbstractPage<T> {
    private String configUrl="baseUrl";
    private By homeLink= By.xpath("//a[@href='https://heavenlyfarms.in/']");

    public OrgYogiAbstractPage click_home_link() {
        return load("ORGYOGI", configUrl);
    }


}
