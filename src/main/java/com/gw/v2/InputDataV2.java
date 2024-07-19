package com.gw.v2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class InputDataV2 {

    public String continent,
            destinationCountry,
            tripPurpose,
            tripDuration,
            noOfTravelers;

    public boolean allTravelersFromPoland;

    public String[] datesOfBirth;
}
