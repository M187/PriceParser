package com.gw;

import com.codeborne.selenide.Configuration;
import com.gw.v1.InputData;
import com.gw.v1.ResultData;
import com.gw.v1.ResultPage;
import com.gw.v1.SearchPage;
import com.gw.v2.InputDataV2;
import org.openqa.selenium.edge.EdgeOptions;
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
        List<InputDataV2> inputDatas = new ArrayList<>();
        inputDatas.add(new InputDataV2("Europa","Chorwacja", "Wypoczynek/zwiedzanie", "8", "2", true, new String[]{"1.1.1990", "1.1.1990"}));
        inputDatas.add(new InputDataV2("Europa","Włochy", "Wypoczynek/zwiedzanie", "14", "2", true, new String[]{"1.1.1990", "1.1.1990"}));
        inputDatas.add(new InputDataV2("Afryka","Egipt", "Wypoczynek/zwiedzanie", "8", "3", true, new String[]{"12.6.1985", "13.8.1991", "12.5.2020"}));
        inputDatas.add(new InputDataV2("Azja","Turcja", "Wypoczynek/zwiedzanie", "8", "4", true, new String[]{"18.9.1984", "25.11.1992", "12.11.2019", "5.6.2015"}));
        inputDatas.add(new InputDataV2("Afryka","Egipt", "Wypoczynek/zwiedzanie", "14", "3", true, new String[]{"12.6.1985", "13.8.1991", "12.5.2020"}));
        inputDatas.add(new InputDataV2("Azja","Turcja", "Wypoczynek/zwiedzanie", "14", "4", true, new String[]{"18.9.1984", "25.11.1992", "12.11.2019", "5.6.2015"}));
        inputDatas.add(new InputDataV2("Ameryka Południowa","Brazylia", "Wypoczynek/zwiedzanie", "14", "2", true, new String[]{"10.2.1985", "13.3.1990"}));
        inputDatas.add(new InputDataV2("Ameryka Północna","Stany Zjednoczone", "Wypoczynek/zwiedzanie", "14", "2", true, new String[]{"30.10.1986", "12.12.1987"}));
        inputDatas.add(new InputDataV2("Europa","Chorwacja", "Wypoczynek/zwiedzanie", "8", "2", true, new String[]{"1.1.1990", "1.2.1987"}));
        inputDatas.add(new InputDataV2("Europa","Chorwacja", "Sporty ekstremalne", "8", "2", true, new String[]{"1.1.1990", "1.2.1987"}));
        inputDatas.add(new InputDataV2("Europa","Niemcy", "Praca fizyczna", "8", "2", true, new String[]{"5.4.1982", "5.4.1982"}));
        inputDatas.add(new InputDataV2("Europa","Włochy", "Narty/Snowboard", "8", "2", true, new String[]{"10.2.1985", "13.3.1990"}));
        inputDatas.add(new InputDataV2("Afryka","Egipt", "Wypoczynek/zwiedzanie", "8", "2", true, new String[]{"12.12.1954", "13.11.1955"}));
        inputDatas.add(new InputDataV2("Europa","Włochy", "Wypoczynek/zwiedzanie", "8", "6", true, new String[]{"10.10.1995", "12.5.1987", "25.12.1990", "30.9.1994", "18.7.1978", "14.5.1998"}));
        inputDatas.add(new InputDataV2("Europa","Włochy", "Wypoczynek/zwiedzanie", "8", "12", true, new String[]{"10.10.1995", "12.5.1987", "25.12.1990", "30.9.1994", "18.7.1978", "14.5.1998", "10.10.1995", "12.5.1987", "25.12.1990", "30.9.1994", "18.7.1978", "14.5.1998"}));


        Configuration.browser = "edge";
        EdgeOptions options = new EdgeOptions().addArguments("--remote-allow-origins=*");
        Configuration.browserCapabilities = options;

        for (InputDataV2 iD : inputDatas) {
//            System.out.println(" >>> Starting iteration for input data >>> " + iD.string());
            open("https://rankomat.pl/kalkulator/ubezpieczenia-turystyczne/?calculationId=TLXDRM#/s/first");

            new CookieHandler().acceptCookies();
//            new SearchPage().basicSearch(iD);
//            if (iD.isAddWinterSports()) new ResultPage().addWinterSports();
            outputDatas.add(
                    new ResultPage().parseData());

            Thread.sleep(30000);
            closeWebDriver();
        }
        new ExcelWriter().writeToExcelV2(inputDatas, outputDatas);
    }

    public static void mainV1(String[] args) throws Exception {

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
        EdgeOptions options = new EdgeOptions().addArguments("--remote-allow-origins=*");
        Configuration.browserCapabilities = options;

        for (InputData iD : inputDatas) {
            System.out.println(" >>> Starting iteration for input data >>> " + iD.string());
            open("https://rankomat.pl/kalkulator/ubezpieczenia-turystyczne/?calculationId=TLXDRM#/s/first");

            new CookieHandler().acceptCookies();
            new SearchPage().basicSearch(iD);
            if (iD.isAddWinterSports()) new ResultPage().addWinterSports();
            outputDatas.add(
                    new ResultPage().parseData());

            Thread.sleep(30000);
            closeWebDriver();
        }
        new ExcelWriter().writeToExcelV1(inputDatas, outputDatas);
    }
}
