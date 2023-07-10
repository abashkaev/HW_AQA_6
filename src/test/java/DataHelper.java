import lombok.Value;
import lombok.val;

public class DataHelper {
    public static CardInfo getFirstCardInfo () {
        return new CardInfo("5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
    }

    public static  CardInfo getSecondCardInfo () {
        return new CardInfo("5559 0000 0000 0002","0f3f5c2a-249e-4c3d-8287-09f7a039391d");
    }

    @Value
    public static class CardInfo {
        String cardNumber;
        String testId;
    }

}
