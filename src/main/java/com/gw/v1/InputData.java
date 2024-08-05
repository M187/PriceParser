package com.gw.v1;

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
	private boolean addWinterSports;


	public String string(){
		 StringBuilder sB = new StringBuilder()
			.append("Destination: " + destination)
			.append(" -- Duration in days: " + durationInDyas)
			.append(" -- Travelers no.: " + travelersAge.size())
			.append(" -- travelers age:" + travelersAge.stream().collect(Collectors.joining(",", "{ ", " }")));
		 if (addWinterSports) sB.append(" -- Winter Sports package");
		return sB.toString();
	}
}
