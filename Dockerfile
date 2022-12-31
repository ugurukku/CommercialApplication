FROM adoptopenjdk/openjdk11
EXPOSE 8080
ARG JAR_FILE=build/libs/secondhand-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} secondhand.jar
ENTRYPOINT ["java","-jar","/secondhand.jar"]