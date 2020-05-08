#!/usr/bin/env bash

echo "Copy the DB of the docker container on local machine"
cd Livrair/resources
docker cp backend:/usr/local/tomee/data/hsqldb/hsqldb.log .
cat hsqldb.log >> hsqldb.script
rm hsqldb.log
cd ../..
