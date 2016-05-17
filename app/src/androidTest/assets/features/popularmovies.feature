Feature: Popular movies

  Scenario: Select a poster and show movie details

    Given the following remote movie posters exist
      | movieId | posterPath   |
      | 1       | deadpool.jpg |

    When I select the poster at position 2

    Then I expect to see the following movie details
      | movieId | posterPath   | movieTitle | movieDetails  |
      | 1       | deadpool.jpg | Deadpool   | awesome movie |
