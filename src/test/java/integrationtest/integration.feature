Feature: Test the prime number  rest service

  Background:
    * url "http://localhost:8080"

  Scenario: Success Scenario - returns all the prime numbers up to and including a number provided
    Given path '/primes/10'
    When method GET
    Then status 200
    * print 'Response:', response

  Scenario Outline: Validating the number param - fails at the validation
    Given path '/primes/<number>'
    When method GET
    Then status <responsecode>
    * print 'Response:', response
    And match response.errorMessage contains "<responsemessage>"

    Examples:
        | number   | responsecode   | responsemessage                 |
        | "siva"   |  400           |For input string: \"\"siva\"\"   |
        | null     |  400           |For input string: \"null\"       |
        | 9.0      |  400           |For input string: \"9.0\"        |

Scenario Outline: Validating the http method types
    Given path '/primes/10'
    When method <httptype>
    Then status 405
    * print 'Response:', response
    And match response.errorMessage contains "<responsemessage>"
    Examples:
        | httptype    | responsemessage                       |
        |   PUT       |Request method 'PUT' is not supported  |
        |   POST      |Request method 'POST' is not supported |
        |   PATCH     |Request method 'PATCH' is not supported|

   Scenario: Success Scenario - wait for 10 seconds before the cache expires
         Given path '/primes/10'
         When method GET
         Then status 200
         * def sleep = function(mills) {java.lang.Thread.sleep (mills)}
         * sleep(10000)
         Given path '/primes/10'
         Then status 200
         * print 'Response:', response