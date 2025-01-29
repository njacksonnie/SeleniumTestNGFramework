FROM jenkins/jenkins:latest-jdk21

USER root

# Install Maven
RUN apt-get update && \
    apt-get install -y maven

USER jenkins