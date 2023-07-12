package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public void isDashboardPageOpen () {
        $("[data-test-id='dashboard']").shouldHave(Condition.text("  Личный кабинет")).shouldBe(Condition.visible);
    }
    public int getCardBalance(int index) {
        var text = cards.get(index).getText();
        return extractBalance(text);
    }



    public void addCardBalance (int index) {
        cards.get(index).$("[data-test-id='action-deposit']").click();
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
