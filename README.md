# Playing with Countries and languages using Streams
Imagine that you have data from all the countries in the world and the official languages spoken in each of them, in the format below:

```sh
[
  {
    "country": "Afeganistao",
    "languages": [
      "pasto",
      "persa"
    ]
  },
  {
    "country": "Africa do sul",
    "languages": [
      "africaner",
      "ingles",
      "ndebele",
      "xhosa",
      "zulu",
      "soto setentrional",
      "soto meridional",
      "tsuana",
      "suazi",
      "venda",
      "tsonga"
    ]
  },
  {
    "country": "Albania",
    "languages": [
      "albanes"
    ]
  },
  {
    "country": "Alemanha",
    "languages": [
      "alemao"
    ]
  },
  {
    "country": "Andorra",
    "languages": [
      "catalao"
    ]
  },
  {
    "country": "Angola",
    "languages": [
      "portugues"
    ]
  },
  {
    "country": "Anguilla",
    "languages": [
      "ingles"
    ]
  },
  {
    "country": "Antigua e barbuda",
    "languages": [
      "ingles"
    ]
  },
  {
    "country": "Antilhas holandesas",
    "languages": [
      "neerlandes"
    ]
  },
  {
    "country": "Arabia saudita",
    "languages": [
      "arabe"
    ]
  },
  {
    "country": "Argelia",
    "languages": [
      "arabe"
    ]
  },
  {
    "country": "Argentina",
    "languages": [
      "espanhol"
    ]
  },
  {
    "country": "Armenia",
    "languages": [
      "armenio"
    ]
  },
  {
    "country": "Aruba",
    "languages": [
      "neerlandes"
    ]
  },
  {
    "country": "Australia",
    "languages": [
      "ingles"
    ]
  }
  ...
]
```
What would you do to display the following information to the user:
- The number of countries in the world.
- The country with the most official languages, where they officially speak a specific language, like German.
- To Count all the official languages spoken in the set of countries.
- To find the Country with the highest number of official languages.
- To find the most common official language(s), of all countries.

This is an API that implements all this methods.
I provide You a Json file named countries.json. You can find it inside this project's root directory containing all countries and its spoken languages respectively. 
I wrote it in Portuguese. Why? I'm a Brazilian.
You can replace this file for another one in the same format, but in different language and everything will continues running well.
I implemented a method named getLanguageSummary in the class CountriesSummary that prints in console the response for all the initial questions. This method works just for display a fast summary, never print things on console!

## Installation and Usage

This API requires [Java 8+](https://www.oracle.com/br/java/technologies/javase/javase8-archive-downloads.html) and [Apache Maven](https://maven.apache.org/) to run.

Go to application directory.
Install the dependencies and build a fat Jar before running the application.

```sh
cd <application_directory>
mvn clean install
java -jar target/countries-and-languages-1.0-jar-with-dependencies.jar
```

Application will read my countries.json file and displays the following text:

```sh
Number of countries in the world is 228
The country(ies) with the most official languages, where they officially speak alemao is [Austria, Italia, Suica]
The total of all the official languages spoken in the listed countries is 121
The country with the highest number of official languages is [Africa do sul]
The most common official language(s), of all countries is(are) [ingles]
```

This jar can receive as args two parameters:
    - The name of a custom file you make like "my_set_of_countries.json".
    ***This file must be in the same directory where the jar is if you are runnig by shell commands or in the project's root directory if you runs it by some IDE***.
    - The name of the language that will be searched on line 2 of the summary like "de"


Imagine the Json file below as the content of your my_set_of_countries.json.
```sh
[
  {
    "country": "US",
    "languages": [
      "en"
    ]
  },
  {
    "country": "BE",
    "languages": [
      "nl",
      "fr",
      "de"
    ]
  },
  {
    "country": "NL",
    "languages": [
      "nl",
      "fy"
    ]
  },
  {
    "country": "DE",
    "languages": [
      "de"
    ]
  },
  {
    "country": "ES",
    "languages": [
      "es"
    ]
  }
]
```

Same format right?
Put this file in application's root directory and let's run it again

```sh
cd <application_directory>
java -jar target/countries-and-languages-1.0-jar-with-dependencies.jar my_set_of_countries.json de
```

The result is now

```
Number of countries in the world is 5
The country(ies) with the most official languages, where they officially speak de is [BE]
The total of all the official languages spoken in the listed countries is 6
The country with the highest number of official languages is [BE]
The most common official language(s), of all countries is(are) [de, nl]
```
## License

MIT

**Free Software, Yeah!**