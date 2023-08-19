# Rest service crd-msa-automotriz

Technologies Used

- Spring Boot 2.7.14
- Spring Data JPA
- Lombok
- H2 DataBase
- open-api 1.6.15
## How to Run this application

First generate a build:

```shell
$ ./gradlew clean build -xtest
```
next, run with this command:

```shell
$ ./gradlew bootRun
```
## Swagger
http://localhost:8080/swagger-ui.html
## Actuactor to monitoring health
http://localhost:8080/actuator/health

### Complaints or doubts
- Omar Santiago Tapia Hidalgo (omar.tapia.h@gmail.com)