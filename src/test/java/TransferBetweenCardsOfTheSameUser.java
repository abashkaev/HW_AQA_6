
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static com.codeborne.selenide.Selenide.*;

public class TransferBetweenCardsOfTheSameUser {


@Test
    public void transferMoneyFirstCartToSecondCard (){ //Перевод с карты 1 на карту 2
    open("http://localhost:9999/");
    var auth = new Authorization();
    var dashboard = new DashboardPage();
    auth.validAuthorization("vasya", "qwerty123", "12345");
    int balanceSecondCardBeforeTransfer = dashboard.getCardBalance(1);
    int balanceFirstCardBeforeTransfer = dashboard.getCardBalance(0);
    int amountTransfer = ((balanceFirstCardBeforeTransfer * 100) / 100) * 50 / 100;
    dashboard.addCardBalance(1);
    $("[data-test-id='amount'] input").setValue(String.valueOf(amountTransfer));
    $("[data-test-id='from'] input").setValue(String.valueOf(DataHelper.getFirstCardInfo().getCardNumber()));
    $("[data-test-id='action-transfer']").click();
    var expectedBalanceFirstCard = balanceFirstCardBeforeTransfer - amountTransfer;
    var expectedBalanceSecondCard = balanceSecondCardBeforeTransfer + amountTransfer;
    Assertions.assertEquals(expectedBalanceFirstCard, dashboard.getCardBalance(0));
    Assertions.assertEquals(expectedBalanceSecondCard, dashboard.getCardBalance(1));
}
@Test
    public void transferMoneySecondCardToFirstCart () { //Перевод с карты 2 на карту 1
    open("http://localhost:9999/");
    var auth = new Authorization();
    var dashboard = new DashboardPage();
    auth.validAuthorization("vasya", "qwerty123", "12345");
    int balanceSecondCardBeforeTransfer = dashboard.getCardBalance(1);
    int balanceFirstCardBeforeTransfer = dashboard.getCardBalance(0);
    int amountTransfer = ((balanceSecondCardBeforeTransfer * 100) / 100) * 50 / 100;
    dashboard.addCardBalance(0);
    $("[data-test-id='amount'] input").setValue(String.valueOf(amountTransfer));
    $("[data-test-id='from'] input").setValue(String.valueOf(DataHelper.getSecondCardInfo().getCardNumber()));
    $("[data-test-id='action-transfer']").click();
    var expectedBalanceFirstCard = balanceFirstCardBeforeTransfer + amountTransfer;
    var expectedBalanceSecondCard = balanceSecondCardBeforeTransfer - amountTransfer;
    Assertions.assertEquals(expectedBalanceFirstCard, dashboard.getCardBalance(0));
    Assertions.assertEquals(expectedBalanceSecondCard, dashboard.getCardBalance(1));
}

@Test
    public void transferAllMoneyToFirstCard () { //Перевод всех средств
        open("http://localhost:9999/");
        var auth = new Authorization();
        var dashboard = new DashboardPage();
        auth.validAuthorization("vasya", "qwerty123", "12345");
        int balanceSecondCardBeforeTransfer = dashboard.getCardBalance(1);
        int balanceFirstCardBeforeTransfer = dashboard.getCardBalance(0);
        int amountTransfer = balanceSecondCardBeforeTransfer;
        dashboard.addCardBalance(0);
       $("[data-test-id='amount'] input").setValue(String.valueOf(amountTransfer));
        $("[data-test-id='from'] input").setValue(String.valueOf(DataHelper.getSecondCardInfo().getCardNumber()));
        $("[data-test-id='action-transfer']").click();
        var expectedBalanceFirstCard = balanceFirstCardBeforeTransfer + amountTransfer;
        var expectedBalanceSecondCard = balanceSecondCardBeforeTransfer - amountTransfer;
        Assertions.assertEquals(expectedBalanceFirstCard, dashboard.getCardBalance(0));
        Assertions.assertEquals(expectedBalanceSecondCard, dashboard.getCardBalance(1));
        Assertions.assertTrue(dashboard.getCardBalance(1) == 0);
        sleep(5000);
}
//
//    @Test
//    public void transferInvalidAmountToFirstCard () { //Перевод средств, превышающий количество средств на счету
//        open("http://localhost:9999/");
//        var auth = new Authorization();
//        var dashboard = new DashboardPage();
//        auth.validAuthorization("vasya", "qwerty123", "12345");
//        int balanceSecondCardBeforeTransfer = dashboard.getCardBalance(1);
//        int amountTransfer = balanceSecondCardBeforeTransfer + 10000;
//        dashboard.addCardBalance(0);
//        $("[data-test-id='amount'] input").setValue(String.valueOf(amountTransfer));
//        $("[data-test-id='from'] input").setValue(String.valueOf(DataHelper.getSecondCardInfo().getCardNumber()));
//        $("[data-test-id='action-transfer']").click();
//        dashboard.errorNotification();
//    }


}
