pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "maven_home"
    }
     parameters {
        string(name: 'CUCUMBER_TAGS', defaultValue: '@smoke', description: 'Cucumber tags to run')
    }

    stages {
        stage('checkout') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'main', url: 'https://github.com/shankar86/RijksmuseumAPI-Assignment2.git'
            }
		}
		stage('Build and Test') {
			steps {
			    withEnv(["cucumber.options=${params.CUCUMBER_TAGS}"]) {
				bat "mvn clean install"
			    }
			}
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