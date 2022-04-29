#!/usr/bin/env groovy

def call (){
    echo "Building Jar from the application on $BRANCH_NAME branch ..."
    sh "mvn package"
}