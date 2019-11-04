# Android BDD using Espresso & Cucumber

## Motivation
https://www.novoda.com/blog/tests-are-the-best-documentation/

## Setup
1. Register and get an API-Token from https://api.themoviedb.org/
2. Create `secrets.properties` and include your token like this:
```java
API_KEY=YOUR_SECRET_KEY
```

## Run Plain Espresso Tests
`./gradlew connectedCheck`  

## Run Espresso Cucumber Tests
### Run all tests
`./gradlew connectedCheck -Pcucumber`  
### Run tests by tag
`./gradlew connectedCheck -Pcucumber -Ptags="@smoke"`  
### Run tests by scenario name
`./gradlew connectedCheck -Pcucumber -Pscenario="Show movie details for a single poster"`
