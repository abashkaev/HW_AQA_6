import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class Authorization {
    public void validAuthorization (String login, String password, String codeVerify) {
        $("[data-test-id='login'] input").setValue(login);
        $("[data-test-id='password'] input").setValue(password);
        $(("[data-test-id='action-login']")).click();
        $("[data-test-id='code'] .input__top").shouldHave(Condition.text( "Код из SMS или Push")).shouldBe(Condition.visible);
        $("[data-test-id='code'] input").setValue(codeVerify);
        $("[data-test-id='action-verify']").click();
        $("[data-test-id='dashboard']").shouldHave(Condition.text("  Личный кабинет")).shouldBe(Condition.visible);
    }
}
