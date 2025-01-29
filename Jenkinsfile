pipeline {
    agent any
    tools {
        jdk 'jdk21'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/njacksonnie/SeleniumTestNGFramework.git'
            }
        }
        stage('Build Image') {
            steps {
                script {
                    docker.build("selenium-tests:${env.BUILD_ID}")
                }
            }
        }
        stage('Run Tests') {
            steps {
                script {
                    docker.image("selenium-tests:${env.BUILD_ID}").run(
                        "--shm-size=2g --platform linux/arm64"
                    )
                }
            }
        }
        stage('Cleanup') {
            steps {
                script {
                    sh 'docker system prune -f'
                }
            }
        }
    }
}