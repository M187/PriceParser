package com.gw;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class Main {

    public static void main(String[] args) throws Exception {

        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

        List<ResultData> outputDatas = new ArrayList<>();
        List<InputData> inputDatas = new ArrayList<>();
        inputDatas.add(new InputData("Chorvatsko", 7, Arrays.asList("42", "36", "17","15"), false));
        inputDatas.add(new InputData("Řecko", 14, Arrays.asList("42", "36", "15"), false));
        inputDatas.add(new InputData("Itálie", 14, Arrays.asList("33"), false));
        inputDatas.add(new InputData("Rakousko", 6, Arrays.asList("42", "36"), false));
        inputDatas.add(new InputData("Rakousko", 6, Arrays.asList("42", "36","7","5"), true));
        inputDatas.add(new InputData("Itálie", 3, Arrays.asList("33"), true));


        Configuration.browser = "edge";

        for (InputData iD : inputDatas) {
            System.out.println(" >>> Starting iteration for input data >>> " + iD.string());
            open("https://www.top-pojisteni.cz/cestovni-pojisteni/kalkulace-a-srovnani");

            new CookieHandler().acceptCookies();
            new SearchPage().basicSearch(iD);
            if (iD.isAddWinterSports()) new ResultPage().addWinterSports();
            outputDatas.add(
                    new ResultPage().parseData());

            Thread.sleep(30000);
            closeWebDriver();
        }
        new ExcelWriter().writeToExcel(inputDatas, outputDatas);
    }
}
