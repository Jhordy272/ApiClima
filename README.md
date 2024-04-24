# Readme ApiClima
Este archivo README proporciona una visión general del ApiClima, esta permite realizar consultas relacionadas con el clima y la contaminación para ciudades especificas.
## Descripción
ApiClima proporciona endpoints para consultar información sobre el clima y la contaminación para ciudades específicas. Utiliza la API Nominatim open streetmap para obener las coordenadas de la ciudad consultada, además utiliza la API de OpenWeatherMap para obtener datos de clima y calidad del aire, y por último registra las consultas realizadas en una base de datos de auditoría.
## Características
-   Proporciona endpoints para obtener información sobre el clima actual, el pronóstico de 5 días y la calidad del aire para una ciudad específica.
- Utiliza la API Nominatim open streetmap para recuperar datos de coordenadas.
-   Utiliza la API de OpenWeatherMap para recuperar datos de clima y contaminación.
-   Implementa la seguridad JWT para autorización.
-   Utiliza un mecanismo de almacenamiento en caché para mejorar el rendimiento de las consultas repetidas.
-   Registra todas las consultas en una base de datos de auditoría.
## Tecnologías Utilizadas
-   Java
-   Spring Boot
-   Swagger para documentación de la API
-   OpenWeatherMap API
- NominatimOpenStreetmap API
-   JSON Web Tokens (JWT) para autenticación
-   Bucket4j para limitar la tasa de consultas
-   ObjectMapper de Jackson para procesamiento JSON
-   Mockito para pruebas unitarias
## Endpoints
-   **GET /login**: Obtiene el token valido para realizar consultas.
    -   **Request example**: 
    {
        "email": "prueba@mail.com",
        "password": "123456"
    }
    -   **Respuesta esperada**: Retorna un header con el token bearer.
    -   **Response header**:
     authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcnVlYmFAbWFpbC5jb20iLCJleHAiOj
     E3MTA1MTIxMTAsIm5vbWJyZSI6InBydWViYSJ9.qUbWbZOWpAUrq1u7-xRUBo0lnZANIYkuotXI3yIMCUQrhYZLKwmurh0eNgI6zQ1ug62VgMxlw1HN-r98H4TmHw
-   **GET /consultas/clima/{name}**: Obtiene información sobre el clima actual para una ciudad específica.
    -   **Respuesta esperada**: Retorna un objeto JSON con información sobre el clima actual de la ciudad especificada.
    - **Response example**:
    { "coord": { "lon": -74.0836, "lat": 4.653 }, "weather": [ { "id": 801, "main": "Clouds", "description": "few clouds", "icon": "02d" } ], "base": "stations", "main": { "temp": 290.14, "feels_like": 289.15, "temp_min": 290.14, "temp_max": 290.14, "pressure": 1030, "humidity": 48 }, "visibility": 10000, "wind": { "speed": 2.57, "deg": 150 }, "clouds": { "all": 20 }, "dt": 1707920186, "sys": { "type": 1, "id": 8582, "country": "CO", "sunrise": 1707909094, "sunset": 1707952182 }, "timezone": -18000, "id": 3678413, "name": "La Merced", "cod": 200 }
-   **GET /consultas/pronostico5dias/{name}**: Obtiene el pronóstico del clima para los próximos 5 días para una ciudad específica.
    -   **Respuesta esperada**: Retorna un objeto JSON con el pronóstico del clima para los próximos 5 días de la ciudad especificada.
    -  **Response example**:
    { "cod": "200", "message": 0, "cnt": 40, "list": [ { "dt": 1707922800, "main": { "temp": 270.84, "feels_like": 264.69, "temp_min": 269.72, "temp_max": 270.84, "pressure": 1019, "sea_level": 1019, "grnd_level": 1015, "humidity": 54, "temp_kf": 1.12 }, "weather": [ { "id": 800, "main": "Clear", "description": "clear sky", "icon": "01d" } ], "clouds": { "all": 0 }, "wind": { "speed": 6.14, "deg": 306, "gust": 13.54 }, "visibility": 10000, "pop": 0, "sys": { "pod": "d" }, "dt_txt": "2024-02-14 15:00:00" } ]
-   **GET /consultas/contaminacion/{name}**: Obtiene información sobre la contaminación para una ciudad específica.
    -   **Respuesta esperada**: Retorna un objeto JSON con información sobre la contaminación de la ciudad especificada.
    - **Response example**:
    { "coord": { "lon": -74.006, "lat": 40.7127 }, "list": [ { "main": { "aqi": 2 }, "components": { "co": 323.77, "no": 3.1, "no2": 13.54, "o3": 60.8, "so2": 2.12, "pm2_5": 2.06, "pm10": 4.19, "nh3": 1.3 }, "dt": 1707920192 } ] }
## Uso
Para usar esta api, simplemente realice solicitudes HTTP a los endpoints mencionados anteriormente, proporcionando el nombre de la ciudad deseada como parámetro en la URL. Asegúrese de incluir un token de autenticación válido en la cabecera de la solicitud para la autorización. Este token será generado en la cabecera a una solicitud HTTP al endpoint login.
## Configuración
Antes de utilizar esta API, asegúrese de configurar correctamente las siguientes dependencias y propiedades:

-   Configurar las credenciales de acceso a la API de OpenWeatherMap.
-   Configurar la base de datos de usuario y asegurarse de que el repositorio de usuario esté correctamente vinculado.
-   Añadir un usuario en la base de datos usuario, este será el que permitirá realizar la autorización a las consultas.
-   Configurar la base de datos de auditoría y asegurarse de que el repositorio de auditoría esté correctamente vinculado.
-   Asegurarse de que Bucket4j esté configurado adecuadamente para controlar la tasa de consultas.
## Autor 
Jhordy Andres Agaton Penagos
