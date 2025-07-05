Feature: User Login

  Scenario: Successful login with valid credentials
    Given the user is on the login page
    When the user enters username "user1" and password "pass123"
    And clicks the login button
    Then the user should be redirected to the dashboard

  Scenario Outline: Unsuccessful login with invalid credentials
    Given the user is on the login page
    When the user enters username "<username>" and password "<password>"
    And clicks the login button
    Then an error message "<message>" should be displayed

    Examples:
      | username | password | message              |
      | wrong    | wrong123 | Invalid credentials  |
      | admin    | 123      | Invalid credentials  |

