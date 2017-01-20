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

Add a scenario and step definitions to **movie_details.feature**. The java implementation of the steps should go to **MovieDetailsSteps**. Leave the step implementation empty so the test fails. For more information have a look at the [Cucumber Wiki](https://github.com/cucumber/cucumber/wiki/Feature-Introduction).

You can run your test scenario using the command-line:

```./gradlew connectedCheck -Pcucumber --info```

*Tips*

- use autocompletion when editing the **feature files**
- You can annotate your scenario or the whole feature, for example with **@details** and just run this using the command-line:

	```./gradlew connectedCheck -Pcucumber -Ptags="@details"```

Next fix our failing test by displaying the **MovieDetails#releaseDate** in the **MovieDetailsActivity**. Therefore you need to add some code to the **PosterDetailsRobot** and use it in the step definitions you have just created.

### Implement the same test using Espresso
Reuse the code you have added to the **PosterDetailsRobot** in a regular Espresso test. Add this test to **MovieDetailsActivityTest** and see how easily you can share the code between Cucumber and Espresso tests!

### Migrate PopularMoviesActivityTest#shouldShowPoster into a cucumber scenario.

*Tips*

- Use [DataTables](http://www.thinkcode.se/blog/2014/06/30/cucumber-data-tables) to pass the DTO's as step arguments as it is done in the **Background** section of **movie_details.feature**

- The assets for movie posters are located in **main/assets**. You can reference them by file name, as it is done in the **PopularMoviesActivityTest**

### Create Scenario Outlines 
If there is still time left, change the test you have just written in order to verify that multiple movies are shown in the correct order using [Scenario Outlines](https://github.com/cucumber/cucumber/wiki/Scenario-Outlines).

Ex. Deadpool at position one and X-MEN at position two.
