package data;

import lombok.Value;

public class DataHelper {
    public static CardInfo getFirstCardInfo () {
        return new CardInfo("5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
    }

    public static  CardInfo getSecondCardInfo () {
        return new CardInfo("5559 0000 0000 0002","0f3f5c2a-249e-4c3d-8287-09f7a039391d");
    }

    public static UserInfo getTestUser () {
        return new UserInfo("vasya", "qwerty123");
    }

    public static VerifyInfo getTestCode () {
        return new VerifyInfo("12345");
    }

    public static AmountTransfer getValidAmount (int startAmount) {
        int amount = (startAmount * 100) * 50 / 10_000;
        return new AmountTransfer(amount);
    }
    public static AmountTransfer getAllMoney (int startAmount) {
        return new AmountTransfer(startAmount);
    }



    @Value
    public static class CardInfo {
        String cardNumber;
        String testId;
    }

    @Value
    public static class  UserInfo {
        String name;
        String password;

    }
    @Value
    public static class VerifyInfo {
        String verifyCode;
    }

    @Value
    public static class AmountTransfer{
        int amount;
    }

}
