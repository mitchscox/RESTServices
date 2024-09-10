pipeline {
  agent any
  stages {
    stage('Log Tool Version') {
      parallel {
        stage('Log Tool Version') {
          steps {
            sh '''mvn --version


'''
          }
        }

        stage('Check for POM') {
          steps {
            fileExists 'pom.xml'
          }
        }

      }
    }

    stage('Validate Compile') {
      steps {
        sh 'mvn validate compile'
      }
    }

    stage('Smoke tests') {
      steps {
        sh 'mvn -Dtest=TestAutomationProjectSmokeTests test'
      }
    }

    stage('Application tests') {
      steps {
        sh '''
mvn -Dtest=TestAutomationProjectApplicationTests test'''
      }
    }

  }
}