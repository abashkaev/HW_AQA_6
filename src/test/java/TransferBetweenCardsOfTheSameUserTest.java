import data.DataHelper;
import page.Authorization;
import page.DashboardPage;
import page.TransferPage;
import page.Verification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static com.codeborne.selenide.Selenide.*;

public class TransferBetweenCardsOfTheSameUserTest {


    @Test
    public void transferMoneyFirstCartToSecondCard() { //Перевод с карты 1 на карту 2
        open("http://localhost:9999/");
        var auth = new Authorization();
        auth.validAuthorization(DataHelper.getTestUser().getName(), DataHelper.getTestUser().getPassword());
        var verify = new Verification();
        verify.validVerification(DataHelper.getTestCode().getVerifyCode());
        var dashboard = new DashboardPage();
        dashboard.isDashboardPageOpen();
        int balanceSecondCardBeforeTransfer = dashboard.getCardBalance(1);
        int balanceFirstCardBeforeTransfer = dashboard.getCardBalance(0);
        dashboard.addCardBalance(1);
        var transfer = new TransferPage();
        int amountTransfer = DataHelper.getValidAmount(balanceFirstCardBeforeTransfer).getAmount();
        transfer.transferMoney(amountTransfer, DataHelper.getFirstCardInfo().getCardNumber());
        dashboard.isDashboardPageOpen();
        var expectedBalanceFirstCard = balanceFirstCardBeforeTransfer - amountTransfer;
        var expectedBalanceSecondCard = balanceSecondCardBeforeTransfer + amountTransfer;
        Assertions.assertEquals(expectedBalanceFirstCard, dashboard.getCardBalance(0));
        Assertions.assertEquals(expectedBalanceSecondCard, dashboard.getCardBalance(1));
    }

    @Test
    public void transferMoneySecondCardToFirstCart() { //Перевод с карты 2 на карту 1
        open("http://localhost:9999/");
        var auth = new Authorization();
        auth.validAuthorization(DataHelper.getTestUser().getName(), DataHelper.getTestUser().getPassword());
        var verify = new Verification();
        verify.validVerification(DataHelper.getTestCode().getVerifyCode());
        var dashboard = new DashboardPage();
        dashboard.isDashboardPageOpen();
        int balanceSecondCardBeforeTransfer = dashboard.getCardBalance(1);
        int balanceFirstCardBeforeTransfer = dashboard.getCardBalance(0);
        dashboard.addCardBalance(0);
        var transfer = new TransferPage();
        int amountTransfer = DataHelper.getValidAmount(balanceSecondCardBeforeTransfer).getAmount();
        transfer.transferMoney(amountTransfer, DataHelper.getSecondCardInfo().getCardNumber());
        dashboard.isDashboardPageOpen();
        var expectedBalanceFirstCard = balanceFirstCardBeforeTransfer + amountTransfer;
        var expectedBalanceSecondCard = balanceSecondCardBeforeTransfer - amountTransfer;
        Assertions.assertEquals(expectedBalanceFirstCard, dashboard.getCardBalance(0));
        Assertions.assertEquals(expectedBalanceSecondCard, dashboard.getCardBalance(1));
    }

    @Test
    public void transferAllMoneyToFirstCard() { //Перевод всех средств
        open("http://localhost:9999/");
        var auth = new Authorization();
        auth.validAuthorization(DataHelper.getTestUser().getName(), DataHelper.getTestUser().getPassword());
        var verify = new Verification();
        verify.validVerification(DataHelper.getTestCode().getVerifyCode());
        var dashboard = new DashboardPage();
        dashboard.isDashboardPageOpen();
        int balanceSecondCardBeforeTransfer = dashboard.getCardBalance(1);
        int balanceFirstCardBeforeTransfer = dashboard.getCardBalance(0);
        dashboard.addCardBalance(0);
        var transfer = new TransferPage();
        int amountTransfer = DataHelper.getAllMoney(balanceSecondCardBeforeTransfer).getAmount();
        transfer.transferMoney(amountTransfer, DataHelper.getSecondCardInfo().getCardNumber());
        dashboard.isDashboardPageOpen();
        var expectedBalanceFirstCard = balanceFirstCardBeforeTransfer + amountTransfer;
        var expectedBalanceSecondCard = balanceSecondCardBeforeTransfer - amountTransfer;
        Assertions.assertEquals(expectedBalanceFirstCard, dashboard.getCardBalance(0));
        Assertions.assertEquals(expectedBalanceSecondCard, dashboard.getCardBalance(1));
        Assertions.assertTrue(dashboard.getCardBalance(1) == 0);
    }

    @Test
    public void transferInvalidAmountToFirstCard () { //Перевод средств, превышающий количество средств на счету
        open("http://localhost:9999/");
        var auth = new Authorization();
        auth.validAuthorization(DataHelper.getTestUser().getName(), DataHelper.getTestUser().getPassword());
        var verify = new Verification();
        verify.validVerification(DataHelper.getTestCode().getVerifyCode());
        var dashboard = new DashboardPage();
        dashboard.isDashboardPageOpen();
        int balanceSecondCardBeforeTransfer = dashboard.getCardBalance(1);
        int amountTransfer = balanceSecondCardBeforeTransfer + 10000;
        dashboard.addCardBalance(0);
        var transfer = new TransferPage();
       transfer.transferMoney(amountTransfer, DataHelper.getSecondCardInfo().getCardNumber());
       transfer.errorNotification();
    }


}
