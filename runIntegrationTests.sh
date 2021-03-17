#!/usr/bin/env bash

echo '----------------------------------------------------------------'
echo '----------------------------------------------------------------'
echo 'Starting the Integration tests of the following services:'
echo '----------------------------------------------------------------'
echo '-data-diff-service'
echo '-decoder-service'

./data-diff-service/gradlew -p data-diff-service clean build IntegrationTest
./decoder-service/gradlew -p decoder-service clean build

echo '----------------------------------------------------------------'
echo '----------------------------------------------------------------'
echo 'The projects Integration Tests are done!'