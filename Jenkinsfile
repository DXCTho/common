pipeline {
  agent any
  environment {
        MYSONARIP = '172.17.0.2'
	MYSONARHOST    = 'http://localhost:9000/'
       XMP_IMAGE = readMavenPom().getArtifactId()
       XMP_POM_VERSION = readMavenPom().getVersion()	
    }
  stages {
    stage('Variable Check') {
        steps {
               sh "printenv | sort"
            	}
				}
    stage('Build') {
      steps {
        configFileProvider([configFile(fileId: '83e8ff71-6618-47d6-a6ca-78038527066f', variable: 'MAVEN_SETTINGS')]) {
          sh 'mvn -s ${MAVEN_SETTINGS} clean install'                                                                                           
        }
      }
    }
	stage('SonarQube analysis') {
      steps {
          sh "mvn sonar:sonar -Dsonar.host.url=${env.MYSONARHOST}"                                                                                         
      }
    }
   stage('Artifactory Repo') {
           steps {
		   withCredentials([string(credentialsId: 'JFrog_API_Key', variable: 'TOKEN')]) {
                sh '''
  			curl -H 'X-JFrog-Art-Api:'${TOKEN}'' -X PUT "http://172.17.0.3:8081/artifactory/libs-release-local/com/dai/xmp/${XMP_IMAGE}/${XMP_POM_VERSION}/${XMP_IMAGE}-${XMP_POM_VERSION}.pom" -T /var/lib/jenkins/.m2/repository/com/dai/xmp/${XMP_IMAGE}/${XMP_POM_VERSION}/${XMP_IMAGE}-${XMP_POM_VERSION}.pom
			curl -H 'X-JFrog-Art-Api:'${TOKEN}'' -X POST http://172.17.0.3:8081/artifactory/api/maven/calculateMetadata/libs-release-local/com/dai/xmp/${XMP_IMAGE}
                '''
            }
					
                }
}
}
}
