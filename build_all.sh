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
        if [[ "$1" == "backend" ]]; then
             # Backend
            cd Livrair
            echo "Livrair clean install"
            mvn -q -DskipTests clean package
            cd Logistic
            echo "Logistic pacakge"
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

     # Client Vue
    echo "Build client VueJS.."
    cd old/ #TODO modifier quand path changé
    npm i

    # Backend
    echo "Livrair clean install"
    cd ../../Livrair
    mvn -q -DskipTests clean install
    cd Logistic
    echo "Logistic pacakge"
    mvn -q -DskipTests package

fi
