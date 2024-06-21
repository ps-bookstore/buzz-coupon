pipeline {
    agent any

    environment {
        COUPON_SERVER_1 = 'nhnacademy@133.186.150.78'
        COUPON_SERVER_DOMAIN = 'buzz-book.store'
        DEPLOY_PATH_1 = '/home/nhnacademy'
        REPO_URL = 'https://github.com/nhnacademy-be6-AA/buzz-coupon-back.git'
        ARTIFACT_NAME = 'coupon-0.0.1-SNAPSHOT.jar'
        DOCKER_IMAGE = 'parkheejun2/coupon'
        JAVA_OPTS = '-XX:+EnableDynamicAgentLoading -XX:+UseParallelGC'
        DOCKER_HUB_CREDENTIALS_ID = 'aa-dockerhub'
    }

    tools {
        jdk 'jdk-21'
        maven 'maven-3.9.7'
    }

    stages {
        stage('Checkout') {
            steps {
                git(
                    url: REPO_URL,
                    branch: 'develop',
                    credentialsId: 'aa-ssh'
                )
            }
        }
        
        stage('Build') {
            steps {
                withEnv(["JAVA_OPTS=${env.JAVA_OPTS}"]) {
                    sh 'mvn clean package -DskipTests=true'
                }
            }
        }
        
        stage('Add SSH Key to Known Hosts') {
            steps {
                script {
                    def remoteHost1 = '133.186.150.78'
                    sh """
                        mkdir -p ~/.ssh || true
                        ssh-keyscan -H ${remoteHost1} >> ~/.ssh/known_hosts || (echo "ssh-keyscan failed" && exit 1)
                    """
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    def app = docker.build("${DOCKER_IMAGE}")
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', "${DOCKER_HUB_CREDENTIALS_ID}") {
                        def app = docker.image("${DOCKER_IMAGE}")
                        app.push('latest')
                    }
                }
            }
        }
        
        stage('Deploy to Front Server 1') {
            steps {
                script {
                    deployDockerContainer(env.COUPON_SERVER_1, env.DEPLOY_PATH_1, 8080)
                    showLogs(env.COUPON_SERVER_1, env.DEPLOY_PATH_1)
                }
            }
        }
        
        stage('Verification') {
            steps {
                verifyDeployment(env.COUPON_SERVER_DOMAIN, 8080)
            }
        }
    }

    post {
        success {
            echo 'Deployment succeeded!'
        }
        failure {
            echo 'Deployment failed!'
        }
    }
}

def deployDockerContainer(server, deployPath, port) {
    withCredentials([sshUserPrivateKey(credentialsId: 'aa-ssh', keyFileVariable: 'PEM_FILE')]) {
        sh """
        ssh -o StrictHostKeyChecking=no -i \$PEM_FILE ${server} '
        docker pull ${env.DOCKER_IMAGE}:latest &&
        docker stop coupon || true &&
        docker rm coupon || true &&
        docker run -d --name coupon -p ${port}:8080 ${env.DOCKER_IMAGE}:latest
        '
        """
    }
}

def showLogs(server, deployPath) {
    withCredentials([sshUserPrivateKey(credentialsId: 'aa-ssh', keyFileVariable: 'PEM_FILE')]) {
        sh """
        ssh -o StrictHostKeyChecking=no -i \$PEM_FILE ${server} 'docker logs -f coupon'
        """
    }
}

def verifyDeployment(server, port) {
    sh """
    curl -s --head http://${server}:${port} | head -n 1
    """
}
