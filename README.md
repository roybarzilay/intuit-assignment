# intuit-assignment

## Overview
The following repo contains examples for OpenFin's Java adapter.
using JAVA 17

## Guidelines
assignment retrieve player data MS 

1. Clone this repository

2. Go to release directory and execute mvn build install

3. go to target directory and execute: java -jar .\assignment-0.0.1-SNAPSHOT.jar

4. Once the application is running you can use one of the APIs:
  - GET player By player ID: curl --location 'http://localhost:9999/intuit/assignment/v1/api/players/zinsebi01'
  - GET player list by page and size: curl --location 'http://localhost:9999/intuit/assignment/v1/api/players?page=4&size=5'

## More Info
More information and API documentation can be found at https://openfin.co/java-api/

## Support
Please enter an issue in the repo for any questions or problems. 
<br> Alternatively, please contact us at barzilay.roy@gmail.com
