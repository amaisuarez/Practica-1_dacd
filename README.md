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

**Controller Classes*

**Main**
The Main class serves as the entry point of the application. It initializes the SQLite database using SQLController, schedules weather data updates with WeatherScheduler, and inserts initial data using DataInserter. The main loop continuously fetches new weather data every 6 hours and inserts it into the database.

**DataInserter**
Responsible for inserting weather data into the SQLite database. It collaborates with the SQLController to interact with the database. The DataInserter class is called by Main to insert data obtained from the WeatherMapProvider.

**LocationCSVParser**
The LocationCSVParser class reads a CSV file containing geographical location information and converts the data into a list of Location objects. It parses each line of the CSV, extracts relevant information such as name, latitude, and longitude, and creates Location objects, which are then added to a list. This class facilitates the conversion of location data from a CSV file for use in other parts of the program, such as fetching weather data from the OpenWeatherMap API.

**WeatherMapProvider**
The WeatherMapProvider class is responsible for interacting with the OpenWeatherMap API to fetch weather data for specified locations. It collaborates with the APIWeatherFetcher to obtain raw JSON responses from the API and utilizes the WeatherDataProcessor to convert the JSON data into a list of Weather objects associated with specific locations. This class enables the retrieval of weather information for multiple locations by processing the API responses and facilitating the integration of weather data into the application.

**WeatherDataProcessor**
The WeatherDataProcessor class processes raw JSON weather data obtained from the OpenWeatherMap API. It extracts relevant information such as temperature, precipitation, humidity, cloudiness, wind speed, and description for each forecasted time point. The class filters the data to focus on forecasts for 12:00 PM and creates corresponding Weather objects for these time points. Additionally, it handles cases where rain volume data is available and provides appropriate default values if certain information is not present in the API response. Overall, WeatherDataProcessor transforms the raw API response into a list of Weather objects suitable for storage and further use within the application.

**SQLController**
The SQLController class manages the creation of SQLite database tables based on the provided list of locations. It uses JDBC to establish a connection to the database and dynamically generates tables for each location, ensuring that each table has fields for date-time, temperature, description, cloudiness, wind speed, precipitation, and humidity. The class handles potential exceptions during the database interaction and provides a mechanism to create tables for different locations, allowing the application to store weather data for multiple geographic points in a structured manner within an SQLite database.

**APIWeatherFetcher**
The APIWeatherFetcher class is responsible for fetching weather data from the OpenWeatherMap API based on the provided geographical coordinates (latitude and longitude) and API key. It constructs the API request URL, sends a GET request to the API, and retrieves the raw JSON response. The class then converts this JSON response into a JSONObject and returns it. In essence, APIWeatherFetcher facilitates the communication with the OpenWeatherMap API to obtain weather data for a specific location, forming a crucial component in the data retrieval process of the application.



 **Model Classes*

**Weather**

Represents weather data with attributes such as temperature, precipitation, humidity, cloudiness, wind speed, description, and date-time. It includes a reference to the Location where the data was recorded.

**Location**

Represents geographical coordinates (latitude and longitude) and the name of a location. Instances of Location are used by Weather to associate weather data with specific places.


# Weather UML 

![Diagram](https://github.com/amaisuarez/Practica-1_dacd/blob/master/UMLFinal.png)
