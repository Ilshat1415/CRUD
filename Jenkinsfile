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

        choice(name: 'INSTANCES', choices: ['1', '2', '3', '4', '5'])
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
                sh 'docker build -t ilshat1415/jenkins-images:0.4 .'
            }
        }
        stage('Push docker image to DockerHub') {
            steps{
                withDockerRegistry(credentialsId: 'dockerhub-ilshat1415', url: 'https://index.docker.io/v1/') {
                    sh '''
                        docker push ilshat1415/jenkins-images:0.4
                    '''
                }
            }
        }
        stage('Delete docker image locally') {
            steps{
                sh 'docker rmi ilshat1415/jenkins-images:0.4'
            }
        }
        stage('Deploy to staging') {
            steps {
                sshagent(['ubuntu']) {
                    script {
                        int n = Integer.parseInt("${params.INSTANCES}");
                        withCredentials([string(credentialsId: 'VM_IP', variable: 'IP')]) {
                            for (int i = 0; i < n; i++) {
                                sh 'ssh -o StrictHostKeyChecking=no ilshat1415@${IP} docker run --network kafka-net -e PORT=8081 -e SPRING_DATASOURCE_URL=jdbc:postgresql://postgres/postgres -e SPRING_KAFKA_CONSUMER_BOOTSTRAP_SERVERS=kafka:9092 -e SPRING_KAFKA_PRODUCER_BOOTSTRAP_SERVERS=kafka:9092 -e EUREKA_HOST=http://eureka:8761/eureka/ -v ${PWD}/logs:/logs -d --memory 512m ilshat1415/jenkins-images:0.4'
                            }
                        }
                    }
                }
            }
        }
    }
    post {
        success {
            withCredentials([string(credentialsId: 'botToken', variable: 'TOKEN'), string(credentialsId: 'chatId', variable: 'CHAT_ID')]) {
            sh """
                curl -s -X POST https://api.telegram.org/bot${TOKEN}/sendMessage -d chat_id=${CHAT_ID} -d parse_mode=markdown -d text='*${env.JOB_NAME}*\n*Tag* : `${params.TAG}`\n*Build* : `OK`\n*Deploy* : `YES`'
            """
            }
        }
        aborted {
            withCredentials([string(credentialsId: 'botToken', variable: 'TOKEN'), string(credentialsId: 'chatId', variable: 'CHAT_ID')]) {
            sh """
                curl -s -X POST https://api.telegram.org/bot${TOKEN}/sendMessage -d chat_id=${CHAT_ID} -d parse_mode=markdown -d text='*${env.JOB_NAME}*\n*Tag* : `${params.TAG}`\n*Build* : `ABORTED`\n*Deploy* : `ABORTED`'
            """
            }
        }
        failure {
            withCredentials([string(credentialsId: 'botToken', variable: 'TOKEN'), string(credentialsId: 'chatId', variable: 'CHAT_ID')]) {
            sh """
                curl -s -X POST https://api.telegram.org/bot${TOKEN}/sendMessage -d chat_id=${CHAT_ID} -d parse_mode=markdown -d text='*${env.JOB_NAME}*\n*Tag* : `${params.TAG}`\n*Build* : `Not OK`\n*Deploy* : `NO`'
            """
            }
        }
    }
}