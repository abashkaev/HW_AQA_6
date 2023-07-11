package Page;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class Authorization {
    public void validAuthorization (String login, String password) {
        $("[data-test-id='login'] input").setValue(login);
        $("[data-test-id='password'] input").setValue(password);
        $(("[data-test-id='action-login']")).click();
        $("[data-test-id='code'] .input__top").shouldHave(Condition.text( "Код из SMS или Push")).shouldBe(Condition.visible);

    }
}
