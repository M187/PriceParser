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
            tripPurpose;

    public int tripDuration;

    public String  noOfTravelers;

    public boolean allTravelersFromPoland;

    public String[] datesOfBirth;
}
