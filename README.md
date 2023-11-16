## Practice 1 DACD

**Subject:** Development of Applications for Data Science 

**Course:** Second Year 

**Degree:** Data Science  

**School:** School of Engineering and Mathematics

**University:** University of Las Palmas de Gran Canaria

**Submitted by:** Amai Suárez Navarro 

# Functionality

This project focuses on the development of an application for retrieving and storing weather data 
from various locations.It utilizes the OpenWeatherMap API to gather information and then stores it in an SQLite database. 
The program is designed to run periodically, updating the data every 6 hours.

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

# Controller Classes

**Main**
The Main class serves as the entry point of the application. It initializes the SQLite database using SQLController, schedules weather data updates with WeatherScheduler, and inserts initial data using DataInserter. The main loop continuously fetches new weather data every 6 hours and inserts it into the database.

**DataInserter**
Responsible for inserting weather data into the SQLite database. It collaborates with the SQLController to interact with the database. The DataInserter class is called by Main to insert data obtained from the WeatherMapProvider.

**SQLController**
Handles the creation of the SQLite database. The createDatabase method uses JDBC to establish a connection, drop any existing tables, and create a new database table with specified fields.

**WeatherMapProvider**
Interacts with the OpenWeatherMap API to fetch weather data for specified locations. It processes location data from a CSV file, sends requests to the API, and returns a list of Weather objects.

# Model Classes

**Weather**
Represents weather data with attributes such as temperature, precipitation, humidity, cloudiness, wind speed, description, and date-time. It includes a reference to the Location where the data was recorded.

**Location**
Represents geographical coordinates (latitude and longitude) and the name of a location. Instances of Location are used by Weather to associate weather data with specific places.


