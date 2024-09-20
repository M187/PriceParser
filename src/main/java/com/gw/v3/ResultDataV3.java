package com.gw.v3;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResultDataV3 {

	private List<String> serviceLevel = new ArrayList<>();
	private List<String> companyName = new ArrayList<>();
	private List<String> productName = new ArrayList<>();
	private List<String> insurancePremium = new ArrayList<>();
	private List<String> CustomerReview = new ArrayList<>();
	private List<String> netriskReview = new ArrayList<>();
	private List<String> carAssistance = new ArrayList<>();
	private List<String> medexSumInsured = new ArrayList<>();
	private List<String> luggage = new ArrayList<>();
	private List<String> gadget = new ArrayList<>();

	public ResultDataV3(){
//		this.companyName.add("Poistovna");
//		this.insurancePremium.add("Cena poistenia");
//		this.liecebneVylohy.add("Liecebne bylohy");
//		this.urazovePojisteni.add("Urazove poistenie");
//		this.pojistOdpovednosti.add("Pojisteni Odpovednosti");
	}
}
