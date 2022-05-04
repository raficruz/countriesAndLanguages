package com.raficruz.countries_n_languages;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CountriesSummaryTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	List<CountryData> allCountriesScenario;
	List<CountryData> customScenario;
	List<CountryData> emptyWorldScenario;
	
	@Before
	public void init() throws IOException {
		System.setOut(new PrintStream(outContent));
		allCountriesScenario = new Loader().loadCountries();
		customScenario = new Loader().loadCountries("src/test/resources/countries.json");
		emptyWorldScenario = new Loader().loadCountries("src/test/resources/emptyWorld.json");
	}

	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	}

	@Test
	public void whenDefaultGetNumberOfCountriesInTheWorldHaveSuccess() {
		CountriesSummary summary = new CountriesSummary(allCountriesScenario);
		long countriesInTheWorld = summary.getNumberOfCountriesInTheWorld();
		
		assertEquals(228, countriesInTheWorld);
	}

	@Test
	public void whenGetNumberOfCountriesInCustomWorldHaveSuccess() {
		CountriesSummary summary = new CountriesSummary(customScenario);
		long countriesInTheWorld = summary.getNumberOfCountriesInTheWorld();
		
		assertEquals(5, countriesInTheWorld);
	}

	@Test
	public void whenGetNumberOfCountriesInAEmptyWorld() {
		CountriesSummary summary = new CountriesSummary(emptyWorldScenario);
		long countriesInTheWorld = summary.getNumberOfCountriesInTheWorld();

		assertEquals(0, countriesInTheWorld);
	}

	@Test
	public void whenDefaultGetNumberLanguagesInTheWorldHaveSuccess() {
		CountriesSummary summary = new CountriesSummary(allCountriesScenario);
		long languagesInTheWorld = summary.getNumberLanguagesInTheWorld();

		assertEquals(121, languagesInTheWorld);
	}

	@Test
	public void whenGetNumberLanguagesInTheWorldHaveSuccess() {
		CountriesSummary summary = new CountriesSummary(customScenario);
		long languagesInTheWorld = summary.getNumberLanguagesInTheWorld();

		assertEquals(6, languagesInTheWorld);
	}

	@Test
	public void whenDefaultGetNumberLanguagesInTheWorlInAEmptyWorld() {
		CountriesSummary summary = new CountriesSummary(emptyWorldScenario);
		long languagesInTheWorld = summary.getNumberLanguagesInTheWorld();

		assertEquals(0, languagesInTheWorld);
	}

	@Test
	public void whenGetCountryWithTheHighestNumberOfOfficialLanguagesAndSpeaksAlemao(){
		CountriesSummary summary = new CountriesSummary(allCountriesScenario);
		List<String> countries = summary.getCountryWithTheHighestNumberOfOfficialLanguagesAndSpeaks("alemao");

		assertEquals(3, countries.size());
		assertEquals(Arrays.asList(new String[] {"Austria", "Italia", "Suica"}), countries);
	}

	@Test
	public void whenGetCountryWithTheHighestNumberOfOfficialLanguagesAndSpeaksAny(){
		CountriesSummary summary = new CountriesSummary(allCountriesScenario);
		List<String> countries = summary.getCountryWithTheHighestNumberOfOfficialLanguagesAndSpeaks(null);

		assertEquals(1, countries.size());
		assertEquals(Arrays.asList(new String[] {"Africa do sul"}), countries);
	}

	@Test
	public void whenGetCountryWithTheHighestNumberOfOfficialLanguagesNoParameters(){
		CountriesSummary summary = new CountriesSummary(allCountriesScenario);
		List<String> countries = summary.getCountryWithTheHighestNumberOfOfficialLanguages();

		assertEquals(1, countries.size());
		assertEquals(Arrays.asList(new String[] {"Africa do sul"}), countries);
	}

	@Test
	public void whenGetCountryWithTheHighestNumberOfOfficialLanguagesAndSpeaksGerman(){
		CountriesSummary summary = new CountriesSummary(customScenario);
		List<String> countries = summary.getCountryWithTheHighestNumberOfOfficialLanguagesAndSpeaks("de");

		assertEquals(1, countries.size());
		assertEquals(Arrays.asList(new String[] {"BE"}), countries);
	}

	@Test
	public void whenGetCountryWithTheHighestNumberOfOfficialLanguagesAndSpeaksAlemaoInAEmptyWorld(){
		CountriesSummary summary = new CountriesSummary(emptyWorldScenario);
		List<String> countries = summary.getCountryWithTheHighestNumberOfOfficialLanguagesAndSpeaks("alemao");

		assertEquals(0, countries.size());
	}

	@Test
	public void getMostSpokenLanguageInTheWorld() {
		CountriesSummary summary = new CountriesSummary(allCountriesScenario);
		List<String> mostSpokenLanguages = summary.getMostSpokenLanguageInTheWorld();

		assertEquals(Arrays.asList(new String[] {"ingles"}), mostSpokenLanguages);
	}
	@Test
	public void getMostSpokenLanguageInACustomWorld() {
		CountriesSummary summary = new CountriesSummary(customScenario);
		List<String> mostSpokenLanguages = summary.getMostSpokenLanguageInTheWorld();
		assertEquals(Arrays.asList(new String[] {"de", "nl"}), mostSpokenLanguages);
	}
	@Test
	public void getMostSpokenLanguageInAnEmptyWorld() {
		CountriesSummary summary = new CountriesSummary(emptyWorldScenario);
		List<String> mostSpokenLanguages = summary.getMostSpokenLanguageInTheWorld();
		assertEquals(0, mostSpokenLanguages.size());
	}

	@Test
	public void getLanguageSummaryInTheWorld() {
		CountriesSummary summary = new CountriesSummary(allCountriesScenario);
		summary.getLanguageSummary("alemao");
		assertEquals(MessageFormat.format(CountriesSummary.getSummaryText(),
											"228",
											"alemao",
											"[Austria, Italia, Suica]",
											"121",
											"[Africa do sul]",
											"[ingles]"),
					outContent.toString().trim());
	}
	@Test
	public void getLanguageSummaryInACustomWorld() {
		CountriesSummary summary = new CountriesSummary(customScenario);
		summary.getLanguageSummary("de");
		assertEquals(MessageFormat.format(CountriesSummary.getSummaryText(),
											"5",
											"de",
											"[BE]",
											"6",
											"[BE]",
											"[de, nl]"),
					outContent.toString().trim());
	}

	@Test
	public void getLanguageSummaryInAnEmptyWorld() {
		CountriesSummary summary = new CountriesSummary(emptyWorldScenario);
		summary.getLanguageSummary("qualquer idioma");
		assertEquals(MessageFormat.format(CountriesSummary.getSummaryText(),
											"0",
											"qualquer idioma",
											"[]",
											"0",
											"[]",
											"[]"),
					outContent.toString().trim());
	}
}
