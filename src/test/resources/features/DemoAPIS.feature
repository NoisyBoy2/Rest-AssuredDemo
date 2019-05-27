@Feature2
Feature: Demo for Rest assured Library using fake server of git 


@get
Scenario: Fetching the details of the user profile
	Given user will Fetch the profile details from url as "https://my-json-server.typicode.com/NoisyBoy2/Rest-AssuredDemo/profile"
	Then  user will verify the response code as "200" from fake server
	And   user profile name as "Ankush"  
	
@Post
Scenario: Creating new student details using post method
	Given user will post the student details resuest as 
	|{"id": 5,"name": "CR7","postId": 1,"score": 72}| 
	Then  user will verify the response code as "201" from fake server

@delete
Scenario: Deleting the comment using delete method
	Given user will delete the comment that has been added 
	Then  user will verify the response code as "200" from fake server



	 
    