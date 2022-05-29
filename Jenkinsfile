pipeline {
    agent any

    stages {
        stage('Delete workspace before build starts') {
            steps {
                echo 'Deleting workspace'
                deleteDir()
            }
        }
        stage('Checkout') {
            steps{
                git branch: 'master',
                    url: 'https://github.com/Ilshat1415/CRUD.git'
                }
        }
        stage('Build docker image') {
            steps{
                    sh 'docker build -t ilshat1415/jenkins-images:0.1 .'
            }
        }
        stage('Push docker image to DockerHub') {
            steps{
                withDockerRegistry(credentialsId: 'dockerhub-ilshat1415', url: 'https://index.docker.io/v1/') {
                    sh '''
                        docker push ilshat1415/jenkins-images:0.1
                    '''
                }
            }
        }
        stage('Delete docker image locally') {
            steps{
                sh 'docker rmi ilshat1415/jenkins-images:0.1'
            }
        }
        stage('Deploy to staging') {
            steps {
                sshagent(['ubuntu']) {
                    sh 'ssh -o StrictHostKeyChecking=no ilshat1415@192.168.135.129 docker run --network kafka-net -p 8081:8081 -e PORT=8081 -e SPRING_DATASOURCE_URL=jdbc:postgresql://postgres/postgres -e SPRING_KAFKA_CONSUMER_BOOTSTRAP_SERVERS=kafka:9092 -e SPRING_KAFKA_PRODUCER_BOOTSTRAP_SERVERS=kafka:9092 ilshat1415/jenkins-images:0.1'
                }
            }
        }
    }
}