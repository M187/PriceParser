package com.gw;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class InputData {

	private String destination;
	private int durationInDyas;
	private List<String> travelersAge;


	public String string(){
		return new StringBuilder()
			.append("Destination: " + destination)
			.append(" -- Duration in days: " + durationInDyas)
			.append(" -- Travelers no.: " + travelersAge.size())
			.append(" -- travelers age:" + travelersAge.stream().collect(Collectors.joining(",", "{ ", " }")))
			.toString();
	}
}
