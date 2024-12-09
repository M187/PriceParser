package com.gw;

import com.codeborne.selenide.Configuration;
import com.gw.v1.InputData;
import com.gw.v1.ResultData;
import com.gw.v1.ResultPage;
import com.gw.v1.LandingPage;
import com.gw.v2.InputDataV2;
import com.gw.v2.LandingPageV2;
import com.gw.v2.ResultDataV2;
import com.gw.v2.ResultPageV2;
import com.gw.v3.InputDataV3;
import com.gw.v3.LandingPageV3;
import com.gw.v3.ResultDataV3;
import com.gw.v3.ResultPageV3;
import com.gw.v3.vehicles.Car;
import com.gw.v3.vehicles.Vehicle;
import org.jutils.jprocesses.JProcesses;
import org.jutils.jprocesses.model.ProcessInfo;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Main {

    public static void mainV3(String[] args) throws Exception {

//        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

        List<ResultDataV3> outputDatas = new ArrayList<>();
        List<InputDataV3> inputData = new ArrayList<>();
        //service level: exclusive
        inputData.add(new InputDataV3("Exkluzív", "Tengerparti nyaralás", "Olaszország", new Vehicle("Vonat / Busz"), 7, 2, new int[]{34, 34}));
        inputData.add(new InputDataV3("Exkluzív", "Sport", "Ausztria", new Vehicle("Egyéb"), 3, 1, new int[] {34}));
        inputData.add(new InputDataV3("Exkluzív", "Tengerparti nyaralás", "Olaszország", new Vehicle("Repülõ"), 8, 2, new int[]{34, 34}));
        inputData.add(new InputDataV3("Exkluzív", "Tengerparti nyaralás", "Olaszország", new Car("Autó", 1, 2015), 7, 2, new int[]{34, 34}));
        inputData.add(new InputDataV3("Exkluzív", "Tengerparti nyaralás", "Horvátország", new Car("Autó", 1, 2015), 7, 3, new int[]{34, 34, 5}));
        inputData.add(new InputDataV3("Exkluzív", "Tengerparti nyaralás", "Horvátország", new Vehicle("Repülõ"), 8, 3, new int[]{34, 34, 5}));
        inputData.add(new InputDataV3("Exkluzív", "Tengerparti nyaralás", "Horvátország", new Vehicle("Vonat / Busz"), 7, 3, new int[]{34, 34, 5}));
        inputData.add(new InputDataV3("Exkluzív", "Tengerparti nyaralás", "Horvátország", new Car("Autó", 1, 2015), 8, 4, new int[]{34, 34, 5, 10}));
        inputData.add(new InputDataV3("Exkluzív", "Tengerparti nyaralás", "Horvátország", new Vehicle("Repülõ"), 8, 4, new int[]{34, 34, 5, 10}));
        inputData.add(new InputDataV3("Exkluzív", "Tengerparti nyaralás", "Horvátország", new Vehicle("Vonat / Busz"), 8, 4, new int[]{34, 34, 5, 10}));
        inputData.add(new InputDataV3("Exkluzív", "Városnézés", "Spanyolország", new Vehicle("Repülõ"), 5, 2, new int[]{34, 34}));
        inputData.add(new InputDataV3("Exkluzív", "Általános", "Ausztria", new Car("Autó", 1, 2009), 5, 2, new int[]{34, 34}));
        inputData.add(new InputDataV3("Exkluzív", "Tengerparti nyaralás", "Thaiföld", new Vehicle("Repülõ"), 8, 2, new int[]{34, 34}));
        inputData.add(new InputDataV3("Exkluzív", "Tengerparti nyaralás", "Thaiföld", new Vehicle("Repülõ"), 8, 3, new int[]{34, 34, 5}));
        inputData.add(new InputDataV3("Exkluzív", "Tengerparti nyaralás", "Thaiföld", new Vehicle("Repülõ"), 15, 2, new int[]{34, 34}));
        inputData.add(new InputDataV3("Exkluzív", "Általános", "Amerikai Egyesült Államok", new Vehicle("Repülõ"), 16, 2, new int[]{34, 34}));
        inputData.add(new InputDataV3("Exkluzív", "Általános", "Amerikai Egyesült Államok", new Vehicle("Repülõ"), 16, 3, new int[]{34, 34, 5}));
        //service level: optimal
        inputData.add(new InputDataV3("Optimális", "Tengerparti nyaralás", "Olaszország", new Vehicle("Vonat / Busz"), 7, 2, new int[]{34, 34}));
        inputData.add(new InputDataV3("Optimális", "Sport", "Ausztria", new Vehicle("Egyéb"), 3, 1, new int[] {34}));
        inputData.add(new InputDataV3("Optimális", "Tengerparti nyaralás", "Olaszország", new Vehicle("Repülõ"), 8, 2, new int[]{34, 34}));
        inputData.add(new InputDataV3("Optimális", "Tengerparti nyaralás", "Olaszország", new Car("Autó", 1, 2015), 7, 2, new int[]{34, 34}));
        inputData.add(new InputDataV3("Optimális", "Tengerparti nyaralás", "Horvátország", new Car("Autó", 1, 2015), 7, 3, new int[]{34, 34, 5}));
        inputData.add(new InputDataV3("Optimális", "Tengerparti nyaralás", "Horvátország", new Vehicle("Repülõ"), 8, 3, new int[]{34, 34, 5}));
        inputData.add(new InputDataV3("Optimális", "Tengerparti nyaralás", "Horvátország", new Vehicle("Vonat / Busz"), 7, 3, new int[]{34, 34, 5}));
        inputData.add(new InputDataV3("Optimális", "Tengerparti nyaralás", "Horvátország", new Car("Autó", 1, 2015), 8, 4, new int[]{34, 34, 5, 10}));
        inputData.add(new InputDataV3("Optimális", "Tengerparti nyaralás", "Horvátország", new Vehicle("Repülõ"), 8, 4, new int[]{34, 34, 5, 10}));
        inputData.add(new InputDataV3("Optimális", "Tengerparti nyaralás", "Horvátország", new Vehicle("Vonat / Busz"), 8, 4, new int[]{34, 34, 5, 10}));
        inputData.add(new InputDataV3("Optimális", "Városnézés", "Spanyolország", new Vehicle("Repülõ"), 5, 2, new int[]{34, 34}));
        inputData.add(new InputDataV3("Optimális", "Általános", "Ausztria", new Car("Autó", 1, 2009), 5, 2, new int[]{34, 34}));
        inputData.add(new InputDataV3("Optimális", "Tengerparti nyaralás", "Thaiföld", new Vehicle("Repülõ"), 8, 2, new int[]{34, 34}));
        inputData.add(new InputDataV3("Optimális", "Tengerparti nyaralás", "Thaiföld", new Vehicle("Repülõ"), 8, 3, new int[]{34, 34, 5}));
        inputData.add(new InputDataV3("Optimális", "Tengerparti nyaralás", "Thaiföld", new Vehicle("Repülõ"), 15, 2, new int[]{34, 34}));
        inputData.add(new InputDataV3("Optimális", "Általános", "Amerikai Egyesült Államok", new Vehicle("Repülõ"), 16, 2, new int[]{34, 34}));
        inputData.add(new InputDataV3("Optimális", "Általános", "Amerikai Egyesült Államok", new Vehicle("Repülõ"), 16, 3, new int[]{34, 34, 5}));
        //service level: basic
        inputData.add(new InputDataV3("Alapszintű", "Tengerparti nyaralás", "Olaszország", new Vehicle("Vonat / Busz"), 7, 2, new int[]{34, 34}));
        inputData.add(new InputDataV3("Alapszintű", "Sport", "Ausztria", new Vehicle("Egyéb"), 3, 1, new int[] {34}));
        inputData.add(new InputDataV3("Alapszintű", "Tengerparti nyaralás", "Olaszország", new Vehicle("Repülõ"), 8, 2, new int[]{34, 34}));
        inputData.add(new InputDataV3("Alapszintű", "Tengerparti nyaralás", "Olaszország", new Car("Autó", 1, 2015), 7, 2, new int[]{34, 34}));
        inputData.add(new InputDataV3("Alapszintű", "Tengerparti nyaralás", "Horvátország", new Car("Autó", 1, 2015), 7, 3, new int[]{34, 34, 5}));
        inputData.add(new InputDataV3("Alapszintű", "Tengerparti nyaralás", "Horvátország", new Vehicle("Repülõ"), 8, 3, new int[]{34, 34, 5}));
        inputData.add(new InputDataV3("Alapszintű", "Tengerparti nyaralás", "Horvátország", new Vehicle("Vonat / Busz"), 7, 3, new int[]{34, 34, 5}));
        inputData.add(new InputDataV3("Alapszintű", "Tengerparti nyaralás", "Horvátország", new Car("Autó", 1, 2015), 8, 4, new int[]{34, 34, 5, 10}));
        inputData.add(new InputDataV3("Alapszintű", "Tengerparti nyaralás", "Horvátország", new Vehicle("Repülõ"), 8, 4, new int[]{34, 34, 5, 10}));
        inputData.add(new InputDataV3("Alapszintű", "Tengerparti nyaralás", "Horvátország", new Vehicle("Vonat / Busz"), 8, 4, new int[]{34, 34, 5, 10}));
        inputData.add(new InputDataV3("Alapszintű", "Városnézés", "Spanyolország", new Vehicle("Repülõ"), 5, 2, new int[]{34, 34}));
        inputData.add(new InputDataV3("Alapszintű", "Általános", "Ausztria", new Car("Autó", 1, 2009), 5, 2, new int[]{34, 34}));
        inputData.add(new InputDataV3("Alapszintű", "Tengerparti nyaralás", "Thaiföld", new Vehicle("Repülõ"), 8, 2, new int[]{34, 34}));
        inputData.add(new InputDataV3("Alapszintű", "Tengerparti nyaralás", "Thaiföld", new Vehicle("Repülõ"), 8, 3, new int[]{34, 34, 5}));
        inputData.add(new InputDataV3("Alapszintű", "Tengerparti nyaralás", "Thaiföld", new Vehicle("Repülõ"), 15, 2, new int[]{34, 34}));
        inputData.add(new InputDataV3("Alapszintű", "Általános", "Amerikai Egyesült Államok", new Vehicle("Repülõ"), 16, 2, new int[]{34, 34}));
        inputData.add(new InputDataV3("Alapszintű", "Általános", "Amerikai Egyesült Államok", new Vehicle("Repülõ"), 16, 3, new int[]{34, 34, 5}));

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        Configuration.browser = "edge";
        Configuration.browserCapabilities = options;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;

        System.out.println("Headless method: " + Configuration.headless);
        System.out.println("Browser Capabilities: " + Configuration.browserCapabilities.toString());

        try {
            int counter = 1;
            for (InputDataV3 data : inputData) {
                System.out.println(" -- Round: " + counter);
                counter++;

                open("https://www.netrisk.hu/biztositas_dijszamitas/utasbiztositas/utasbiztositas.php");
                new LandingPageV3().populateOfferFormWithData(data);

                outputDatas.add(
                        new ResultPageV3(data).parseData());

                getWebDriver().quit();

            }
            new ExcelWriter().writeToExcelV3(inputData, outputDatas);
        } catch (Exception e) {
            throw e;
        }
    }

    public static void mainV2(String[] args) throws Exception {

//        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

        List<ResultDataV2> outputDatas = new ArrayList<>();
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
        Configuration.headless = false;

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
                new CookieHandler().acceptCookiesV2();
                new LandingPageV2(data).populateOfferFormWithData(data);

                outputDatas.add(
                        new ResultPageV2().parseData());

                getWebDriver().quit();

                if (!Configuration.headless)
                    for (String pI : JProcesses.getProcessList("msedge.exe").stream().map(ProcessInfo::getPid).collect(Collectors.toList())) {
                        if (!processesPidList.contains(pI)) {
                            JProcesses.killProcess(Integer.parseInt(pI));
                        }
                    }
            }
            new ExcelWriter().writeToExcelV2(inputData, outputDatas);
        } catch (Exception e) {
            if (!Configuration.headless)
                for (String pI : JProcesses.getProcessList("msedge.exe").stream().map(ProcessInfo::getPid).collect(Collectors.toList())) {
                    if (!processesPidList.contains(pI)) {
                        JProcesses.killProcess(Integer.parseInt(pI));
                    }
                }
            throw e;
        }
    }

    public static void main(String[] args) throws Exception {

//        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

        List<ResultData> outputDatas = new ArrayList<>();
        List<InputData> inputDatas = new ArrayList<>();
        inputDatas.add(new InputData("Chorvatsko", 7, Arrays.asList("42", "36", "17","15"), false));
        inputDatas.add(new InputData("Řecko", 14, Arrays.asList("42", "36", "15"), false));
        inputDatas.add(new InputData("Itálie", 14, Arrays.asList("33"), false));
        inputDatas.add(new InputData("Rakousko", 6, Arrays.asList("42", "36"), false));
        inputDatas.add(new InputData("Rakousko", 6, Arrays.asList("42", "36","7","5"), true));
        inputDatas.add(new InputData("Itálie", 3, Arrays.asList("33"), true));


        Configuration.browser = "edge";
        Configuration.headless = false;
        Configuration.browserSize = "1920x1920";
        EdgeOptions options = new EdgeOptions().addArguments("--remote-allow-origins=*");
        Configuration.browserCapabilities = options;

//        List<String> processesPidList = null;
//        if (!Configuration.headless)
//            processesPidList = JProcesses.getProcessList("msedge.exe").stream().map(ProcessInfo::getPid).collect(Collectors.toList());

        for (InputData iD : inputDatas) {
            try {
                System.out.println(" >>> Starting iteration for input data >>> " + iD.string());
                open("https://www.top-pojisteni.cz/cestovni-pojisteni/kalkulace-a-srovnani");

                new CookieHandler().acceptCookies();
                new LandingPage().basicSearch(iD);
                if (iD.isAddWinterSports()) new ResultPage().addWinterSports();
                outputDatas.add(
                        new ResultPage().parseData());

//                Thread.sleep(10000);
                closeWebDriver();
//                if (!Configuration.headless)
//                    for (String pI : JProcesses.getProcessList("msedge.exe").stream().map(ProcessInfo::getPid).collect(Collectors.toList())) {
//                        if (!processesPidList.contains(pI)) {
//                            JProcesses.killProcess(Integer.parseInt(pI));
//                        }
//                    }
            }catch (Exception e){
                closeWebDriver();
//                if (!Configuration.headless)
//                    for (String pI : JProcesses.getProcessList("msedge.exe").stream().map(ProcessInfo::getPid).collect(Collectors.toList())) {
//                        if (!processesPidList.contains(pI)) {
//                            JProcesses.killProcess(Integer.parseInt(pI));
//                        }
//                    }
                throw e;
            }
        }
        new ExcelWriter().writeToExcelV1(inputDatas, outputDatas);
    }
}
