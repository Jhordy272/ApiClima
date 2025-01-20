# ApiClima Readme
This README file provides an overview of ApiClima, which allows querying weather and pollution data for specific cities.

## Description
ApiClima provides endpoints to query information about the weather and pollution for specific cities. It uses the Nominatim OpenStreetMap API to obtain the coordinates of the queried city, the OpenWeatherMap API to get weather and air quality data, and logs all queries in an audit database.

## Features
- Provides endpoints to get current weather, 5-day forecast, and air quality data for a specific city.
- Uses the Nominatim OpenStreetMap API to retrieve coordinate data.
- Uses the OpenWeatherMap API to retrieve weather and pollution data.
- Implements JWT security for authorization.
- Uses a caching mechanism to improve the performance of repeated queries.
- Logs all queries in an audit database.

## Technologies Used
- Java
- Spring Boot
- Swagger for API documentation
- OpenWeatherMap API
- Nominatim OpenStreetMap API
- JSON Web Tokens (JWT) for authentication
- Bucket4j for rate limiting
- Jackson's ObjectMapper for JSON processing
- Mockito for unit testing

## Endpoints
- **GET /login**: Gets the valid token for making queries.
    - **Request example**:
    ```json
    {
        "email": "test@mail.com",
        "password": "123456"
    }
    ```
    - **Expected response**: Returns a header with the bearer token.
    - **Response header**:
    ```
    authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcnVlYmFAbWFpbC5jb20iLCJleHAiOj
    E3MTA1MTIxMTAsIm5vbWJyZSI6InBydWViYSJ9.qUbWbZOWpAUrq1u7-xRUBo0lnZANIYkuotXI3yIMCUQrhYZLKwmurh0eNgI6zQ1ug62VgMxlw1HN-r98H4TmHw
    ```

- **GET /consultas/clima/{name}**: Gets current weather data for a specific city.
    - **Expected response**: Returns a JSON object with current weather data for the specified city.
    - **Response example**:
    ```json
    {
        "coord": { "lon": -74.0836, "lat": 4.653 },
        "weather": [
            { "id": 801, "main": "Clouds", "description": "few clouds", "icon": "02d" }
        ],
        "base": "stations",
        "main": {
            "temp": 290.14,
            "feels_like": 289.15,
            "temp_min": 290.14,
            "temp_max": 290.14,
            "pressure": 1030,
            "humidity": 48
        },
        "visibility": 10000,
        "wind": { "speed": 2.57, "deg": 150 },
        "clouds": { "all": 20 },
        "dt": 1707920186,
        "sys": {
            "type": 1,
            "id": 8582,
            "country": "CO",
            "sunrise": 1707909094,
            "sunset": 1707952182
        },
        "timezone": -18000,
        "id": 3678413,
        "name": "La Merced",
        "cod": 200
    }
    ```

- **GET /consultas/pronostico5dias/{name}**: Gets the 5-day weather forecast for a specific city.
    - **Expected response**: Returns a JSON object with the 5-day weather forecast for the specified city.
    - **Response example**:
    ```json
    {
        "cod": "200",
        "message": 0,
        "cnt": 40,
        "list": [
            {
                "dt": 1707922800,
                "main": {
                    "temp": 270.84,
                    "feels_like": 264.69,
                    "temp_min": 269.72,
                    "temp_max": 270.84,
                    "pressure": 1019,
                    "sea_level": 1019,
                    "grnd_level": 1015,
                    "humidity": 54,
                    "temp_kf": 1.12
                },
                "weather": [
                    { "id": 800, "main": "Clear", "description": "clear sky", "icon": "01d" }
                ],
                "clouds": { "all": 0 },
                "wind": { "speed": 6.14, "deg": 306, "gust": 13.54 },
                "visibility": 10000,
                "pop": 0,
                "sys": { "pod": "d" },
                "dt_txt": "2024-02-14 15:00:00"
            }
        ]
    }
    ```

- **GET /consultas/contaminacion/{name}**: Gets pollution data for a specific city.
    - **Expected response**: Returns a JSON object with pollution data for the specified city.
    - **Response example**:
    ```json
    {
        "coord": { "lon": -74.006, "lat": 40.7127 },
        "list": [
            {
                "main": { "aqi": 2 },
                "components": {
                    "co": 323.77,
                    "no": 3.1,
                    "no2": 13.54,
                    "o3": 60.8,
                    "so2": 2.12,
                    "pm2_5": 2.06,
                    "pm10": 4.19,
                    "nh3": 1.3
                },
                "dt": 1707920192
            }
        ]
    }
    ```

## Usage
To use this API, simply make HTTP requests to the endpoints mentioned above, providing the name of the desired city as a parameter in the URL. Be sure to include a valid authentication token in the request header for authorization. This token will be generated in the header of an HTTP request to the login endpoint.

## Configuration
Before using this API, ensure the following dependencies and properties are correctly configured:

- Configure the OpenWeatherMap API access credentials.
- Configure the user database and ensure the user repository is properly linked.
- Add a user in the user database, which will enable authorization for queries.
- Configure the audit database and ensure the audit repository is properly linked.
- Ensure Bucket4j is configured appropriately to control the rate of queries.

## Author
Jhordy Andres Agaton Penagos

