package com.gw.v3;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.*;

public class ResultPageV3 {
    String[] serviceLevels = {"kiemelt", "normal", "alap"};
    InputDataV3 inputData;
    private ElementsCollection insuranceList;
    private SelenideElement numOfResults = $x(".//span[@class='font-size-18 szolgaltatas-csomag-db']");
    private SelenideElement carAssistanceCheckbox = $("#assistencia");

    public ResultPageV3(InputDataV3 inputData) {
        this.inputData = inputData;
        insuranceList = $$x(".//ul[@id='ajanlat-lista-egyedi_ajanlatok']/li[contains(@class, '" + getServiceLevel(this.inputData) + "')]");
    }

    public ResultDataV3 parseData() throws InterruptedException {Thread.sleep(2000);
        resultTableWaitToBeLoaded();

        ResultDataV3 result = new ResultDataV3();
        setServiceLevel(inputData);
        result.getServiceLevel().addAll(parseServiceLevel(inputData));
        result.getCompanyName().addAll(parseCompanyName(insuranceList));
        result.getProductName().addAll(parseProductName(insuranceList));
        result.getInsurancePremium().addAll(parsePremiums(insuranceList));
        result.getCustomerReview().addAll(parseCustomerReview(insuranceList));
        if(!Objects.equals(inputData.serviceLevel, "Alapszintű")) {
            result.getNetriskReview().addAll(parseNetristReviews(insuranceList));
        }
        result.getCarAssistance().addAll(parseCarAssistance(carAssistanceCheckbox));
        result.getMedexSumInsured().addAll(parseMedex(insuranceList));
        result.getLuggage().addAll(parseLuggage(insuranceList));
        result.getGadget().addAll(parseGadget(insuranceList));
        return result;
    }

    private String getServiceLevel(InputDataV3 input) {
        switch (input.serviceLevel) {
            case "Exkluzív":
                return serviceLevels[0];
            case "Optimális":
                return serviceLevels[1];
            case "Alapszintű":
                return serviceLevels[2];
            default:
                return "Service level input is invalid, please check the getServiceLevel function or the ResultData options";
        }
    }

    private void setServiceLevel(InputDataV3 input) throws InterruptedException {

        switch (input.serviceLevel) {
            case "Optimális":
                $x(".//label[@data-szolgaltatas_csomag_szint='" + this.serviceLevels[1] + "']/span[1]").click();
                $x(".//label[@data-szolgaltatas_csomag_szint='" + this.serviceLevels[0] + "']/span[1]").click();
                Thread.sleep(2000);
                break;
            case "Alapszintű":
                $x(".//label[@data-szolgaltatas_csomag_szint='" + this.serviceLevels[2] + "']/span[1]").click();
                $x(".//label[@data-szolgaltatas_csomag_szint='" + this.serviceLevels[0] + "']/span[1]").click();
                Thread.sleep(2000);
                break;
            default:
                break;
        }
    }

    private List<String> parseCarAssistance(SelenideElement assistanceCheckBox) {
        List carAssistance = new ArrayList();
        carAssistance.add("Car assistance");
        carAssistance.add("Autó asszisztencia");
        int results = Integer.parseInt(numOfResults.getText());
        String isCarAssistance;
        isCarAssistance = getCarAssistanceResult(assistanceCheckBox);

        for (int i = 0; i < results; i++) {
            carAssistance.add(isCarAssistance);

        }
        return carAssistance;
    }

    private static String getCarAssistanceResult(SelenideElement assistanceCheckBox) {
        String isCarAssistance;
        if(assistanceCheckBox.exists() && assistanceCheckBox.getAttribute("checked") != null) {
            isCarAssistance = "yes";
        }
        else {
            isCarAssistance = "no";
        }
        return isCarAssistance;
    }

