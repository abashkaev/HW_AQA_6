package Page;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class Verification {
    public void validVerification (String codeVerify) {
        $("[data-test-id='code'] input").setValue(codeVerify);
        $("[data-test-id='action-verify']").click();
        $("[data-test-id='dashboard']").shouldHave(Condition.text("  Личный кабинет")).shouldBe(Condition.visible);
    }
}
