Feature: Popular movies

  @smoke
  Scenario: Show Movie Posters
    Given the following remote movie posters exist
      | movieId | posterPath   |
      | 100     | deadpool.jpg |
      | 200     | starwars.jpg |
      | 300     | xmen.jpg |
    When I start the application
    Then I expect to see the following movie posters
      | position | posterPath   |
      | 0        | deadpool.jpg |
      | 1        | starwars.jpg |
      | 2        | xmen.jpg |

  @foo
  Scenario: Foo
    Given the following remote movie posters exist
      | movieId | posterPath   |
      | 293660  | deadpool.jpg |
    And the following remote movie details exist
      | movieId | posterPath   | title    | description   |
      | 293660  | deadpool.jpg | Deadpool | awesome movie |
    When I start the application
    And I select the poster at position 0
    Then I expect to see the following movie details
      | movieId | posterPath   | movieTitle | movieDetails  |
      | 1       | deadpool.jpg | Deadpool   | awesome movie |


  @bar
  Scenario: Bar
    Given the following remote movie posters exist
      | movieId | posterPath   |
      | 293660  | deadpool.jpg |
    And the following remote movie details exist
      | movieId | posterPath   | title    | description   |
      | 293660  | deadpool.jpg | Deadpool | awesome movie |
    When I start the application
    And I select the poster at position 0
    Then I expect to see the following movie details
      | movieId | posterPath   | movieTitle | movieDetails  |
      | 1       | deadpool.jpg | Deadpool   | awesome movie |
