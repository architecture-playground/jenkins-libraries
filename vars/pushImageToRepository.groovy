#!/usr/bin/env groovy

def call(body) {

    echo "** Docker login started"
    withCredentials([usernamePassword(credentialsId: 'dockerhub_architectureplayground', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
        sh '''docker login -u $USERNAME -p $PASSWORD'''
    }
    echo "** Docker login finished"

    sh '''#!/bin/bash -ex
echo "** Building application docker image started" && \\
docker build --target app -t architectureplayground/"+env.JOB_NAME.split("/")[0]+":latest . && \\
echo "** Building application docker image finished" && \\

echo "** Start pushing docker image in docker hub repository" && \\
docker push architectureplayground/"+env.JOB_NAME.split("/")[0]+":latest && \\
echo "** Docker image pushed to docker hub repository"
                    '''
}

