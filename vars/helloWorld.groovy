#!/usr/bin/env groovy

def call(String textMessage) {
    print textMessage
    sh('printenv')
}