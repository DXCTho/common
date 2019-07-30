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
	stage('Artifactory') {
		steps {
	configFileProvider([configFile(fileId: '83e8ff71-6618-47d6-a6ca-78038527066f', variable: 'MAVEN_SETTINGS')]) {
		sh 'mvn -s ${MAVEN_SETTINGS} deploy'  
		}
	}
}
		
}
}
