FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} productservice.jar
# COPY pom.xml /bryanyidev/pom.xml

# start the jar file
ENTRYPOINT ["java", "-jar", "/productservice.jar"]
# RUN mvn -f /bryanyidev/pom.xml clean package -DskipTests=true

# expose port
EXPOSE 8080

