pipeline {
    agent any

    environment {
        IMAGE_NAME  = 'miras-suleimenov-student-api'
        IMAGE_TAG   = 'v1.0-miras-suleimenov'
        STUDENT     = 'Miras Suleimenov | IT2-2313 | ID: 37233'
    }

    stages {
        stage('Checkout') {
            steps {
                echo "=== [Checkout] ${STUDENT} ==="
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo "=== [Build] ${STUDENT} ==="
                sh './gradlew bootJar --no-daemon'
            }
        }

        stage('Test') {
            steps {
                echo "=== [Test] ${STUDENT} ==="
                sh './gradlew test --no-daemon'
            }
            post {
                always {
                    junit '**/build/test-results/test/*.xml'
                }
            }
        }

        stage('Docker Build') {
            steps {
                echo "=== [Docker Build] ${STUDENT} ==="
                sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
            }
        }

        stage('Verify') {
            steps {
                echo "=== [Verify] ${STUDENT} ==="
                sh "docker images ${IMAGE_NAME}:${IMAGE_TAG}"
                echo "Image ${IMAGE_NAME}:${IMAGE_TAG} built successfully for ${STUDENT}"
            }
        }
    }

    post {
        success {
            echo "Pipeline SUCCESS — ${STUDENT}"
        }
        failure {
            echo "Pipeline FAILED  — ${STUDENT}"
        }
    }
}
