#!/usr/bin/env groovy

def call(body) {
    when {
        expression {
            return env.BRANCH_NAME == 'master';
        }
    }
    {
        echo "** Docker login started"
        withCredentials([usernamePassword(credentialsId: 'dockerhub_architectureplayground', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
            sh '''docker login -u $USERNAME -p $PASSWORD'''
        }
        echo "** Docker login finished"

        sh '''#!/bin/bash -ex
echo "** Building application docker image started" && \\
docker build --target app -t architectureplayground/feature-toggle:latest . && \\
echo "** Building application docker image finished" && \\

echo "** Start pushing docker image in docker hub repository" && \\
docker push architectureplayground/feature-toggle:latest && \\
echo "** Docker image pushed to docker hub repository"
                    '''
    }
}
