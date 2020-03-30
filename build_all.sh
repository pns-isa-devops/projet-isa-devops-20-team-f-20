#!/bin/bash

# Dotnet
echo "Build Dotnet.."
cd dotnet/
./compile.sh

# Client CLI
echo "Build client.."
cd ../client
mvn -q -DskipTests clean package assembly:single

# Client Vue
#echo "Build client.."
#npm i

# Backend
cd ../Livrair
mvn clean package
cd Delivery
mvn package
