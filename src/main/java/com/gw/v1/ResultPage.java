package com.gw.v1;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ResultPage {

    private SelenideElement insuranceTable = $x(".//table[contains(@class, 'results-table')]");
    private SelenideElement winterSportsSlider = $x(".//div[@id='slider[data_sport][winter]']");
    private SelenideElement nextButton = $x(".//button[contains(text(), 'Spočítat ceny')]");

    public ResultData parseData() throws InterruptedException {
        Thread.sleep(2000);
        long startTime = System.currentTimeMillis();
        resultTableWaitToBeLoaded();
        if (!insuranceTable.exists()) {
            if (nextButton.exists()) {
                nextButton.scrollIntoView(false).should(Condition.enabled).click();
                resultTableWaitToBeLoaded();
            }
        }
        ElementsCollection insuranceCompanyNamesAndPrices = insuranceTable.$$x("./thead/tr[1]/td");
        ResultData result = new ResultData();
        result.getCompanyName().addAll(parseCompanyName(insuranceCompanyNamesAndPrices));
        result.getInsurancePrice().addAll(parsePrices(insuranceCompanyNamesAndPrices));
        result.getLiecebneVylohy().addAll(parseCoverage(insuranceTable.$$x("./tbody/tr[5]/td")));
        result.getUrazovePojisteni().addAll(parseCoverage(insuranceTable.$$x("./tbody/tr[7]/td")));
        result.getPojistOdpovednosti().addAll(parseCoverage(insuranceTable.$$x("./tbody/tr[9]/td")));
        return result;
    }

    private void resultTableWaitToBeLoaded() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        while (startTime + 60000 > System.currentTimeMillis()) {
            if (insuranceTable.exists()) {
                break;
            }
            Thread.sleep(2000);
        }
    }

    private List<String> parseCompanyName(ElementsCollection tdNodes) {
        List resultNames = new ArrayList();
        for (SelenideElement td : tdNodes) {
            String path = td.$x(".//img").getAttribute("src");
            int index = path.lastIndexOf("/");
            resultNames.add(path.substring(index + 1).split("\\.")[0]);
        }
        return resultNames;
    }

    private List<String> parsePrices(ElementsCollection tdNodes) {
        List resultPrices = new ArrayList();
        for (SelenideElement td : tdNodes) {
            String priceText = td.$x(".//div/span/span[2]").getText();
            resultPrices.add(priceText.split(" K")[0]);
        }
        return resultPrices;
    }

    private List<String> parseCoverage(ElementsCollection tdNodes) {
        List resultList = new ArrayList();
        for (SelenideElement td : tdNodes) {
            resultList.add(td.$x(".//span").getText());
        }
        return resultList;
    }

    public void addWinterSports() {
        $x(".//div[@id='slider[data_sport][winter]']").click();
        $("#recalculate_button").should(Condition.visible, Duration.ofSeconds(10)).click();
        $x(".//input[@type='submit']").should(Condition.enabled, Duration.ofSeconds(25));
    }
}
