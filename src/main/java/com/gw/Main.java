package com.gw;

import com.codeborne.selenide.Configuration;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;

public class Main {

    public static void main(String[] args) throws Exception {

        List<ResultData> outputDatas = new ArrayList<>();
        List<InputData> inputDatas = new ArrayList<>();
        inputDatas.add(new InputData("Chorvatsko", 7, Arrays.asList("42", "36", "17","15")));
//        inputDatas.add(new InputData("Recko", 14, Arrays.asList("42", "36", "15")));
//        inputDatas.add(new InputData("Itálie", 14, Arrays.asList("33")));
//        inputDatas.add(new InputData("Rakousko", 6, Arrays.asList("42", "36")));


        Configuration.browser = "firefox";

        for (InputData iD : inputDatas) {

            open("https://www.top-pojisteni.cz/cestovni-pojisteni/kalkulace-a-srovnani");

            new CookieHandler().acceptCookies();
            new SearchPage().basicSearch(iD);
            outputDatas.add(
                    new ResultPage().parseData());

//            Thread.sleep(120000);
        }
        new ExcelWriter().writeToExcel(inputDatas, outputDatas);
    }
}
