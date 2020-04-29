#!/usr/bin/env groovy

def call(String repositoryName) {

    sh("""#!/bin/bash -ex
echo "** Building tests docker image started" && \\
docker build --target build -t architectureplayground/${repositoryName}:tests . && \\
echo "** Building tests docker image finished" && \\

echo "** Tests started" && \\
docker run -i --rm -v /var/run/docker.sock:/var/run/docker.sock architectureplayground/${repositoryName}:tests && \\
echo "** Tests finished"
""")
}