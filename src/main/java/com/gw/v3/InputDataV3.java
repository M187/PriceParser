package com.gw.v3;

import com.gw.v3.vehicles.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    public Map<String, String> getQuoteParameters() {
        Map<String, String> quoteParameters = new HashMap<>();

        quoteParameters.put("service level", serviceLevel);
        quoteParameters.put("Purpose of trip", tripPurpose);
        quoteParameters.put("destination", destinationCountry);
        quoteParameters.put("Travel mode / vehicle", vehicle.name);
        quoteParameters.put("Duration in days", Integer.toString(tripDuration));
        quoteParameters.put("Number of travelers", Integer.toString(noOfTravelers));
        quoteParameters.put("Travelers' age", Arrays.toString(datesOfBirth));

        return quoteParameters;
    }
}
