import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SoftAssertionsTest {
    @BeforeAll
    static void PrepareToRun(){
        Configuration.browser = "Chrome";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x900";
    }

    @Test
    void CheckCodeInside() {
        open("https://github.com/");
        $("[placeholder='Search or jump to...']").click();
        $("input[id='query-builder-test']").setValue("Selenide").pressEnter();
        $("a[href='/selenide/selenide']").click();
        $("#wiki-tab").click();
        $("#wiki-body").shouldHave(text("Soft assertions")).$(byText("Soft assertions")).click();
        $("#wiki-body").shouldHave(text("Soft assertions")).click();
        $$(".markdown-heading").findBy(text("JUnit5")).sibling(0).$("pre").shouldHave(text("@ExtendWith({SoftAssertsExtension.class})\n"+
                "class Tests {\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}"));
    }
}
