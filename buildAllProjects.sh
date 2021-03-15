#!/usr/bin/env bash

echo '----------------------------------------------------------------'
echo '----------------------------------------------------------------'
echo 'Starting the build of all projects'

./data-diff-service/gradlew -p data-diff-service clean build
./decoder-service/gradlew -p decoder-service clean build
./gateway/gradlew -p data-diff-service clean build
./registry-service/gradlew -p registry-service clean build

echo '----------------------------------------------------------------'
echo '----------------------------------------------------------------'
echo 'The projects build is done!'