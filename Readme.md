# Global Optima - Customer microservice
### Goals
 - CRUD operations on the Customer entity
 - Dynamic reconfiguration with consul
 - Make requests for new orders
 - Create reviews for food preparation and delivery services

### Requirements
 - Java openjdk-19.0.1
 - Maven 3.8.6
 - Running postgres server on localhost  
`docker run -d --name pg-customer -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=customers -p 5432:5432 postgres:15.1`

Check the correct versions with `mvn --version`

### How to run
 - run consul with `consul agent -dev ui`
 - build the project `mvn clean package`  
 - run locally `java -jar target/customer-server-1.0-SNAPSHOT.jar`  
 - Service can be accessed via [http://localhost:8080/v1/customers](http://localhost:8080/v1/customers)
 - openapi documentation can be viewed via [http://localhost:8080/openapi/ui](http://localhost:8080/openapi/ui)
 - change config status on `http://localhost:8500` - consul UI and add key: `environments/dev/services/customer-server/1.0.0/config/rest-config/maintenance`

### Docker
```
docker build -t customer-server .   
docker images
docker run customer-server
```

```
docker network ls  
docker network rm gteam
docker network create gteam
docker run -d --name pg-customer -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=customers -p 5432:5432 --network gteam postgres:15.1
docker inspect pg-customer
docker run -p 8080:8080 --name customer --network gteam -d -e KUMULUZEE_DATASOURCES0_CONNECTIONURL=jdbc:postgresql://pg-customer:5432/customers customer-server
```

