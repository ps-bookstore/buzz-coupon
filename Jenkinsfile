pipeline {
    agent any  // Jenkins 파이프라인이 어떤 에이전트에서나 실행될 수 있도록 설정

    environment {  // 환경 변수를 정의
        COUPON_SERVER_1 = 'nhnacademy@133.186.150.78'  // 첫 번째 프론트 서버
        DEPLOY_PATH_1 = '/home/nhnacademy'  // 첫 번째 서버의 배포 경로
        REPO_URL = 'https://github.com/nhnacademy-be6-AA/buzz-coupon-back.git'  // Git 저장소 URL
        ARTIFACT_NAME = 'coupon-0.0.1-SNAPSHOT.jar'  // 빌드 산출물 이름
        JAVA_OPTS = '-XX:+EnableDynamicAgentLoading -XX:+UseParallelGC'  // Java 실행 옵션
    }

    tools {  // 사용 도구를 설정
        jdk 'jdk-21' // Global Tool Configuration에서 설정한 JDK 이름
        maven 'maven-3.9.7' // Global Tool Configuration에서 설정한 Maven 이름
    }

    stages {
        stage('Checkout') {  // Git 저장소에서 코드를 체크아웃하는 단계
            steps {
                git(
                    url: REPO_URL,  // 저장소 URL
                    branch: 'develop',  // 체크아웃할 브랜치
                    credentialsId: 'aa-phj'  // 인증에 사용할 자격 증명 ID
                )
            }
        }
        stage('Build') {  // Maven을 사용하여 프로젝트를 빌드하는 단계
            steps {
                withEnv(["JAVA_OPTS=${env.JAVA_OPTS}"]) {  // Java 옵션을 환경 변수로 설정
                    sh 'mvn clean package -DskipTests=true'  // 테스트를 생략하고 패키징
                }
            }
        }
        stage('Test') {  // Maven을 사용하여 테스트를 실행하는 단계
            steps {
                withEnv(["JAVA_OPTS=${env.JAVA_OPTS}"]) {  // Java 옵션을 환경 변수로 설정
                    sh 'mvn test -Dsurefire.forkCount=1 -Dsurefire.useSystemClassLoader=false'  // 테스트 실행
                }
            }
        }
        stage('Add SSH Key to Known Hosts') {  // 배포 서버에 SSH 키를 추가하는 단계
            steps {
                script {
                    def remoteHost1 = '133.186.150.78'  // 첫 번째 원격 호스트
                    sh """
                        mkdir -p ~/.ssh  // .ssh 디렉토리를 생성
                        ssh-keyscan -H ${remoteHost1} >> ~/.ssh/known_hosts  // 첫 번째 호스트를 known_hosts에 추가
                    """
                }
            }
        }
        stage('Deploy to Front Server 1') {  // 첫 번째 프론트 서버에 배포하는 단계
            steps {
                deployToServer(COUPON_SERVER_1, DEPLOY_PATH_1, 8080)  // 서버에 배포하는 사용자 정의 함수 호출
                showLogs(COUPON_SERVER_1, DEPLOY_PATH_1)  // 로그를 출력하는 사용자 정의 함수 호출
            }
        }
        stage('Verification') {  // 배포가 성공했는지 확인하는 단계
            steps {
                verifyDeployment(COUPON_SERVER_1, 8080)  // 첫 번째 서버의 배포 확인
            }
        }
    }
    post {  // 파이프라인 실행 후의 후처리
        success {
            echo 'Deployment succeeded!'  // 성공 메시지 출력
        }
        failure {
            echo 'Deployment failed!'  // 실패 메시지 출력
        }
    }
}

def deployToServer(server, deployPath, port) {
    withCredentials([sshUserPrivateKey(credentialsId: 'aa-phj', keyFileVariable: 'PEM_FILE')]) {  // SSH 자격 증명을 사용
        sh """
        scp -o StrictHostKeyChecking=no -i \$PEM_FILE target/${ARTIFACT_NAME} ${server}:${deployPath}  // 빌드 산출물을 서버로 복사
        ssh -o StrictHostKeyChecking=no -i \$PEM_FILE ${server} 'nohup java -jar ${deployPath}/${ARTIFACT_NAME} --server.port=${port} ${env.JAVA_OPTS} > ${deployPath}/app.log 2>&1 &'  // 애플리케이션을 백그라운드에서 실행
        """
    }
}

def showLogs(server, deployPath) {
    withCredentials([sshUserPrivateKey(credentialsId: 'aa-phj', keyFileVariable: 'PEM_FILE')]) {  // SSH 자격 증명을 사용
        sh """
        ssh -o StrictHostKeyChecking=no -i \$PEM_FILE ${server} 'tail -n 100 ${deployPath}/app.log'  // 서버의 로그 파일을 출력
        """
    }
}

def verifyDeployment(server, port) {
    sh """
    curl -s --head http://${server}:${port} | head -n 1  // HTTP 요청을 보내 서버 상태 확인
    """
}
