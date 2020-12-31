package rian.example.springcloudfunction.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Country {

	private String name;
	private List<CountryDetails> countryDetails;

}
