package page;

import static com.codeborne.selenide.Selenide.$;

public class Authorization {
    public void validAuthorization (String login, String password) {
        $("[data-test-id='login'] input").setValue(login);
        $("[data-test-id='password'] input").setValue(password);
        $(("[data-test-id='action-login']")).click();

    }
}
