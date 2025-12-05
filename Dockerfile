# Stage 1: Build the WAR using Maven
FROM maven:3.9.1-eclipse-temurin-17 AS builder
WORKDIR /app

# Copy all project files
COPY . .

# Build the WAR (skip tests if needed)
RUN mvn clean package -DskipTests

# Stage 2: Run WAR in Tomcat
FROM tomcat:10.1-jdk17-temurin
# Clean default webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy WAR from builder stage
COPY --from=builder /app/target/ReportProject-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Expose Tomcat port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
