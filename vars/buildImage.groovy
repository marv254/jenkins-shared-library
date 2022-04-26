#!/usr/bin/env groovy

def call(){
    echo 'building Image of the application'
    withCredentials([usernamePassword(credentialsId: 'nexus-docker-repo', usernameVariable: 'USER', passwordVariable: 'PASS')]){
        sh 'docker build -t 127.0.0.1:8083/java-maven-app:1.0 .'
        sh 'echo $PASS | docker login -u $USER --password-stdin 127.0.0.1:8083'
    }
}