    private List<String> parseCustomerReview(ElementsCollection listItems) {
        List<String> starCollection = new ArrayList();
        starCollection.add("Customer review");
        starCollection.add("Ügyfélértékelés");
        for (SelenideElement item : listItems) {
            ElementsCollection fullStars = item.$x(".//div[@class='nr-ertekeles-biztosito-container']//span[@class='nr-evaluation-stars']").$$x("./i[@class='fa fa-star']");
            ElementsCollection halfStar = item.$x(".//div[@class='nr-ertekeles-biztosito-container']//span[@class='nr-evaluation-stars']").$$x("./i[@class='fa fa-star-half-o']");
            if(!halfStar.isEmpty()) {
                starCollection.add(fullStars.size() + 0.5 + "");
            }
            else {
                starCollection.add(fullStars.size() + "");
            }
        }
        return starCollection;
    }

    private List<String> parseProductName(ElementsCollection listItems) {
        List resultProducts = new ArrayList();
        resultProducts.add("Product name");
        resultProducts.add("Termék");
        for (SelenideElement item : listItems) {
            resultProducts.add(item.$x(".//h2/a").getText());

        }
        return resultProducts;
    }
    private List<String> parseServiceLevel(InputDataV3 input) {
        List resultServiceLevels = new ArrayList();
        resultServiceLevels.add("Service level");
        resultServiceLevels.add("Szolgáltatás színvonala");
        int results = Integer.parseInt(numOfResults.getText());

        for (int i = 0; i < results; i++) {
            resultServiceLevels.add(input.serviceLevel);

        }
        return resultServiceLevels;
    }


    private void resultTableWaitToBeLoaded() throws InterruptedException {
        while (!executeJavaScript("return document.readyState").equals("complete")) {
            sleep(100);
        }
        insuranceList.get(0).should(Condition.exist,  Duration.ofSeconds(60));
        Thread.sleep(2000);
    }

    private List<String> parseCompanyName(ElementsCollection listItems) {
        List resultNames = new ArrayList();
        resultNames.add("Insurance company name");
        resultNames.add("Biztosító");
        for (SelenideElement item : listItems) {
            String companyName = item.$x(".//div[@class='biztosito-neve biztosito-kepes-ikon']").$x(".//img").getAttribute("alt");
            resultNames.add(companyName);
        }
        return resultNames;
    }

    private List<String> parsePremiums(ElementsCollection listItems) {
        List resultPrices = new ArrayList();
        resultPrices.add("Premium(HUF)");
        resultPrices.add("Díj(HUF)");
        for (SelenideElement item : listItems) {
            String priceText = item.$x(".//div[@class='font-size-22 nr-color font-weight-bold']").getText().trim();
            resultPrices.add(priceText);
        }
        return resultPrices;
    }

    private List<String> parseMedex(ElementsCollection listItems) {
        List resultMedexList = new ArrayList();
        resultMedexList.add("Medex");
        resultMedexList.add("Orvosi állátás");
        for (SelenideElement item : listItems) {
            String result = item.$x("./div/div/div/div[1]/div/div[3]/div/div/div[3]/div/div/div[1]/div[2]").getText();
            resultMedexList.add(result);
        }
        return resultMedexList;
    }

    private List<String> parseLuggage(ElementsCollection listItems) {
        List resultLuggageList = new ArrayList();
        resultLuggageList.add("Baggage");
        resultLuggageList.add("Útipoggyász");
        for (SelenideElement item : listItems) {
            String result = item.$x("./div/div/div/div[1]/div/div[3]/div/div/div[3]/div/div/div[2]/div[2]").getText();
            resultLuggageList.add(result);
        }
        return resultLuggageList;
    }

    private List<String> parseGadget(ElementsCollection listItems) {
        List resultGadgetList = new ArrayList();
        resultGadgetList.add("Gadget");
        resultGadgetList.add("Műszaki cikk");
        for (SelenideElement item : listItems) {
            String result = item.$x("./div/div/div/div[1]/div/div[3]/div/div/div[3]/div/div/div[3]/div[2]").getText();
            resultGadgetList.add(result);
        }
        return resultGadgetList;
    }

    private List<String> parseNetristReviews(ElementsCollection insuranceList) {
        List resultReviews = new ArrayList();
        resultReviews.add("Netrisk review");
        resultReviews.add("Szakértői értékelés");
        for (SelenideElement item : insuranceList) {
            String resultRate = item.$x(".//div[@class='product-rating-value-outer']/div/div[contains(@class,'disable-mouseleave')]").getText();
            resultReviews.add(resultRate);
        }
        return resultReviews;
    }
}
