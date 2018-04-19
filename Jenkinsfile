@Library('deploy')
import deploy

def deployLib = new deploy()

node {
    def application = "pam-cv-indexer"

    def committer, committerEmail, changelog, pom, releaseVersion, isSnapshot, nextVersion // metadata

    def mvnHome = tool "maven-3.3.9"
    def mvn = "${mvnHome}/bin/mvn"
    def deployEnv = "${env.DEPLOY_ENV}"
    def namespace = "${env.NAMESPACE}"
    def policies = "app-policies.xml"
    def notenforced = "not-enforced-urls.txt"
    def appConfig = "nais.yaml"
    def dockerRepo = "repo.adeo.no:5443"
    def zone = 'sbs'

    def color

    try {

        stage("checkout") {
                    cleanWs()
                    withCredentials([string(credentialsId: 'navikt-ci-oauthtoken', variable: 'token')]) {
                     withEnv(['HTTPS_PROXY=http://webproxy-utvikler.nav.no:8088']) {
                            sh(script: "git clone https://${token}:x-oauth-basic@github.com/navikt/${application}.git .")
                        }
                    }
                }

        stage("initialize") {
            pom = readMavenPom file: 'pom.xml'
            releaseVersion = pom.version.tokenize("-")[0]
            isSnapshot = pom.version.contains("-SNAPSHOT")
            committer = sh(script: 'git log -1 --pretty=format:"%an (%ae)"', returnStdout: true).trim()
            committerEmail = sh(script: 'git log -1 --pretty=format:"%ae"', returnStdout: true).trim()
            changelog = sh(script: 'git log `git describe --tags --abbrev=0`..HEAD --oneline', returnStdout: true)
        }

        stage("verify maven versions") {
            sh 'echo "Verifying that no snapshot dependencies is being used."'
            sh 'grep module pom.xml | cut -d">" -f2 | cut -d"<" -f1 > snapshots.txt'
            sh 'echo "./" >> snapshots.txt'
            sh 'while read line;do if [ "$line" != "" ];then if [ `grep SNAPSHOT $line/pom.xml | wc -l` -gt 1 ];then echo "SNAPSHOT-dependencies found. See file $line/pom.xml.";exit 1;fi;fi;done < snapshots.txt'
        }


        stage("build and test backend") {
            if (isSnapshot) {
                sh "${mvn} clean install -Dit.skip=true -Djava.io.tmpdir=/tmp/${application} -B -e"
            } else {
                println("POM version is not a SNAPSHOT, it is ${pom.version}. Skipping build and testing of backend")
            }

        }

        stage("release version") {
            withEnv(['HTTPS_PROXY=http://webproxy-utvikler.nav.no:8088']) {
                withCredentials([string(credentialsId: 'navikt-ci-oauthtoken', variable: 'token')]) {
                    sh "${mvn} versions:set -B -DnewVersion=${releaseVersion} -DgenerateBackupPoms=false"
                    sh "git commit -am \"set version to ${releaseVersion} (from Jenkins pipeline)\""
                    sh ("git push https://${token}:x-oauth-basic@github.com/navikt/${application}.git")
                    sh ("git tag -a ${application}-${releaseVersion} -m ${application}-${releaseVersion}")
                    sh ("git push https://${token}:x-oauth-basic@github.com/navikt/${application}.git --tags")
                }
            }
        }

        stage("publish yaml") {
            withCredentials([usernamePassword(credentialsId: 'nexusUploader', usernameVariable: 'NEXUS_USERNAME', passwordVariable: 'NEXUS_PASSWORD')]) {
                sh "curl --user ${env.NEXUS_USERNAME}:${env.NEXUS_PASSWORD} --upload-file ${appConfig} https://repo.adeo.no/repository/raw/nais/${application}/${releaseVersion}/nais.yaml"
            }
        }

        stage("build and publish docker image") {
            withCredentials([usernamePassword(credentialsId: 'nexusUploader', usernameVariable: 'NEXUS_USERNAME', passwordVariable: 'NEXUS_PASSWORD')]) {
                sh "docker build --build-arg JAR_FILE=${application}-${releaseVersion}.jar -t ${dockerRepo}/${application}:${releaseVersion} ."
                sh "docker login -u ${env.NEXUS_USERNAME} -p ${env.NEXUS_PASSWORD} ${dockerRepo} && docker push ${dockerRepo}/${application}:${releaseVersion}"
            }
        }

        stage("new dev version") {
            withEnv(['HTTPS_PROXY=http://webproxy-utvikler.nav.no:8088']) {
                withCredentials([string(credentialsId: 'navikt-ci-oauthtoken', variable: 'token')]) {
                    nextVersion = (releaseVersion.toInteger() + 1) + "-SNAPSHOT"
                    sh "${mvn} versions:set -B -DnewVersion=${nextVersion} -DgenerateBackupPoms=false"
                    sh "git commit -am \"updated to new dev-version ${nextVersion} after release by ${committer}\""
                    sh "git push https://${token}:x-oauth-basic@github.com/navikt/${application}.git"
                }
            }
        }

        stage("deploy to preprod") {
            callback = "${env.BUILD_URL}input/Deploy/"

            def deploy = deployLib.deployNaisApp(application, releaseVersion, deployEnv, zone, namespace, callback, committer, false).key

            try {
                timeout(time: 15, unit: 'MINUTES') {
                    input id: 'deploy', message: "Check status here:  https://jira.adeo.no/browse/${deploy}"
                }
            } catch (Exception e) {https://repo.adeo.no/repository/raw/
            throw new Exception("Deploy feilet :( \n Se https://jira.adeo.no/browse/" + deploy + " for detaljer", e)

            }
        }


        color = '#BDFFC3'
        GString message = ":heart_eyes_cat: Siste commit på ${application} bygd og deploya OK.\nSiste commit ${changelog}"
        slackSend color: color, channel: '#pam_bygg', message: message, teamDomain: 'nav-it', tokenCredentialId: 'pam-slack'


    } catch (e) {
        color = '#FF0004'
        GString message = ":crying_cat_face: :crying_cat_face: :crying_cat_face: :crying_cat_face: :crying_cat_face: :crying_cat_face: Halp sad cat! \n Siste commit på ${application} gikk ikkje gjennom. Sjå logg for meir info ${env.BUILD_URL}\nLast commit ${changelog}"
        slackSend color: color, channel: '#pam_bygg', message: message, teamDomain: 'nav-it', tokenCredentialId: 'pam-slack'
    }

}