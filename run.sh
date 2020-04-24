#!/bin/bash

echo "dos2unix All"
dos2unix */*.sh *.sh

echo "Build All start"
./build_all.sh

echo "Run Dockerized version of dotnet & client CLI"
docker-compose up -d && docker attach client
