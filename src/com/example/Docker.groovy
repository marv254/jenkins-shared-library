#!/usr/bin/env groovy
package com.example

class  Docker implements  Serializable {
    def script

    Docker(script){
        this.script=script
    }
    def buildDockerImage(String imageName) {
        script.echo 'building Image of the application'
        script.withCredentials([script.usernamePassword(credentialsId: 'nexus-docker-repo', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
            script.sh "docker build -t $script.imageName ."
            script.sh "echo $script.PASS | docker login -u $script.USER --password-stdin 127.0.0.1:8083"
        }
    }


}