# Pokemon Shakespearean description
Let's get your favourite Pokemon's description Shalesparean translation.

## Description
This API is to retrieve Pokemon description from Pokemon name. Written in Java and tested with tomcat local server.
Using below APIs to retrieve information:
* Poke API: https://pokeapi.co/
* Shakespeare translation API: https://funtranslations.com/shakespeare


## Getting Started
Install a local server such as tomcat: https://tomcat.apache.org/download-90.cgi
(For example, Tomcat 9)

Place the WAR file (Poke.war) in the webapps folder in the tomcat installtion folder

Start up the tomcat server.
* Mac: Go to the tomcat installtion folder (such as apache-tomcat-9.0.56)and execute below commands
```
> sudo chmod +x bin/*.sh
> bin/startup.sh
```
Go to the browser and go to the base URL with a pokemon name at the end. This example is calling "ditto"
* Base URL:  http://localhost:8080/Poke/pokemon/
* Example: http://localhost:8080/Poke/pokemon/ditto

The call will return results in JSON format like this:
```
{
"name": "ditto",
"description": "Capable of copying an foe's genetic code to instantly transform itself into a duplicate of the foe.",
}
```
### Dependencies
* Using Java libraries:
* javax.servlet-api-4.0.1.jar
* javax.json-api-1.1.4.jar
* httpclient-4.5.13.jar
* httpcore-4.4.13.jar
* json-20211205.jar
* commons-codec-1.11.jar
* commons-logging-1.2.jar


### Executing Test class
Includes test main class (TestMain.java)
Using this class, individual methods can be tested (have to edit the code to make the method public)
* String getDescription(String pokename)
* String getTranslation(String description)


## Authors
Junko Spagnuolo(junko.t.uk@gmail.com)


## License
This project is licensed under the Junko Spagnuolo License 



