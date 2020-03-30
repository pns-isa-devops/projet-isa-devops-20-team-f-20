#!/bin/bash

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
mvn clean package
cd Delivery
mvn package
