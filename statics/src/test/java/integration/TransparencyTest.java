package integration;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

class TransparencyTest extends IntegrationTest {
  @BeforeEach
  void openTestPage() {
    Assumptions.assumeFalse(WebDriverRunner.isHtmlUnit(), "opacity:0 is visible in HtmlUnit");
    openFile("page_with_transparent_elements.html");
  }

  @Test
  void transparentElementIsInvisibleButClickable() {
    $("#inv-link").should(exist)
      .shouldNotBe(visible);
    $("#inv-link").click();
    $("#inv-link").doubleClick();
    $("#inv-link").contextClick();
  }

  @Test
  void almostTransparentElementIsVisibleAndClickable() {
    $("#link").shouldBe(visible);
    $("#link").click();
    $("#link").doubleClick();
    $("#link").contextClick();
  }

  @Test
  void transparentElementIsInvisibleButGetsInput() {
    $("#inv-input").should(exist)
      .shouldNotBe(visible);
    $("#inv-input").setValue("abc");
    $("#inv-input").clear();
    $("#inv-input").pressEnter();
    $("#inv-input").pressEscape();
    $("#inv-input").pressTab();
  }

  @Test
  void almostTransparentElementIsVisibleAndGetsInput() {
    $("#input").shouldBe(visible);
    $("#input").setValue("abc");
    $("#input").clear();
    $("#input").pressEnter();
    $("#input").pressEscape();
    $("#input").pressTab();
  }
}
