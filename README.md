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

**Note:** the application will automatically creates the table for the user while statring of the application. The only thing user should update is the application properties which user can find in application.properties file, as given below.
					
          datasource.url=jdbc:mysql://localhost:3306/tfl #update the local or server database url
          datasource.username=root       #update database username
          datasource.password=root       #update database password


