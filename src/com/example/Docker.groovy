#!/usr/bin/env groovy

package com.example

class Docker implements Serializable{
    def script
    Docker(script){
        this.script=script
    }

    def dockerLogin(){
        script.echo "Logging in into Docker Hub ..."
        script.withCredentials([script.usernamePasswordVariable(credentialsId: 'docker-hub-creds' , username: 'USER', password: 'PASS')]){
            script.sh 'docker login '
        }
    }

    def buildImage(String imageName){
        script.echo "Building image of $script.BRANCH_NAME branch of the application ..."
        script.sh "docker build -t $script.imageName ."
    }

    def dockerPush(String imageName){
        script.echo "Deploying docker Image Application on  Docker Hub ..."
        script.sh "docker push $script.imageName"
    }
}
