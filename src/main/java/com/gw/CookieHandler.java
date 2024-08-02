package com.gw;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CookieHandler {

	public void acceptCookies(){
		$("#continents").should(Condition.exist, Duration.ofSeconds(60));
		$("#CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll").should(Condition.exist, Duration.ofSeconds(60)).click();

//		long startTime = System.currentTimeMillis();
//		while (startTime + 15000 > System.currentTimeMillis()) {
//			if ($("#CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll").isDisplayed()) {
//				$("#CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll").click();
//				break;
//			} else if ($x(".//button[contains(text(), 'Tak')]").isDisplayed()) {
//				$x(".//button[contains(text(), 'Tak')]");
//				break;
//			}
//		}
	}

}
