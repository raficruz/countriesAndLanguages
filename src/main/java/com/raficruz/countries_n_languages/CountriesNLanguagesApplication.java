package com.raficruz.countries_n_languages;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

public class CountriesNLanguagesApplication {

	public static void main(String[] args) throws IOException {
		List<CountryData> countries;
		
		if( ArrayUtils.isNotEmpty(args) ) {
			countries = new Loader().loadCountries(args[0]);
		} else {
			countries = new Loader().loadCountries();
		}

		CountriesSummary summary = new CountriesSummary(countries);

		summary.getLanguageSummary(args.length == 2? args[1] : "alemao");
	}
}
