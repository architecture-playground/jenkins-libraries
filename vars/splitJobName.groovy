#!/usr/bin/env groovy

def jobName = "${env.JOB_NAME}"
Jenkins.instance.getItem(jobName.split('/')[0]).description = "hi"