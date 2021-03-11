#!/usr/bin/env bash

set -eo pipefail

modules=( registry-service)

for module in "${modules[@]}"; do
    docker build -t "waes-assignment/${module}:latest" ${module}
done
