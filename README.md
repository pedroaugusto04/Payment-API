# Payment-API
API to process payments in Java

Project inspired by Wirecard Backend Challenge. Consists of an API to process payments, storing information in a MySQL database. My goal with this project was to better understand Spring ecosystem, together with good practices related to its use.

Technologies used: Spring Web, Spring Data JPA with Hibernate, Spring security, MySQL, Maven, Bean Validation with Hibernate

# 🛠️ How to run

* Download JDK (Java Development Kit)

* Download MySQL and Create a MySQL database

* Clone this repository

* Go to demo/src/main/resources

* Create a file named application.properties

* Inside application.properties, copy and paste the following code, filling in with your data

```
spring.datasource.url=jdbc:mysql://localhost:<port>/<databaseName>?useTimeZone=true&serverTimezone=UTC&autoReconnect=true

spring.datasource.username=<yourUser>

spring.datasource.password=<yourPassword>

spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver

spring.datasource.testWhileIdle=true

spring.datasource.validationQuery=SELECT 1

spring.jpa.show-sql=true

spring.jpa.hibernate.ddl-auto=update

spring.jpa.hibernate.naming-strategy=org.hibernate.cfg.ImprovedNamingStrategy

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

spring.sql.init.mode=ALWAYS

spring.datasource.data=data.sql

spring.jpa.defer-datasource-initialization=true
```
* Download Maven. Go to /demo and run `mvn clean install`

* Go to /demo/target. You will se demo-0.0.1-SNAPSHOT.jar

* Run demo-0.0.1-SNAPSHOT.jar with `java -jar demo-0.0.1-SNAPSHOT.jar` and send your requests

# REST API
## User
  There are three types of user: "ADMIN", "CLIENT", "BUYER"
  
  They are needed to see if you are allowed to do some request or not
### Create User (ALLOWED FOR ALL)

`POST /users/signup`

### Request
    {
    "username": "yourUser",
    "password": "yourPass",
    "roleType": "admin" or "client" or "buyer"  (you are able to choose your role only to facilitate) 
    }
### Response
    201 CREATED
    {
    "id": "54514d62-3217-4026-a2b4-74ba60ac391c",
    "username": "yourUser",
    "password": "$2a$10$fYMaIQYgb44qpp3yr.HXBucZAv02kIj3aDbNoBiHVGNdGAGrUVVGK",   (encrypted key)
    "enabled": true,
    "authorities": [
        {
            "id": 3,
            "authority": "ROLE_ADMIN"
        }
    ],
    "accountNonExpired": true,
    "accountNonLocked": true,
    "credentialsNonExpired": true
    }
### GET User Info (ALLOWED FOR ADMIN)
`GET /users  (retrieves all users)`

`GET /users/id/{username}   (retrieves userId from username)`   

### DELETE User (ALLOWED FOR ADMIN)

`DELETE /users/{userId}`   

### UPDATE User (ALLOWED FOR ADMIN)

`PUT /users/{userId}`

## Card
  To buy using credit option, a card is needed
### Create Card (ALLOWED FOR BUYER,ADMIN)

`POST /cards`

### Request
    {
    "cardHolderName": "yourName",
    "cardNumber": "1",
    "cardCvv": "123" 
    }
### Response
    201 CREATED
### GET Card Info (ALLOWED FOR ADMIN)

`GET /cards/{cardNumber}    (retrieves all cards) ` 

`GET /cards/{cardNumber}    (retrieves card from cardNumber) `   

`GET /users/id/{username}   (retrieves userId from username)` 

### DELETE Card (ALLOWED FOR ADMIN)

`DELETE /cards/{cardNumber}`

### UPDATE Card (ALLOWED FOR ADMIN)

`PUT /cards/{cardNumber}`

## Buyer

### Create Buyer (ALLOWED FOR CLIENT,BUYER,ADMIN)

`POST /buyers`

### Request
    {
    "cpf": "123.456.789-09",
    "name": "yourName",
    "email": "example123@gmail.com" 
    }
### Response
    201 CREATED
### GET Buyer Info (ALLOWED FOR ADMIN)

`GET /buyers    (retrieves all buyers) `   

`GET /buyers/{buyerCpf}   (retrieves buyer from buyerCpf)`

### DELETE Buyer (ALLOWED FOR ADMIN)

`DELETE /buyers/{buyerCpf}`   

### UPDATE Buyer (ALLOWED FOR ADMIN)

`PUT /buyers/{buyerCpf}`

## Payment

### Create Credit Payment (ALLOWED FOR BUYER,ADMIN)

`POST /payments/credit`

### Request
    {
    "payment":{
        "amount": 500,
        "card":{
            "cardHolderName": "yourName",
            "cardNumber": 12,
            "cardCvv": 124
        }
    },
    "buyer":{
    "cpf": "123.456.789-09"   
    }
    }
### Response
    201 CREATED
    Sucessfully transaction for yourName
### Create Boleto Payment (ALLOWED FOR BUYER,ADMIN)

`POST /payments/boleto`

### Request
    {
    "payment":{
        "amount": 500
    },
    "buyer":{
    "cpf": "123.456.789-09"   
    }
    }
### Response
    201 CREATED  
    8213777781789215734  (num boleto)
### GET Payment Info (ALLOWED FOR ADMIN)

`GET /payments    (retrieves all payments)`   

`GET /payments/{paymentId}   (retrieves payment from paymentId)`

### DELETE Payment (ALLOWED FOR ADMIN)

`DELETE /payments/{paymentId}`   

### UPDATE Payment (ALLOWED FOR ADMIN)

`PUT /payments/{paymentId}`
