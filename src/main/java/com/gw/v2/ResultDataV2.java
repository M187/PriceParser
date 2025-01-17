package com.gw.v2;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResultDataV2 {

	private List<String> companyName = new ArrayList<>();
	private List<String> productName = new ArrayList<>();
	private List<String> insurancePremium = new ArrayList<>();
	private List<String> medexSumInsured = new ArrayList<>();
	private List<String> oc = new ArrayList<>();
	private List<String> rate = new ArrayList<>();
	private List<String> otherCoverage = new ArrayList<>();

	public ResultDataV2(){
	}
}
