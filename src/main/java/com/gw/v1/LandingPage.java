package com.gw.v1;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.Calendar;

import static com.codeborne.selenide.Selenide.*;

public class LandingPage {

	private SelenideElement
			destinaiton = $("#formparam_destination_input"),
			travelerAge = $("#traveller_"),
//			nextButton = $x(".//button[contains(text(), 'Spočítat ceny')]"),
			nextButton = $x(".//button[@name='formparam_data_next']"),
			dayOfReturn = $("#field_date_return_day"),
			monthOfReturn = $("#field_date_return_month"),
			yearOfReturn = $("#field_date_return_year");
	ElementsCollection a = $("#travelcz").$$x(".//div[contains(@class, 'form-group')]");

	public void basicSearch(InputData inputData) {
		destinaiton.setValue(inputData.getDestination());
		for (int i = 1; i < inputData.getTravelersAge().size() + 1; i++){
			String age = inputData.getTravelersAge().get(i-1);
			$("#traveller_" + i).setValue(age);
			$("#traveller_" + (i+1)).should(Condition.visible, Duration.ofSeconds(30)).scrollIntoView(true).click();
		}

		Calendar calndr2 = Calendar.getInstance();
		calndr2.add(Calendar.DAY_OF_WEEK, inputData.getDurationInDyas() +1);

		dayOfReturn.setValue(String.valueOf(calndr2.get(Calendar.DAY_OF_MONTH)));
		monthOfReturn.setValue(String.valueOf(calndr2.get(Calendar.MONTH) + 1));
		yearOfReturn.setValue(String.valueOf(calndr2.get(Calendar.YEAR)));

//		a.get(a.size() - 2).click();
		nextButton.scrollIntoView(true).should(Condition.enabled).click();
		System.out.println(" ---- ");
	}
}
