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
    }
}