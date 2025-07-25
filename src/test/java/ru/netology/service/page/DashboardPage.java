package ru.netology.service.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement dashboard = $("[data-test-id='dashboard']").shouldBe(text("Личный кабинет"));

    public DashboardPage() {
        dashboard.shouldBe(visible);
    }
}
