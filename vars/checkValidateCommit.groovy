#!/usr/bin/env groovy

int shaInfoIndent = 43 // + a9988e5594b7be5bd0a2ef8e88adfa73bebd6c29 Commit message

def gitCherryResult = "git cherry -v origin/master".execute()

def errorBuffer = new StringBuffer()
gitCherryResult.consumeProcessErrorStream(errorBuffer)

if (errorBuffer.size() > 0) {
    print errorBuffer.toString()
    System.exit(1)
} else {

    def invalidCommits = new ArrayList()
    gitCherryResult.text.stripIndent(shaInfoIndent).eachLine { line ->
        switch (line) {
            case ~'^SPIKE.*':
                break
            case ~'^#\\d+.*':
                break
            default:
                invalidCommits.add("Invalid commit message: $line")
        }
    }

    if (!invalidCommits.isEmpty()) {
        println(invalidCommits)
        System.exit(1)
    }
}
