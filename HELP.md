# Intuit assignment

Rest Request:

* GET player By player ID: curl --location 'http://localhost:9999/intuit/assignment/v1/api/players/zinsebi01'
* GET player list by page and size: curl --location 'http://localhost:9999/intuit/assignment/v1/api/players?page=4&size=5'

### JAR file execute
* mvm clean install 
* java -jar .\assignment-0.0.1-SNAPSHOT.jar

Player list request Sort By:
* PLAYER_ID
* BIRTH_YEAR
* BIRTH_MONTH
* BIRTH_DAY
* BIRTH_COUNTRY
* BIRTH_STATE
* BIRTH_CITY
* DEATH_YEAR
* DEATH_MONTH
* DEATH_DAY
* DEATH_COUNTRY
* DEATH_STATE
* DEATH_CITY
* NAME_FIRST
* NAME_LAST
* NAME_GIVEN
* WEIGHT
* HEIGHT
* BATS
* THROWS
* DEBUT
* FINAL_GAME
* RETRO_ID
* BBREF_iD
