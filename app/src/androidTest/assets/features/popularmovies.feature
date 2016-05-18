Feature: Popular movies

  Scenario: Select a poster and show movie details

    Given the following remote movie posters exist
      | movieId | posterPath    |
      | 293660  | /deadpool.jpg |

    And the following remote movie details exist
      | movieId | posterPath    | title    | description   |
      | 293660  | /deadpool.jpg | Deadpool | awesome movie |


    When I start the application

    When I select the poster at position 0

    Then I expect to see the following movie details
      | movieId | posterPath   | movieTitle | movieDetails  |
      | 1       | deadpool.jpg | Deadpool   | awesome movie |
