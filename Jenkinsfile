pipeline {
    agent any

    parameters {
        gitParameter (  branch: '',
                        branchFilter: '.*',
                        defaultValue: 'master',
                        description: '',
                        name: 'TAG',
                        quickFilterEnabled: true,
                        selectedValue: 'NONE',
                        sortMode: 'NONE',
                        tagFilter: '*',
                        type: 'PT_TAG')
    }

    stages {
        stage('Delete workspace before build starts') {
            steps {
                echo 'Deleting workspace'
                deleteDir()
            }
        }
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM',
                    branches: [[name: "${params.TAG}"]],
                    doGenerateSubmoduleConfigurations: false,
                    extensions: [],
                    gitTool: 'Default',
                    submoduleCfg: [],
                    userRemoteConfigs: [[url: 'https://github.com/Ilshat1415/CRUD.git']]
                ])
            }
        }
        stage('Build docker image') {
            steps{
                sh 'docker build -t ilshat1415/jenkins-images:${TAG} .'
            }
        }
        stage('Push docker image to DockerHub') {
            steps{
                withDockerRegistry(credentialsId: 'dockerhub-ilshat1415', url: 'https://index.docker.io/v1/') {
                    sh '''
                        docker push ilshat1415/jenkins-images:${TAG}
                    '''
                }
            }
        }
        stage('Delete docker image locally') {
            steps{
                sh 'docker rmi ilshat1415/jenkins-images:${TAG}'
            }
        }
    }
}