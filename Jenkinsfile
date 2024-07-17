pipeline {
	agent any
	environment {
        MAVEN_HOME = tool 'maven_home'
    }
	stages {
		stage('checkout') {
			steps {
				checkout 'https://github.com/shankar86/RijksmuseumAPI-Assignment2.git'
			}
		}
		stage('Build') {
			steps {
				sh "${MAVEN_HOME}/bin/mvn clean compile"
			}
		}
		stage('Test') {
			steps {
				sh "${MAVEN_HOME}/bin/mvn install -Dcucumber.filter.tags='@smoke'"
			}
		}
		stage('Report') {
            steps {
                cucumber buildStatus: 'UNSTABLE', reportTitle: 'Cucumber Report', fileIncludePattern: '**/target/cucumber-reports/*.json'
            }
	}
	post {
        always {
            // Cleanup and notifications
            junit '**/target/surefire-reports/*.xml' // Collect test results
            cucumber buildStatus: 'UNSTABLE', reportTitle: 'Cucumber Report', fileIncludePattern: '**/target/cucumber-reports/*.json'
        }
    }
}