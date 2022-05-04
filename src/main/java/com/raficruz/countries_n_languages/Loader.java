package com.raficruz.countries_n_languages;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Loader {

	public List<CountryData> loadCountries(String filename) throws IOException {
		return new ObjectMapper()
				.readValue(
						new File(filename), 
						new TypeReference<List<CountryData>>(){}
				);
	}
	
	public List<CountryData> loadCountries() throws IOException {
		return new ObjectMapper()
				.readValue(
						new File("countries.json"), 
						new TypeReference<List<CountryData>>(){}
				);
	}
}
