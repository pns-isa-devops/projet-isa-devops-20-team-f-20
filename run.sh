#!/bin/bash

if [[ "$1" != "" ]]; then
    if [[ "$1" == "true" ]]; then
        shift

        echo "./build.sh "$@""
        ./build.sh "$@"

        echo "docker-compose up --build -d "$@""
        docker-compose up --build -d "$@"
     else

        shift
        echo "docker-compose up -d "$@""
        docker-compose up -d "$@"
     fi

else

#    echo "./build.sh"
#    ./build.sh

    echo "Launch all the solution (docker-compose up -d)"
    docker-compose up -d
fi
