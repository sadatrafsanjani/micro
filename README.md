#Micro-service project


## Consul Dashboard

consul agent -node=micro -dev

./mvnw spring-boot:run -Dspring-boot.run.arguments="--server.port=9080


## Build docker image

docker build -t multiplication:1.0.0 .

