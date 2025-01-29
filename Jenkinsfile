pipeline {
    agent {
        docker {
            image 'eclipse-temurin:21-jdk-jammy'  // M1 compatible image
            args '--platform linux/arm64 -v /var/run/docker.sock:/var/run/docker.sock'
        }
    }
    tools {
        jdk 'JDK 21'  // Case-sensitive match
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                url: 'https://github.com/njacksonnie/SeleniumTestNGFramework.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }
}