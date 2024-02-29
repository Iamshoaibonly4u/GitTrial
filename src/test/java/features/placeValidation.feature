Feature: Validating the Place API's
@AddPlace @Regression
Scenario Outline: Verify if place is being succfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "addPlaceAPI" with "Post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify placeId created maps to "<name>" using "getPlaceAPI"
    
Examples:
    |      name			|language	|         address					|
    |Frontline house|Hindi    |29, side layout, cohen 09|
#    |White house|English    |22/4, B.Block,Dhatkidih|

@DeletePlace @Regression
Scenario: Verify Delete Place API functionality working fine
	  Given Delete Place Payload
	  When user calls "deletePlaceAPI" with "post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"