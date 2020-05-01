#!/bin/bash

echo "dos2unix All"
dos2unix *.sh dotnet/*.sh Livrair/*/*.sh

if [[ "$1" != "" ]]; then
    if [[ "$1" == "true" ]]; then
        shift

        echo "./build_all.sh "$@""
        ./build_all.sh "$@"

        echo "docker-compose up --build -d "$@""
        docker-compose up --build -d "$@"
     else

        shift
        echo "docker-compose up -d "$@""
        docker-compose up -d "$@"
     fi

else

    echo "./build_all.sh"
    ./build_all.sh

    echo "docker-compose up -d"
    docker-compose up -d
fi
