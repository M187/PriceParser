package com.gw.v3;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.gw.v3.vehicles.Car;
import com.gw.v3.vehicles.Vehicle;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import static com.codeborne.selenide.Selenide.*;

public class LandingPageV3 {

    private Calendar calndr = Calendar.getInstance();

    private SelenideElement

            numOfTravelersSelect = $("#utasok_szama"),
            btnVehicleSubmit = $x("//input[@name='section_tovabb_mivel']"),
            btnTravelDatesSubmit = $x("//input[@name='section_tovabb_mikor']");


    ElementsCollection
            travelersBirthdateInputs = $$x("//input[contains(@name, 'szuletes_datum')]");


    public void populateOfferFormWithData(InputDataV3 data) throws Exception {
        String currentDay = getCurrentDay();
        String tripEndDate = getTripEndDate(data.tripDuration);

        selectTripPurpose(data.tripPurpose);
        selectDestinationCountry(data.destinationCountry);
        selectVehicle(data.vehicle);
        setTripDate(currentDay, tripEndDate);
        setNumOfTravelers(data.noOfTravelers);
        setBirthDayOfTravelers(data.datesOfBirth);
    }

    private void selectVehicle(Vehicle vehicle) {

        switch (vehicle.name) {
            case "Autó":
                Car car = (Car) vehicle;
                $x("//div[@title='"+ car.name + "']").shouldBe(Condition.visible, Duration.ofSeconds(20)).click();

                $("#auto").should(Condition.exist).selectOption(car.quantity + 1);
                $("#gyartasi_ev_1").shouldBe(Condition.visible).setValue("" + car.yearOfManufacture);
                btnVehicleSubmit.click();
                break;
            case "Repülõ":
            case "Vonat / Busz":
            case "Egyéb":
                $x("//div[@title='"+ vehicle.name + "']").shouldBe(Condition.visible, Duration.ofSeconds(20)).click();
                btnVehicleSubmit.click();
                break;
        }
    }

    private void selectDestinationCountry(String destinationCountry) {
        $("#select2-celorszag_alap-container").shouldBe(Condition.visible, Duration.ofSeconds(10));
        $("#celorszag_alap").selectOption(destinationCountry);
        $x("//input[@value='Tovább']").click();
    }

    private void selectTripPurpose(String tripPurpose) {
        $x("//div[@title='Tengerparti nyaralás']").shouldBe(Condition.visible, Duration.ofSeconds(40));

        switch (tripPurpose) {
            case "Tengerparti nyaralás":
                $x("//div[@title='Tengerparti nyaralás']").click();
                break;
            case "Városnézés":
                $x("//div[contains(@title, 'Városnézés')]").click();
                break;
            case "Általános":
                $x("//div[contains(@title, 'Általános')]").click();
                break;
            case "Sport":
                $x("//div[contains(@title, 'Sport')]").click();
                break;

        }

    }

    private void setBirthDayOfTravelers(int[] datesOfBirth) {

        for (int i = 0; i < datesOfBirth.length; i++) {
            travelersBirthdateInputs.get(i).click();
            travelersBirthdateInputs.get(i) .setValue(calculateBirthdate(datesOfBirth[i]));
            }

        $x("//input[@name='section_tovabb_kik']").shouldBe(Condition.visible).click();
    }

    private void setNumOfTravelers(int numOfTravelers) {
        numOfTravelersSelect.shouldBe(Condition.visible, Duration.ofSeconds(20)).selectOption(numOfTravelers + " fő");
    }

    private void setTripDate(String currentDate, String tripEndDate) {
        $("#indulas").setValue(currentDate);
        $("#erkezes").setValue(tripEndDate);
        btnTravelDatesSubmit.click();
    }

    private String getTripEndDate(int tripLength) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.now().plusDays(tripLength + 2).format(formatter);
    }

    private String getCurrentDay() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.now().plusDays(2).format(formatter);
    }

    private static String calculateBirthdate(int age) {
        LocalDate today = LocalDate.now();
        LocalDate birthdate = today.minusYears(age).withDayOfYear(1);

        return birthdate.toString();
    }
}
