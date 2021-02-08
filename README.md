# iti0215-distributed-systems

The main goal is to create project which represents minimalistic microservices
architecture without using popular frameworks. This project is based on Java 11
and gradle version 6.8.1.

## Build
`gradle build
`

## Run
`gradle run
`

## Tests

`gradle test
`

## Build and Run all microservices 
`gradle build && docker-compose up --build
`

## Request examples
`http://localhost:8080/user-api/v1/user
`
`http://localhost:8081/inventory-api/v1/item/10
`