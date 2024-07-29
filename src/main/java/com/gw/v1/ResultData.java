package com.gw.v1;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResultData {

	private List<String> companyName = new ArrayList<>();
	private List<String> insurancePremium = new ArrayList<>();
	private List<String> medexSumInsured = new ArrayList<>();
	private List<String> oc = new ArrayList<>();
	private List<String> rate = new ArrayList<>();

	public ResultData(){
//		this.companyName.add("Poistovna");
//		this.insurancePremium.add("Cena poistenia");
//		this.liecebneVylohy.add("Liecebne bylohy");
//		this.urazovePojisteni.add("Urazove poistenie");
//		this.pojistOdpovednosti.add("Pojisteni Odpovednosti");
	}
}
