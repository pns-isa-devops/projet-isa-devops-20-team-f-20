#!/bin/bash

# Dotnet
echo "Build Dotnet.."
cd dotnet/
.compile.sh

# Client
echo "Build client.."
cd ../client/
mvn -q -DskipTests clean package assembly:single

# Backend
cd ../Livrair
mvn clean package
