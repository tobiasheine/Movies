Feature: Movie Details

  Background:
    Given the following remote movie posters exist
      | movieId | posterPath   |
      | 100     | deadpool.jpg |
      | 200     | xmen.jpg     |
      | 300     | starwars.jpg |
    And the following remote movie details exist
      | movieId | posterPath   | title     | description               | releaseDate |
      | 100     | deadpool.jpg | Deadpool  | awesome movie             | 2000-01-01  |
      | 200     | xmen.jpg     | X-Men     | wolverine rocks           | 2010-05-03  |
      | 300     | starwars.jpg | Star Wars | may the force be with you | 2008-03-04  |

  @test
  Scenario: Show movie details for a single poster
    Given I start the application
    When I select the poster at position 0
    Then I expect to see the following movie details
      | movieTitle | movieDetails  |
      | Deadpool   | awesome movie |

  Scenario: Show movie details for a single poster from details screen
    Given I start the details screen with movie id 100
    Then I expect to see the following movie details
      | movieTitle | movieDetails  |
      | Deadpool   | awesome movie |

  Scenario Outline: Show movie details for all posters
    Given I start the application
    When I select the poster at position <pos>
    Then I expect to see the following movie details
      | movieTitle | movieDetails |
      | <title>    | <details>    |

    Examples:
      | pos | title     | details                   |
      | 0   | Deadpool  | awesome movie             |
      | 1   | X-Men     | wolverine rocks           |
      | 2   | Star Wars | may the force be with you |
