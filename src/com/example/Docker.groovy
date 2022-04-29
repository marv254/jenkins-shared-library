#!/usr/bin/env groovy

package com.example

class Docker implements Serializable{
    def script
    Docker(script){
        this.script=script
    }

    def dockerLogin(){
        script.echo "Logging in into Docker Hub ..."
        script.withCredentials([script.usernamePassword(credentialsId: 'docker-hub-creds' , usernameVariable: 'USER', passwordVariable: 'PASS')]){
            script.sh "echo $script.PASS | docker login -u $script.USER  --password-stdin "

        }
    }

    def buildImage(String imageName){
        script.echo "Building image of $script.BRANCH_NAME branch of the application ..."
        script.sh "docker build -t $imageName ."
    }

    def dockerPush(String imageName){
        script.echo "Deploying docker Image Application on  Docker Hub ..."
        script.sh "docker push $imageName"
    }
}
