# Dictionary Games

This repository contains different implementations of popular word based games in different languages.

## Current implementations

### _**HexLetters**_

Game clone of NY Times' [Spelling Bee](https://www.nytimes.com/puzzles/spelling-bee) and [Paraulògic](https://vilaweb.cat/paraulogic/).
  + Languages available: 
    + US English based on [Webster's Unabridged Dictionary](https://unabridged.merriam-webster.com/).
    + JSON version of the dictionary from [here](https://github.com/matthewreagan/WebstersEnglishDictionary).

#### Gameplay
Find all the possible words with a 7 letter combination (2 vowels and 5 consonants or 3 vowels and 4 consonants).

Rules:
* Words need to have more than 3 letters.
* Words must contain the central letter (or main letter) of the puzzle.

## Dependencies
- [JUnit](https://junit.org).
- [Jackson](https://github.com/FasterXML/jackson).
- [Project Lombok](https://projectlombok.org/).
- [log4j2](https://logging.apache.org/log4j/2.x/)
- [Sl4j](https://www.slf4j.org/)

## TODOs
- Parametrize variables in .properties file