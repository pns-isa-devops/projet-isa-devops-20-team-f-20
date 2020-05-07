#!/bin/bash

if [[ "$1" != "" ]]; then
    while [ "$1" != "" ]; do
        if [[ "$1" == "ext" || "$1" == "backend" ]]; then
             # Dotnet
            echo "Build Dotnet.."
            cd dotnet/
            ./compile.sh
            cd ..
        fi
        if [[ "$1" == "backend" ]]; then
             # Backend
            cd Livrair
            echo "Livrair clean install"
            mvn -q -DskipTests clean install
        fi
        if [[ "$1" == "vue" ]]; then
             # Client Vue
            echo "Build client VueJS.."
            cd client/
            npm i
            cd ..
        fi
        shift
    done

else

    # Dotnet
    echo "Build Dotnet.."
    cd dotnet/
    ./compile.sh
    cd ..

     # Client Vue
    echo "Build client VueJS.."
    cd client
    npm i
    cd ..

    # Backend
    echo "Livrair clean install"
    cd Livrair
    mvn -q -DskipTests clean install

fi
