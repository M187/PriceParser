package com.gw.v3;

import com.gw.v3.vehicles.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;

@Builder
@Getter
@AllArgsConstructor
public class InputDataV3 {

    public String
            serviceLevel,
            tripPurpose,
            destinationCountry;

    public Vehicle vehicle;

    public int
            tripDuration,
            noOfTravelers;


    public int[] datesOfBirth;

    public String getQuoteParameters(){
        StringBuilder rawData = new StringBuilder()
                .append(" -- Service level: " + serviceLevel)
                .append(" -- Purpose of trip: " + tripPurpose)
                .append(" -- Destination: " + destinationCountry)
                .append(" -- Travel mode / vehicle: " + vehicle.name)
                .append(" -- Duration in days: " + tripDuration)
                .append(" -- Travelers no.: " + noOfTravelers)
                .append(String.format(" -- travelers age:" + Arrays.toString(datesOfBirth), ","));
        return rawData.toString();
    }
}
