Feature: Theatre Booking Acceptance

  Scenario: User checks for only one audience
       Given The valid layout is provided
  	   When I enter the valid request  with 1 user
   	   Then I test the output of the algorithm
   	   
  Scenario: User checks for only one audience with large number of tickets i.e "can't handle the party"
       Given The valid layout is provided
  	   When I enter the request  with more number of tickets than available in theatre
   	   Then I test the output of the algorithm for can't handle the party	
   	   
  Scenario: User checks for only one audience in which number of ticket requires splitting i.e "Call to split party"
       Given The valid layout is provided
  	   When I enter the request  with more number of tickets than available in one section
   	   Then I test the output of the algorithm for Call to split party   
   	 
  Scenario: User checks if front row is occupied when audience are lesser
       Given The valid big layout is provided
  	   When I enter the request  with less number of tickets
   	   Then I test the output of the algorithm for seating arrangment    
   	   
   Scenario: User checks if most orders are processed
       Given The valid big layout is provided
  	   When I enter the request  with more number of tickets
   	   Then I test the output of the algorithm for number of seats allocated  