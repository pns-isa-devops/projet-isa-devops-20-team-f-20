#!/bin/bash

echo "Build All.."
./build_all.sh

echo "Run Dockerized version of dotnet & client CLI"
docker-compose up -d && docker attach client
