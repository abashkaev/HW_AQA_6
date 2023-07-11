package Page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";


    public int getCardBalance(int index) {
        var text = cards.get(index).getText();
        return extractBalance(text);
    }

    public void errorNotification () {
        $("[data-test-id='error-notification']").shouldHave(Condition.text("Ошибка")).shouldBe(Condition.visible);
    }

    public void addCardBalance (int index) {
        cards.get(index).$("[data-test-id='action-deposit']").click();
        $(".heading.heading_size_xl").shouldBe(Condition.text("Пополнение карты"));
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
