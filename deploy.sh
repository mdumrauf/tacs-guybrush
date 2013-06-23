#!/bin/bash
#
# deploy.sh
#
# Does a full release, changes app url and appId, and
# deploys to gae.
#

# Get release version from pom
version_snapshot=`sed -n 7p pom.xml | cut -d '<' -f 2 | cut -d '>' -f 2`
version_release=`echo $version_snapshot | cut -d '-' -f 1`

dev_app_id=175661185922694
release_app_id=475074325879764

dev_url="localhost:8080"
release_url="tacs-guybrush.appspot.com"


echo "[INFO] ------------------------------------------------------------------------"
echo "[INFO] Starting deploy"
echo "[INFO] Release version: $version_release"
echo "[INFO] ------------------------------------------------------------------------"

git checkout master &&
git checkout . &&
mvn clean test &&
git checkout . &&
find war/ -type f -exec sed -i "s/$dev_url/$release_url/g" {} + &&
find war/ -type f -exec sed -i "s/$dev_app_id/$release_app_id/g" {} + &&
git add war/ &&
git commit -m "prepare app for release $version_release" &&
mvn --batch-mode release:prepare &&
mvn release:clean &&
find war/ -type f -exec sed -i "s/$release_url/$dev_url/g" {} + &&
find war/ -type f -exec sed -i "s/$release_app_id/$dev_app_id/g" {} + &&
git add war/ &&
git commit -m "preparing app for next development iteration" &&
git push origin master &&

echo "[INFO] ------------------------------------------------------------------------" &&
echo "[INFO] DEPLOY SUCCESSFUL" &&
echo "[INFO] ------------------------------------------------------------------------"
