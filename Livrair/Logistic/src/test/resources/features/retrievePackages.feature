Feature: Retrieve Packages

  Background:
    Given 2 packages from the supplier "SuppTest"

    Scenario: Get registered packages
      When Marcel asks for the list of packages
      Then there are 2 packages in the list