@counting
Feature: Counting posts


  Scenario: Counting posts for user5
    When I get a list of blog posts using the API endpoint
    Then user "5" should have "10" posts

  Scenario: Counting posts for user7
    When I get a list of blog posts using the API endpoint
    Then user "7" should have "10" posts

  Scenario: Counting posts for user9
    When I get a list of blog posts using the API endpoint
    Then user "9" should have "10" posts

