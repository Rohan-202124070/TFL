This application developed using Spring Boot and basic HTML, CSS, and JavaScript.

The application basically has two features.

1. TFL Service List: This feature will be displaying the available Transport for London Bus services using open API URL https://api.tfl.gov.uk/StopPoint/490009333W/arrivals  

2. History of API calls: In this feature user able to see the history of TFL API calls along with few search parameters.


***Instructions to use it:***

         1. Clone the project to your local system.
         2. Setup the cloned project into your suitable IDE.
         3. Clean, Build, and run the project as Spring Boot application.
         4. Open the prefered browser and use http://localhost:8080/tfl to see the output web pages.
         5. To navigate to the history of TFL API calls page click the History of API calls just next to TFL Service tab.


***About the Database:***

Application uses the MYSQL database with single table which is TFL.

The table can be created using the avaliable sql script file in the repo named TFL.sql, copy the intire script and paste it in MYSQL workbench and run it. 
User also should update is the application properties which user can find in application.properties file, as given below.
					
          datasource.url=jdbc:mysql://localhost:3306/tfl #update the local or server database url
          datasource.username=root       #update database username
          datasource.password=root       #update database password

***Future work:***

         1. The user interface presents as very basic, so it has to be updated and make more user interactive interface.
         2. Authentication and validation must be provided to the api calls.
         3. The validation must be implemented for the search fields in the History of API calls pages.
         4. The pagination must be implemented for the History of API calls pages.
         5. SpringBoot application can be updated using Hibernate and JPA for database interactions.
         6. Maybe the dao layer can be written in more optimized way than existing one.
         7. JUint test cases must be writen for the application.
