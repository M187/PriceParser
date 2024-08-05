package com.gw.v1;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.sleep;

public class ResultPage {
    private SelenideElement spinner = $x(".//div[contains(@class,'rank-spinner')]");
    private SelenideElement loadingDialog = $x("//div[@class='rank-modal-dialog']");
    private SelenideElement insuranceList = $x("//offers-list");
    private ElementsCollection insuranceCompanyNames = insuranceList.$$x(".//div[contains(@class, 'logo__image')]");

    public ResultData parseData() throws InterruptedException {Thread.sleep(2000);
        resultTableWaitToBeLoaded();
        ElementsCollection premium = insuranceList.$$x(".//div[contains(text(), 'Cena polisy')]/following-sibling::span[1]");
        ElementsCollection medexSumInsured = insuranceList.$$x(".//div[contains(@class, 'offer-bar__details--params')]/div[1]");
        ElementsCollection oc = insuranceList.$$x(".//div[contains(@class, 'offer-bar__details--params')]/div[2]");
        ElementsCollection ratings = insuranceList.$$x(".//div[@class='offer-bar__rating']//offer-rating/div/div[3]");

        ResultData result = new ResultData();
        result.getCompanyName().addAll(parseCompanyName(insuranceCompanyNames));
        result.getInsurancePremium().addAll(parsePremiums(premium));
        result.getMedexSumInsured().addAll(parseCoverages(medexSumInsured));
        result.getOc().addAll(parseCoverages(oc));
        result.getRate().addAll(parseRatings(ratings));
        return result;
    }


    private void resultTableWaitToBeLoaded() throws InterruptedException {
        while (!executeJavaScript("return document.readyState").equals("complete")) {
            sleep(100);
        }
        loadingDialog.shouldNotBe(Condition.visible, Duration.ofSeconds(100));
        spinner.shouldNotBe(Condition.visible, Duration.ofSeconds(100));
        insuranceCompanyNames.get(0).shouldBe(Condition.visible,  Duration.ofSeconds(60));
    }

    private List<String> parseCompanyName(ElementsCollection elements) {
        List resultNames = new ArrayList();
        for (SelenideElement element : elements) {
            String path = element.$x(".//img").getAttribute("src");
            int index = path.lastIndexOf("/");
            resultNames.add(path.substring(index + 1).split("\\.")[0]);
        }
        return resultNames;
    }

    private List<String> parsePremiums(ElementsCollection spanNodes) {
        List resultPrices = new ArrayList();
        for (SelenideElement span : spanNodes) {
            String priceText = span.getText().trim();
            priceText = priceText.replaceAll("\\D+", "");
            priceText = priceText.substring(0, priceText.length() - 2) + "," + priceText.substring(priceText.length() - 2) + " zł";
            resultPrices.add(priceText);
        }
        return resultPrices;
    }

    private List<String> parseCoverages(ElementsCollection divs) {
        List resultList = new ArrayList();
        for (SelenideElement div : divs) {
            String result = div.$x("./div[2]").getText();
            resultList.add(result.isEmpty() || result.equals("-") ? "N/A" : result);
        }
        return resultList;
    }

    private List<String> parseRatings(ElementsCollection divs) {
        List resultRates = new ArrayList();
        for (SelenideElement div : divs) {
            String resultRate = div.$x("./div").getText().trim();
            resultRates.add(resultRate);
        }
        return resultRates;
    }


    public void addWinterSports() {
        $x(".//div[@id='slider[data_sport][winter]']").click();
        $("#recalculate_button").should(Condition.visible, Duration.ofSeconds(10)).click();
        $x(".//input[@type='submit']").should(Condition.enabled, Duration.ofSeconds(25));
    }
}
