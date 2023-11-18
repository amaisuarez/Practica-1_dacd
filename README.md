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
LocationCSVParser reads a CSV file with geographical data, converting it into a list of Location objects. Parsing each line, it extracts information like name, latitude, and longitude, creating objects added to a list. This class aids in converting location data from a CSV file, supporting various program functionalities such as fetching weather data from OpenWeatherMap API.

+ **WeatherMapProvider**
WeatherMapProvider interacts with OpenWeatherMap API to fetch weather data for specified locations. Collaborating with APIWeatherFetcher, it obtains raw JSON responses and uses WeatherDataProcessor to convert data into a list of Weather objects associated with locations. This class facilitates the retrieval of weather information for multiple locations, integrating the data into the application.

+ **WeatherDataProcessor**
WeatherDataProcessor transforms raw JSON weather data from OpenWeatherMap API, extracting key information for 12:00 PM forecasted time points. It ensures data consistency, handling rain volume and setting defaults for missing information. The result is a list of Weather objects suitable for storage and application use.

+ **SQLController**
SQLController manages the creation of SQLite database tables, dynamically generating tables for each location from a provided list. Using JDBC, it establishes a connection, ensuring essential fields like date-time, temperature, and more are included. The class incorporates error handling for database interactions, allowing systematic storage of weather data for multiple locations in a well-structured SQLite database.

+ **APIWeatherFetcher**
APIWeatherFetcher fetches weather data from OpenWeatherMap API using provided coordinates and API key. It constructs the API request URL, sends a GET request, and retrieves the raw JSON response. The class converts this into a JSONObject and returns it, playing a key role in obtaining location-specific weather data from the API in the application's data retrieval process.


 **Model Classes**

+ **Weather**

Represents weather data with attributes such as temperature, precipitation, humidity, cloudiness, wind speed, description, and date-time. It includes a reference to the Location where the data was recorded.

+ **Location**

Represents geographical coordinates (latitude and longitude) and the name of a location. Instances of Location are used by Weather to associate weather data with specific places.


# Weather UML 

![Diagram](https://github.com/amaisuarez/Practica-1_dacd/blob/master/UMLFinal.png)
