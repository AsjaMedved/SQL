package ru.netology.service.Page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.service.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class VerificationPage {
private SelenideElement codeVerification = $("[data-test-id='code'] input");
    private SelenideElement continueButton = $$("button").findBy(Condition.text("Продолжить"));
    private SelenideElement errorNotification = $("[data-test-id='error-notification'] .notification__content");


    public VerificationPage() {
        codeVerification.shouldBe(Condition.visible);
    }
    public void verificationCode (String code){
    codeVerification.setValue(code);
        continueButton.click();
    }

    public DashboardPage validVerify(DataHelper.VerificationCode code) {
        verificationCode(code.getCode());
        return new DashboardPage();
    }

    public void incorrectVerificationCode() {
        errorNotification.shouldBe(Condition.visible).shouldHave(Condition.text("Ошибка! Неверно указан код! Попробуйте ещё раз"));
    }

}
