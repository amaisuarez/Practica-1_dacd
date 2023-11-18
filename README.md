## Practice 1 DACD

**Subject:** Development of Applications for Data Science 

**Course:** Second Year 

**Degree:** Data Science  

**School:** School of Engineering and Mathematics

**University:** University of Las Palmas de Gran Canaria

**Submitted by:** Amai Suárez Navarro 

# Functionality
This project focuses on the development of an application for retrieving and storing weather data from various locations. It utilizes the OpenWeatherMap API to gather information and then stores it in an SQLite database. The program is designed to run periodically, updating the data every 6 hours. Additionally, the application provides the capability to visualize weather data in separate tables for each island. Each table is dedicated to a specific location, allowing users to easily access and analyze weather information specific to individual islands.


# Resources Used

**Development Environment-**
IDE (Integrated Development Environment): IntelliJ IDEA
Programming Language: Java
Database: SQLite

**Version Control-**
Version Control System: Git
Hosting: GitHub

**Documentation-**
README Format: Markdown
Documentation: Self-documenting code and inline comments

**APIs-**
Weather Data: OpenWeatherMap API

**Build and Dependency Management-**
Build Tool: Maven
Some dependencies have been added to the pom.xml file. You can find the details in the project's Maven configuration file.

# Design Overview

**Controller Classes**

+ **Main**
The Main class serves as the entry point of the application. It initializes the SQLite database using SQLController, schedules weather data updates with WeatherScheduler, and inserts initial data using DataInserter. The main loop continuously fetches new weather data every 6 hours and inserts it into the database.

+ **DataInserter**
Responsible for inserting weather data into the SQLite database. It collaborates with the SQLController to interact with the database. The DataInserter class is called by Main to insert data obtained from the WeatherMapProvider.

+ **LocationCSVParser**
The LocationCSVParser class reads a CSV file containing geographical location information and converts the data into a list of Location objects. It parses each line of the CSV, extracts relevant information such as name, latitude, and longitude, and creates Location objects, which are then added to a list. This class facilitates the conversion of location data from a CSV file for use in other parts of the program, such as fetching weather data from the OpenWeatherMap API.

+ **WeatherMapProvider**
The WeatherMapProvider class is responsible for interacting with the OpenWeatherMap API to fetch weather data for specified locations. It collaborates with the APIWeatherFetcher to obtain raw JSON responses from the API and utilizes the WeatherDataProcessor to convert the JSON data into a list of Weather objects associated with specific locations. This class enables the retrieval of weather information for multiple locations by processing the API responses and facilitating the integration of weather data into the application.

+ **WeatherDataProcessor**
The WeatherDataProcessor class is responsible for transforming raw JSON weather data from the OpenWeatherMap API. It extracts key information such as temperature, precipitation, humidity, cloudiness, wind speed, and description for specific forecasted time points, specifically those at 12:00 PM. The class ensures data consistency by handling cases where rain volume data is present and sets default values when certain information is missing in the API response. Ultimately, WeatherDataProcessor converts the raw API data into a list of Weather objects, making it suitable for storage and subsequent use within the application

+ **SQLController**
The SQLController class oversees the creation of SQLite database tables, dynamically generating tables for each location based on the provided list. Utilizing JDBC, it establishes a connection to the database and ensures that each table includes essential fields such as date-time, temperature, description, cloudiness, wind speed, precipitation, and humidity. The class incorporates error handling for potential exceptions during database interactions. By facilitating the creation of tables for different locations, the SQLController enables the application to systematically store weather data for multiple geographic points in a well-structured manner within an SQLite database.

+ **APIWeatherFetcher**
The APIWeatherFetcher class is responsible for fetching weather data from the OpenWeatherMap API based on the provided geographical coordinates (latitude and longitude) and API key. It constructs the API request URL, sends a GET request to the API, and retrieves the raw JSON response. The class then converts this JSON response into a JSONObject and returns it. In essence, APIWeatherFetcher facilitates the communication with the OpenWeatherMap API to obtain weather data for a specific location, forming a crucial component in the data retrieval process of the application.



 **Model Classes**

+ **Weather**

Represents weather data with attributes such as temperature, precipitation, humidity, cloudiness, wind speed, description, and date-time. It includes a reference to the Location where the data was recorded.

+ **Location**

Represents geographical coordinates (latitude and longitude) and the name of a location. Instances of Location are used by Weather to associate weather data with specific places.


# Weather UML 

![Diagram](https://github.com/amaisuarez/Practica-1_dacd/blob/master/UMLFinal.png)
