package com.gw;

import static com.codeborne.selenide.Selenide.$;

public class CookieHandler {

	public void acceptCookies(){
		$("#CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll").click();
	}

}
