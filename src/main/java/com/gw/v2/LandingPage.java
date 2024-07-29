package com.gw.v2;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import static com.codeborne.selenide.Selenide.*;

public class LandingPage {
    private final InputDataV2 data;
    private Calendar calndr = Calendar.getInstance();

    private SelenideElement
            continent,
            countryOption,
            tripPurpose,
            pageHeader = $("#rank-header"),
            countryDropdown = $x("//div[@id='destinationCountries']//rank-select"),
            travelTimePicker = $x("//section[@id='travelTime']//rank-date-picker"),
            btnNextMonth = $x("//div[contains(@class,'next-month')]//button"),
            numOfTravelersInput = $x("//div[@id='travelersCount']//input"),
            btnTermsAndCondition = $("#termsOfUseAllowance_checkbox"),
            btnShowMoreOffers = $("#buttonShowOffers");


    ElementsCollection
            btnsTravelersLivingInResidence = $$x("//div[@id='travelersLivingInResidence']//input/parent::div"),
            travelersBirthdate = $$x("//section[contains(@id, 'traveler')]//rank-date-picker");

    public LandingPage(InputDataV2 data) {
        this.data = data;
        this.continent = $x("//div[text()='"+ getContinent(data)+"']/ancestor::rank-pic");
        this.countryOption = $x("//div[text()='"+ getCountry(data)+"']");
        this.tripPurpose = $x("//div[text()='"+ getTripPurpose(data)+"']/ancestor::rank-pic");
    }

    public void populateOfferFormWithData(InputDataV2 data) throws Exception {
        String currentDay = getCurrentDay();
        String tripEndDate = getTripEndDate(data.tripDuration);

        waitUntilBrowserFullyLoads();
        setContinent();
        setCountry();
        setTripPurpose();
        setTripDate(currentDay, tripEndDate);
        setNumOfTravelers(data.noOfTravelers);
        setIfTravelersFromPoland(data.allTravelersFromPoland);
        setBirthDayOfTravelers(data.datesOfBirth);
        acceptTermsOfUse();
        startQuote();
    }

    private void waitUntilBrowserFullyLoads() {
        while (!executeJavaScript("return document.readyState").equals("complete")) {
            sleep(100);
        }
    }

    private void setTripPurpose() {
        tripPurpose.click();
    }

    private void setContinent() {
        continent.shouldBe(Condition.visible, Duration.ofSeconds(30)).click();
    }

    private void setCountry() {
        countryDropdown.click();
        countryOption.scrollIntoView(false);
        countryOption.click();
        pageHeader.click();

    }

    private void startQuote() {
        btnShowMoreOffers.scrollIntoView(true).click();
        System.out.println("Success");
    }

    private void acceptTermsOfUse() {
        // hide overlapping element before click
        executeJavaScript("document.querySelector('#termsOfUseAllowance > div.rank-question-area > div > div:nth-child(1) > div > label > span').style.display = 'none';");
        btnTermsAndCondition.scrollIntoView(true).click();
        btnTermsAndCondition.shouldHave(Condition.attributeMatching("class", ".*ng-not-empty.*"));
    }

    private void setBirthDayOfTravelers(String[] datesOfBirth) throws Exception {
        if(datesOfBirth.length == travelersBirthdate.size()) {
            for (int i = 0; i < datesOfBirth.length; i++) {
                travelersBirthdate.get(i).click();
                travelersBirthdate.get(i).$x(".//input").setValue(datesOfBirth[i]);
            }
        } else {
            throw new Exception("Not enough birthday input for all travelers");
        }
    }

    private void setIfTravelersFromPoland(boolean isFromPoland) {
        if(isFromPoland) {
            btnsTravelersLivingInResidence.get(0).click();
        } else {
            btnsTravelersLivingInResidence.get(1).scrollIntoView(true).click();
        }
    }

    private void setNumOfTravelers(String numOfTravelers) {
        $x("//div[@id='travelersCount']//rank-select").click();
        numOfTravelersInput.setValue(numOfTravelers);
        $x("//div[@id='travelersCount']//div[text()='"+numOfTravelers+"']").shouldBe(Condition.visible).click();
    }

    private void setTripDate(String currentDate, String tripEndDate) {
        //Make input fields editable
        executeJavaScript("document.querySelector(arguments[0]).removeAttribute('readonly');", "div.rank-question-area > rank-date-picker > div > div > input:nth-child(1)");
        executeJavaScript("document.querySelector(arguments[0]).removeAttribute('readonly');", "div.rank-question-area > rank-date-picker > div > div > input:nth-child(2)");
        $$x("//div[@class='rank-input-range rank-input-control']//input").get(0).setValue(currentDate);
        $$x("//div[@class='rank-input-range rank-input-control']//input").get(1).setValue(tripEndDate);
    }

    private String getTripEndDate(int tripLength) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.now().plusDays(tripLength + 1).format(formatter);
    }

    private String getCurrentDay() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.now().plusDays(1).format(formatter);
    }

    private String getContinent(InputDataV2 data){
        return data.continent;
    }

    private String getCountry(InputDataV2 data){
        return data.destinationCountry;
    }

    private String getTripPurpose(InputDataV2 data){
        return data.tripPurpose;
    }
}
