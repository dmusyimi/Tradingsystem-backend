pipeline {
    agent any

    environment {
        BUILD_DIR = "built"
        REPO_URL = "https://github.com/dmusyimi/Tradingsystem-backend.git"
        BRANCH = "main"
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: "${BRANCH}",
                    credentialsId: 'jenkins-github-creds',
                    url: "${REPO_URL}"
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Create built Directory') {
            steps {
                sh '''
                    mkdir -p ${BUILD_DIR}
                    cp target/*.jar ${BUILD_DIR}/
                '''
            }
        }
    }

    post {
        success {
            echo "Build successful. .jar stored in built/"
            archiveArtifacts artifacts: 'built/*.jar', fingerprint: true
        }
        failure {
            echo "Build failed"
        }
    }
}