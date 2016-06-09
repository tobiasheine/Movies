Feature: Popular Movies

  @smoke
  Scenario: Show Movie Posters
    Given the following remote movie posters exist
      | movieId | posterPath   |
      | 100     | deadpool.jpg |
      | 200     | starwars.jpg |
      | 300     | xmen.jpg     |
    When I start the application
    Then I expect to see the following movie posters
      | position | posterPath   |
      | 0        | deadpool.jpg |
      | 1        | starwars.jpg |
      | 2        | xmen.jpg     |

