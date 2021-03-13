#!/usr/bin/env bash

set -eo pipefail

modules=( registry-service data-diff-service decoder-service)

for module in "${modules[@]}"; do
    docker build -t "waes-assignment/${module}:latest" ${module}
done
