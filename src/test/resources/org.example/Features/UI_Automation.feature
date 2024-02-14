Feature: Login to an e-commerce website

  @ui
  Scenario Outline: Verify users can login to portal with valid credentials
    Given User visits e-commerce website
    When User enters valid "<username>" and "<password>"
    Then User can logged in successfully

    Examples:
      | username           | password |
      | testuser412@grr.la | 2t8zmqzL |