#!/usr/bin/env groovy

def call(body) {
    when {
        expression {
            print(env.BRANCH_NAME)
            return env.BRANCH_NAME == 'master';
        }
    }
}