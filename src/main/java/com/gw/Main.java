package com.gw;

import com.codeborne.selenide.Configuration;
import com.gw.v1.InputData;
import com.gw.v1.ResultData;
import com.gw.v1.ResultPage;
import com.gw.v1.SearchPage;
import com.gw.v2.InputDataV2;
import com.gw.v2.LandingPage;
import org.jutils.jprocesses.JProcesses;
import org.jutils.jprocesses.model.ProcessInfo;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Main {

    public static void main(String[] args) {

        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

        List<ResultData> outputDatas = new ArrayList<>();
        List<InputDataV2> inputData = new ArrayList<>();
        inputData.add(new InputDataV2("Europa", "Chorwacja", "Wypoczynek, zwiedzanie", 8, "2", true, new String[]{"01-01-1990", "01-01-1990"}));
        inputData.add(new InputDataV2("Europa", "Włochy", "Wypoczynek, zwiedzanie", 14, "2", true, new String[]{"01-01-1990", "01-01-1990"}));
        inputData.add(new InputDataV2("Afryka", "Egipt", "Wypoczynek, zwiedzanie", 8, "3", true, new String[]{"12-06-1985", "13-08-1991", "12-05-2020"}));
        inputData.add(new InputDataV2("Azja", "Turcja", "Wypoczynek, zwiedzanie", 8, "4", true, new String[]{"18-09-1984", "25-11-1992", "12-11-2019", "05-06-2015"}));
        inputData.add(new InputDataV2("Afryka", "Egipt", "Wypoczynek, zwiedzanie", 14, "3", true, new String[]{"12-06-1985", "13-08-1991", "12-05-2020"}));
        inputData.add(new InputDataV2("Azja", "Turcja", "Wypoczynek, zwiedzanie", 14, "4", true, new String[]{"18-09-1984", "25-11-1992", "12-11-2019", "05-06-2015"}));
        inputData.add(new InputDataV2("Ameryka Południowa", "Brazylia", "Wypoczynek, zwiedzanie", 14, "2", true, new String[]{"10-02-1985", "13-03-1990"}));
        inputData.add(new InputDataV2("Ameryka Północna", "Stany Zjednoczone", "Wypoczynek, zwiedzanie", 14, "2", true, new String[]{"30-10-1986", "12-12-1987"}));
        inputData.add(new InputDataV2("Europa", "Chorwacja", "Wypoczynek, zwiedzanie", 8, "2", true, new String[]{"01-01-1990", "01-02-1987"}));
        inputData.add(new InputDataV2("Europa", "Chorwacja", "Sporty ekstremalne", 8, "2", true, new String[]{"01-01-1990", "01-02-1987"}));
        inputData.add(new InputDataV2("Europa", "Niemcy", "Praca fizyczna", 8, "2", true, new String[]{"05-04-1982", "05-04-1982"}));
        inputData.add(new InputDataV2("Europa", "Włochy", "Narty, snowboard", 8, "2", true, new String[]{"10-02-1985", "13-03-1990"}));
        inputData.add(new InputDataV2("Afryka", "Egipt", "Wypoczynek, zwiedzanie", 8, "2", true, new String[]{"12-12-1954", "13-11-1955"}));
        inputData.add(new InputDataV2("Europa", "Włochy", "Wypoczynek, zwiedzanie", 8, "6", true, new String[]{"10-10-1995", "12-05-1987", "25-12-1990", "30-09-1994", "18-07-1978", "14-05-1998"}));
        inputData.add(new InputDataV2("Europa", "Włochy", "Wypoczynek, zwiedzanie", 8, "10", true, new String[]{"10-10-1995", "12-05-1987", "25-12-1990", "30-09-1994", "18-07-1978", "14-05-1998", "10-10-1995", "12-05-1987", "25-12-1990", "30-09-1994"}));

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");

        Configuration.browser = "edge";
        Configuration.browserCapabilities = options;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = true;

        System.out.println("Headless method: " + Configuration.headless);
        System.out.println("Browser Capabilities: " + Configuration.browserCapabilities.toString());

        List<String> processesPidList = null;
        if (!Configuration.headless)
            processesPidList = JProcesses.getProcessList("msedge.exe").stream().map(ProcessInfo::getPid).collect(Collectors.toList());

        try {
            int counter = 1;
            for (InputDataV2 data : inputData) {
                System.out.println(" -- Round: " + counter);
                counter++;

                open("https://rankomat.pl/kalkulator/ubezpieczenia-turystyczne");
                new CookieHandler().acceptCookies();
                new LandingPage(data).populateOfferFormWithData(data);

                outputDatas.add(
                        new ResultPage().parseData());

                getWebDriver().quit();

                for (String pI : JProcesses.getProcessList("msedge.exe").stream().map(ProcessInfo::getPid).collect(Collectors.toList())) {
                    if (!processesPidList.contains(pI)) {
                        JProcesses.killProcess(Integer.parseInt(pI));
                    }
                }
            }
            new ExcelWriter().writeToExcelV2(inputData, outputDatas);
        } catch(Exception e){
            if (!Configuration.headless)
            for (String pI : JProcesses.getProcessList("msedge.exe").stream().map(ProcessInfo::getPid).collect(Collectors.toList())) {
                if (!processesPidList.contains(pI)) {
                    JProcesses.killProcess(Integer.parseInt(pI));
                }
            }
        }
    }

    public static void mainV1(String[] args) throws Exception {

        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

        List<ResultData> outputDatas = new ArrayList<>();
        List<InputData> inputDatas = new ArrayList<>();
        inputDatas.add(new InputData("Chorvatsko", 7, Arrays.asList("42", "36", "17", "15"), false));
        inputDatas.add(new InputData("Řecko", 14, Arrays.asList("42", "36", "15"), false));
        inputDatas.add(new InputData("Itálie", 14, Arrays.asList("33"), false));
        inputDatas.add(new InputData("Rakousko", 6, Arrays.asList("42", "36"), false));
        inputDatas.add(new InputData("Rakousko", 6, Arrays.asList("42", "36", "7", "5"), true));
        inputDatas.add(new InputData("Itálie", 3, Arrays.asList("33"), true));


//        Configuration.browser = "edge";
//        EdgeOptions options = new EdgeOptions().addArguments("--remote-allow-origins=*");
//        Configuration.browserCapabilities = options;

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
