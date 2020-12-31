package rian.example.springcloudfunction.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryDetails {

	@JsonProperty
	private String Country;

	@JsonProperty
	private String CountryCode;

	@JsonProperty
	private String Province;

	@JsonProperty
	private String City;

	@JsonProperty
	private String CityCode;

	@JsonProperty
	private String Lat;

	@JsonProperty
	private String Lon;

	@JsonProperty
	private Integer Cases;

	@JsonProperty
	private String Status;

	@JsonProperty
	private String Date;

	@Override
	public String toString() {
		return "CountryDetails [Country=" + Country + ", CountryCode=" + CountryCode + ", Province=" + Province
				+ ", City=" + City + ", CityCode=" + CityCode + ", Lat=" + Lat + ", Lon=" + Lon + ", Cases=" + Cases
				+ ", Status=" + Status + ", Date=" + Date + "]";
	}

}
