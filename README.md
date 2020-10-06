# Clients CRUD Rest API.

![](https://forthebadge.com/images/featured/featured-built-with-love.svg)

This is a CRUD Rest API for Clients that also allows user to obtain certain data according clients information.

**The main technologies used to implement this Rest API are:**
- Java 8.
- SpringBoot.
- MongoDb.

**In order to get more information about this Rest API endpoints, please get into the SWAGGER documentation:** 

- [Swagger Documentation](http://ec2-3-137-153-137.us-east-2.compute.amazonaws.com/swagger-ui.html#/)
(If you can't access is because jar is down and need to be runned. I'm working on keep jar up 24/7).
You can access swagger documentation while running local through this url: http://localhost:8080/swagger-ui.html#/

**Cloud deployment:**

- This Rest API is deployed in a AWS instance, you can hit Rest API endpoints with the next base url:
- http://ec2-3-137-153-137.us-east-2.compute.amazonaws.com:8080/
(If you can't access is because jar is down and need to be runned. I'm working in keep jar up 24/7).

**Try it on Postman:**

- [Here](https://www.getpostman.com/collections/a7d90acff3140588cb4e) you have a postman link to import 
in order to hit these Rest API endpoints.

**Running this Rest API:**
In order to run this Rest API on your machine, you can follow next steps:
- Generate JAR file using: **mvn clean install** command on terminal
- Go to the path where generated jar is located and use **java -jar JAR_NAME.jar**
- Now your application would be running on localhost-

You can also use maven plugin to run this app:
- Use command: mvn spring-boot:run on terminal.


## Questions

* [bcardenas133@gmail.com](bcardenas133@gmail.com)
