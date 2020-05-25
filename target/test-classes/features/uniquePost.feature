@unique
Feature: Each blog should have a unique ID


  Scenario: Unique ID per post
    When I get a list of blog posts using the API endpoint
    Then Each blog post should have a unique ID