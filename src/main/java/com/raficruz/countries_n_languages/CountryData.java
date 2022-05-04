package com.raficruz.countries_n_languages;

import java.util.List;

import org.apache.commons.collections4.ListUtils;

public class CountryData {

	private String country;
	private List<String> languages;

	public String getCountry() {
		return country;
	}

	public List<String> getLanguages() {
		return languages;
	}

	public int getNumberOfLanguages() {
		return ListUtils.emptyIfNull(languages).size();
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setLanguages(List<String> languages) {
		this.languages = ListUtils.emptyIfNull(languages);
	}

}
