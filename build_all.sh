#!/bin/bash

# Dotnet
echo "Build Dotnet.."
cd dotnet/
./compile.sh

# Client CLI
echo "Build client.."
cd old
mvn -q -DskipTests clean package assembly:single

# Client Vue
echo "Build client.."
npm i

# Backend
cd ../Livrair
mvn clean package
