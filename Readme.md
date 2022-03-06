# Dictionary Games

This repository contains different implementations of popular word based games in different languages.

## Project structure
The code is currently organized in three main modules:
* _dictionary_: This is where the different dictionary parsers are located.
* _domain_: Main domain classes for the projects such as games, exceptions, etc.
* _games_: Implementations of the different games of the project.

## Working with the code
The project uses [Apache Maven](https://maven.apache.org/index.html) as a project management and comprehension tool and
[Amazon Corretto 11](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/what-is-corretto-11.html) as JDK.

Plugins:
* [surefire - 2.22.2](https://maven.apache.org/surefire/maven-surefire-plugin/index.html): Run the JUnit unit tests in an isolated classloader.

### Executing tests
* Run all tests by executing `mvn surefire:test` or `mvn test`.
* Test a single class by executing `mvn -Dtest=<class name without exension> test`.
  * ex: `mvn -Dtest=TestHexLetters test`.
* More information on how to execute single tests [here](https://maven.apache.org/surefire/maven-surefire-plugin/examples/single-test.html).

### Dependencies
- [Jackson - 2.13.1](https://github.com/FasterXML/jackson).
- [Project Lombok - 1.18.22](https://projectlombok.org/).
- [log4j2 - 2.17.1](https://logging.apache.org/log4j/2.x/)
- [Sl4j - 2.17.1](https://www.slf4j.org/)
- [snakeyaml - 1.30](https://github.com/snakeyaml)
* Testing
  - [JUnit - 5.8.2](https://junit.org).
  - [assertj - 3.22.0](https://assertj.github.io/doc/)


## Current game implementations

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

## TODOs
- Parametrize variables in .properties file