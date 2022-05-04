package com.raficruz.countries_n_languages;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;

public class CountriesSummary {

	private static final String SUMMARY_TEXT = "Number of countries in the world is {0}\n" + 
					"The country(ies) with the most official languages, where they officially speak {1} is {2}\n" + 
					"The total of all the official languages spoken in the listed countries is {3}\n" + 
					"The country with the highest number of official languages is {4}\n" + 
					"The most common official language(s), of all countries is(are) {5}";
	
	private List<CountryData> countries;

	public CountriesSummary(List<CountryData> countries) {
		this.countries = ListUtils.emptyIfNull(countries);
	}

	public Long getNumberOfCountriesInTheWorld() {
		return this.countries.stream().count();
	}

	public Long getNumberLanguagesInTheWorld() {
		return this.countries.stream()
						.flatMap(c -> c.getLanguages().stream())
						.distinct()
						.count();
	}

	public List<String> getCountryWithTheHighestNumberOfOfficialLanguagesAndSpeaks(String language) {
		List<CountryData> countriesWhichSpeakesThisLanguage;

		if(StringUtils.isNotEmpty(language)) {
			countriesWhichSpeakesThisLanguage = this.countries.stream()
					.filter(c -> c.getLanguages().contains(language))
					.collect(Collectors.toList());
		} else {
			countriesWhichSpeakesThisLanguage = this.countries;
		}

		return getCountryWithTheHighestNumberOfOfficialLanguages(countriesWhichSpeakesThisLanguage);
	}
	
	public List<String> getCountryWithTheHighestNumberOfOfficialLanguages() {
		return getCountryWithTheHighestNumberOfOfficialLanguages(this.countries.stream().collect(Collectors.toList()));
	}

	private List<String> getCountryWithTheHighestNumberOfOfficialLanguages(List<CountryData> countries) {
		
		int maximumOccurrencesOfALanguage = 
				countries.stream()
					.mapToInt(CountryData::getNumberOfLanguages)
					.max()
					.orElse(-1);

		return countries.stream()
			.filter(c -> c.getLanguages().size() == maximumOccurrencesOfALanguage)
			.map(CountryData::getCountry)
			.collect(Collectors.toList());
	}

	public List<String> getMostSpokenLanguageInTheWorld() {
		Map<String, Long> occurrencesOfALanguage = 
			this.countries.stream()
					.flatMap(c -> c.getLanguages().stream())
					.collect( Collectors.groupingBy( Function.identity(), Collectors.counting()));

//		Long highestOccurrencesOfALanguage = 
//				occurrencesOfALanguage.entrySet().stream()
//									  .max(Map.Entry.comparingByValue())
//									  .get().getValue();

		Optional<Entry<String, Long>> optHighestOccurrencesOfALanguage = 
				occurrencesOfALanguage.entrySet().stream()
									  .max(Map.Entry.comparingByValue());
		
		Long highestOccurrencesOfALanguage = optHighestOccurrencesOfALanguage.isPresent()? 
												optHighestOccurrencesOfALanguage.get().getValue() : 
												-1L;

		return occurrencesOfALanguage.entrySet()
				.stream()
				.filter( c -> c.getValue().equals(highestOccurrencesOfALanguage))
				.map( Entry::getKey )
				.collect(Collectors.toList());
	}

	public static String getSummaryText() {
		return SUMMARY_TEXT;
	}

	public void getLanguageSummary(String language) {
		System.out.println(MessageFormat.format(SUMMARY_TEXT, 
												getNumberOfCountriesInTheWorld(),
												language,
												getCountryWithTheHighestNumberOfOfficialLanguagesAndSpeaks(language),
												getNumberLanguagesInTheWorld(),
												getCountryWithTheHighestNumberOfOfficialLanguagesAndSpeaks(null),
												getMostSpokenLanguageInTheWorld()));
	}
	
}
