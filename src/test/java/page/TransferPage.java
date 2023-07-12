package page;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    public void transferMoney (int amount, String numberCard) {
        $(".heading.heading_size_xl").shouldBe(Condition.text("Пополнение карты"));
        $("[data-test-id='amount'] input").setValue(String.valueOf(amount));
        $("[data-test-id='from'] input").setValue(String.valueOf(numberCard));
        $("[data-test-id='action-transfer']").click();
    }
    public void errorNotification () {
        $("[data-test-id='error-notification']").shouldHave(Condition.text("Ошибка")).shouldBe(Condition.visible);
    }
}
