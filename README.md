## Fleet Management microservice

Project written with java 18 + Spring Boot 2.6.6
Tech:
<b>
- Mysql
- Mongodb (logging mechanism)
- Kafka (logging mechanism)
- CircuitBreaker
- Flyway
- Docker (with Volumes)
- Testcontainers
- Swagger
</b>

This service have a 11 endpoint;

1. **POST /v1/vehicles** for create new vehicle

2. **PUT /v1/vehicles** for vehicle status update

3. **GET /v1/vehicles/{vehiclePlate}** for get vehicle by plate

4. **POST /v1/sacks** for create new sack

5. **POST /v1/sacks/put-to-vehicle** for sack put on the vehicle

6. **GET /v1/sacks/{barcode}**  for get sack by barcode

7. **POST /v1/packages** for create new package

8. **POST /v1/packages/put-to-sack** for package put on the sack

9. **GET /v1/packages/{barcode}** for get package by barcode

10. **POST /v1/delivery-points** for create new delivery point

11. **GET /v1/distribute/{vehiclePlate}** for distribute sacks and packages

Project have Swagger-ui you can test it in our browser by visiting http://localhost:8080/swagger-ui.html

**DELIVERY_POINT,PACKAGE,SACK and VEHICLE** stored in mysql database (dockerized + volume )

**fleetLogs** stored in mongodb (dockerized + volume )

For run project ;
1. With Docker :
    - Compile project with Java 18
    - Run with Docker comments , go to project folder and run this commands:

    ```
    $ docker build -t fleet-management-image .
    $ docker-compose -f docker-compose.yaml up -d
    ```
2. With IDE  :
    - Firstly you must be run mysql,kafka,zookeper and mongodb in docker
    - add your host file
      0.0.0.0 fleet-management-mysql
      0.0.0.0 fleet-management-kafka
      0.0.0.0 fleet-management-mongo-db
      0.0.0.0 fleet-management-net
    - Run mysql in docker-compose.yaml file and run java project


Project have %80 method coverage with integration test;


-Curl Command
- You can see curl command in http://localhost:8080/swagger-ui.html

