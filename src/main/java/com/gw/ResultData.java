package com.gw;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResultData {

	private List<String> companyName = new ArrayList<>();
	private List<String> insurancePrice = new ArrayList<>();
	private List<String> liecebneVylohy = new ArrayList<>();
	private List<String> urazovePojisteni = new ArrayList<>();
	private List<String> pojistOdpovednosti = new ArrayList<>();

	public ResultData(){
		this.companyName.add("Poistovna");
		this.insurancePrice.add("Cena poistenia");
//		this.liecebneVylohy.add("Liecebne bylohy");
//		this.urazovePojisteni.add("Urazove poistenie");
//		this.pojistOdpovednosti.add("Pojisteni Odpovednosti");
	}
}
