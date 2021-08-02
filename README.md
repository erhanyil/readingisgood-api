# ReadingIsGood API

---

### Tech Stack

Used tech stack are;

- Java 11
- Maven
- SpringBoot 2.5.3
    - Web
    - Security
    - Validation
    - Mongo
- MongoDB

---

### Before Start

> **Note:** The **MongoDB uri** must be changed !

- `mvn clean install`
- `./mvnw package && java -jar target/gs-spring-boot-docker-0.1.0.jar`

---

### Start

- With docker,
    - `docker build -t springio/gs-spring-boot-docker .`
    - `docker run -p 8080:8080 springio/gs-spring-boot-docker`
- Without docker,
    - `mvn spring-boot:run`

---

# API Test Credentials

- **Username**: erhan_yil@windowslive.com
- **Password**: 123

---

Swagger Url: http://localhost:8080/swagger-ui.html

---