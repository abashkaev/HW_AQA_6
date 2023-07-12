package page;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class Verification {
    public void validVerification (String codeVerify) {
        $("[data-test-id='code'] .input__top").shouldHave(Condition.text( "Код из SMS или Push")).shouldBe(Condition.visible);
        $("[data-test-id='code'] input").setValue(codeVerify);
        $("[data-test-id='action-verify']").click();
    }
}
