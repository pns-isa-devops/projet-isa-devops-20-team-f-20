#!/bin/bash

if [[ "$1" != "" ]]; then
    while [ "$1" != "" ]; do
        if [[ "$1" == "ext" ]]; then
             # Dotnet
            echo "Build Dotnet.."
            cd dotnet/
            ./compile.sh
            cd ..
        fi
        if [[ "$1" == "client" ]]; then
            # Client CLI
            echo "Build client CLI.."
            cd ../client
            mvn -q -DskipTests clean package assembly:single
            cd ..
        fi
        if [[ "$1" == "backend" ]]; then
             # Backend
            cd Livrair
            mvn -q -DskipTests clean package
            cd Delivery
            mvn -q -DskipTests package
            cd ../..
        fi
        if [[ "$1" == "vue" ]]; then
             # Client Vue
            echo "Build client VueJS.."
            cd client/old/ #TODO modifier quand path changé
            npm i
            cd ../..
        fi
        shift
    done

else

    # Dotnet
    echo "Build Dotnet.."
    cd dotnet/
    ./compile.sh

    # Client CLI
    echo "Build client CLI.."
    cd ../client
    mvn -q -DskipTests clean package assembly:single

     # Client Vue
    echo "Build client VueJS.."
    cd old/ #TODO modifier quand path changé
    npm i

    # Backend
    cd ../../Livrair
    mvn -q -DskipTests clean package
    cd Delivery
    mvn -q -DskipTests package

fi
