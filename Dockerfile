FROM --platform=linux/arm64 eclipse-temurin:21-jdk

# Install Chrome for Selenium
RUN apt-get update && \
    apt-get install -y \
    chromium \
    chromium-driver

WORKDIR /app
COPY . .

# Example for Maven
RUN mvn clean install -DskipTests

CMD ["mvn", "test"]