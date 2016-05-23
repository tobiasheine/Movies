Feature: Popular movies

  Scenario Outline: Show movie details for poster
    Given the following remote movie posters exist
      | movieId | posterPath   |
      | 100     | deadpool.jpg |
      | 200     | xmen.jpg     |
      | 300     | starwars.jpg |
    And the following remote movie details exist
      | movieId | posterPath   | title     | description            |
      | 100     | deadpool.jpg | Deadpool  | awesome movie          |
      | 200     | xmen.jpg     | X-Men     | wolverine rocks        |
      | 300     | starwars.jpg | Star Wars | may the force with you |
    When I start the application
    And I select the poster at position <pos>
    Then I expect to see the following movie details
      | movieId | posterPath | movieTitle | movieDetails |
      | <id>    | <path>     | <title>    | <details>    |

    Examples:
      | pos | id  | path         | title     | details                |
      | 0   | 100 | deadpool.jpg | Deadpool  | awesome movie          |
      | 1   | 200 | xmen.jpg     | X-Men     | wolverine rocks        |
      | 2   | 300 | starwars.jpg | Star Wars | may the force with you |
