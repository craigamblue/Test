pipeline {
    agent any

    stages {
        stage("Maven clean package") {
            steps {
                sh 'mvn clean package'
                echo 'Building..'
            }
        }

}