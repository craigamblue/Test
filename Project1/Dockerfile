# FROM openjdk:8-jdk-alpine
# COPY target/Project1-0.0.1-SNAPSHOT.jar Project1.jar
# ENTRYPOINT ["java", "-jar", "/Project1.jar"]

#builder stage with maven & jdk 8 to create base image
FROM maven:3.6.3-openjdk-8 as builder 

#need to copy source code and pom.xml from host computer to container
COPY src/ src/
COPY pom.xml pom.xml


#Bulid application to generage .jar file
RUN mvn package -Dmaven.test.skip

#No longer need Maven, so save artifiact up to this point, and discard maven
FROM java:8 as runner
#Base image with only JRE 

#copy from builder stage above (.jar) to runner stage
COPY --from=builder target/project01.jar project1.jar

#Use entrypoint to run application
ENTRYPOINT ["java", "-jar", "/project1.jar"]