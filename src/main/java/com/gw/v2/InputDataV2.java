package com.gw.v2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Collectors;

@Builder
@Getter
@AllArgsConstructor
public class InputDataV2 {

    public String
            continent,
            destinationCountry,
            tripPurpose;

    public int tripDuration;

    public String  noOfTravelers;

    public boolean allTravelersFromPoland;

    public String[] datesOfBirth;

    public String getQuoteParameters(){
        StringBuilder rawData = new StringBuilder()
                .append("Continent(s) affected: " + continent)
                .append(" -- Countries affected: " + destinationCountry)
                .append(" -- Purpose of trip: " + tripPurpose)
                .append(" -- Duration in days: " + tripDuration)
                .append(" -- Travelers no.: " + noOfTravelers)
                .append(" -- Travelers from Poland?: " + allTravelersFromPoland)
                .append(String.format(" -- travelers age:" + Arrays.toString(datesOfBirth), ","));
        return rawData.toString();
    }
}
