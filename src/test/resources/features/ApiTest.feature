@test
Feature: Demo for Rest assured Library 

@Post
Scenario: Creating blob using post method
	Given user will post the resuest as 
	|["bill","steve","bob"]| 
	Then  user will verify the response code as "201"
	And Content type as "application/json; charset=utf-8"
@get
Scenario: Updating the details of the user Blob
	Given user will put the resuest as 
	|[""fred","mark","andrew"]| 
	Then  user will verify the profile details of the user been updated to "andrew"
		 
@delete
Scenario: Deleting the blobid using delete method
	Given user will delete the blobid that has been added 
	Then  user will verify the response code as "200"
	And   verify user profile has been deleted
	 
    