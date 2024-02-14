Feature: Employee API Testing

  @api
  Scenario Outline: Calling Employee list API
    Given Employee list API "<BASE_URL>" is provided
    When User call Employee list "<ENDPOINT>" API
    Then Employee list "<RESPONSE_MESSAGE>" will be shown
    And Employee API returns response code 200
    Examples:
      |BASE_URL  |ENDPOINT|RESPONSE_MESSAGE|
      |https://dummy.restapiexample.com/api/v1|/employees|Successfully! All records has been fetched.|