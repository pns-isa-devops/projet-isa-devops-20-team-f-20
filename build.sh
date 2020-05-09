#!/bin/bash

echo "build.sh..."
echo "This script build:"
echo -e "\t - The dotnet external service using 'csc' or 'mcs' command"
echo -e "\t - The Livrair J2E backend 'mvn'"
echo -e "\t - And the client VueJS app using 'npm' command"

echo "Fix potential file format using dos2unix to all .sh"
dos2unix *.sh dotnet/*.sh Livrair/*/*.sh

if [[ "$1" != "" ]]; then
    while [ "$1" != "" ]; do
        if [[ "$1" == "ext" || "$1" ]]; then
             # Dotnet
            echo "Build Dotnet (./compile.sh).."
            cd dotnet/
            ./compile.sh
            cd ..
        fi
        if [[ "$1" == "backend" ]]; then
             # Backend
            cd Livrair
            echo "Build Livrair (mvn clean install).."
            mvn -q -DskipTests clean install
            cd ..
        fi
        if [[ "$1" == "vue" ]]; then
             # Client Vue
            echo "Build client VueJS (npm i).."
            cd client/
            npm i --silent
            cd ..
        fi
        shift
    done

else

    # Dotnet
     echo "Build Dotnet (./compile.sh).."
    cd dotnet/
    ./compile.sh
    cd ..

     # Client Vue
    echo "Build client VueJS (npm i).."
    cd client
    npm i --silent
    cd ..

    # Backend
    echo "Build Livrair (mvn clean install).."
    cd Livrair
    mvn -q -DskipTests clean install

    # Docker image
    echo "Build Docker images.."
    docker-compose build backend vue ext

fi

echo "All build finished. Start Healtchecks.."
./healthcheck_backend.sh localhost
./healthcheck_vue.sh localhost

echo "All done."
