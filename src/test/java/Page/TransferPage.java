package Page;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    public void transferMoney (int amount, String numberCard) {
        $("[data-test-id='amount'] input").setValue(String.valueOf(amount));
        $("[data-test-id='from'] input").setValue(String.valueOf(numberCard));
        $("[data-test-id='action-transfer']").click();
    }
}
