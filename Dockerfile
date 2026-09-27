# Build: compile the WAR with Maven + JDK 11
FROM maven:3.9-eclipse-temurin-11 AS build
WORKDIR /app
COPY PROG/Proyecto_PayToWin/pom.xml .
RUN mvn -B -q dependency:go-offline
COPY PROG/Proyecto_PayToWin/src ./src
RUN mvn -B -q package -DskipTests

# Run: Tomcat 9 (javax.* servlets), app served at /
FROM tomcat:9.0-jre11-temurin
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war
# Render free instances have 512 MB of RAM
ENV JAVA_OPTS="-XX:MaxRAMPercentage=70 -XX:+UseSerialGC"
EXPOSE 8080
# Render provides the port in $PORT
CMD ["sh", "-c", "sed -i \"s/port=\\\"8080\\\"/port=\\\"${PORT:-8080}\\\"/\" conf/server.xml && exec catalina.sh run"]
