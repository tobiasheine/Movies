# Cucumber + Android Kata

---

## Goal
Get your hands dirty on Cucumber + Android!

## Setup
Install the [Gherkin](https://plugins.jetbrains.com/androidstudio/plugin/7211-gherkin) and Cucumber for Java Plugin in Android Studio.

Clone **git@github.com:tobiasheine/Movies.git** and checkout the branch **kata**.

Create a file called **secrets.properties** in the root directory and add the **API-Key** you have received in advance.

---

## Exercise

Your Android team was working hard to create an application which displays the latest movies from [themoviedb](https://www.themoviedb.org/documentation/api).

To improve the internal feature documentation they want to migrate their existing Espresso test suite to Cucumber scenarios. The team has done an awesome job so far by decoupling most of the test code from the tests by introducing the testing robots **PopularMoviesRobot** and **PosterDetailsRobot**.

### Display the movie release date besides the description

Create a scenario that verifies that the **releaseDate** of a given movie is displayed in the **MovieDetailsActivity**. 

Add a scenario and step definitions to **movie_details.feature**. The java implementations of the steps should go to **MovieDetailsSteps**. 

The first step should use **PosterDetailsRobot#launchDetailsScreen** to start the **MovieDetailsActivity** with a given **movieId**:
```When I show the detail screen for movie with id 1```


You can run your test scenario using the command-line:

```./gradlew connectedCheck -Pcucumber --info```

*Tips*

- Have a look at the [Cucumber Wiki](https://github.com/cucumber/cucumber/wiki/Feature-Introduction) if you're not cure how a Scenario is structured

- use autocompletion when editing the **feature files**

- [here](http://blog.czeczotka.com/2014/08/17/writing-cucumber-jvm-step-definitions/) you can find some examples how to pass arguments to your step definitions

Next fix the failing test by displaying the **MovieDetails#releaseDate** in the **MovieDetailsActivity**. Implement the feature and add the needed code to the **PosterDetailsRobot** to make the test pass.

### Implement the same test without Cucumber

Reuse the code you have added to the **PosterDetailsRobot** in a regular Espresso test. Add this test to **MovieDetailsActivityTest** and see how easily you can share the code between Cucumber and Espresso tests!

### Migrate PopularMoviesActivityTest#shouldShowPoster into a cucumber scenario.

Create a new **feature file** with the name **movie_stream.feature** and a scenario that makes sure the poster of a given movie is displayed. Use **PopularMoviesRobot** in your steps implementations. The step implementations should go to **PopularMoviesSteps**.

*Tips*

- Use [DataTables](http://www.thinkcode.se/blog/2014/06/30/cucumber-data-tables) to pass the DTO's as step arguments as it is done in the **Background** section of **movie_details.feature**

- The assets for movie posters are located in **main/assets**. You can reference them by file name, as it is done in **PopularMoviesActivityTest.POSTER_PATH**

- You can annotate your scenario or the whole feature, for example with **@details** to just run this one using the command-line:

	```./gradlew connectedCheck -Pcucumber -Ptags="@details"```



### Create Scenario Outlines 
If there is still time left, change the test you have just written in order to verify that multiple movies are shown in the correct order using [Scenario Outlines](https://github.com/cucumber/cucumber/wiki/Scenario-Outlines).

Ex. Deadpool at position one and X-MEN at position two.
