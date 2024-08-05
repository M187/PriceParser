package com.gw;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class CookieHandler {

	public void acceptCookies(){
		$("#CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll").should(Condition.exist, Duration.ofSeconds(60)).click();
	}

	public void acceptCookiesV2(){
		$("#continents").should(Condition.exist, Duration.ofSeconds(60));
		$("#CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll").should(Condition.exist, Duration.ofSeconds(60)).click();
	}

}
