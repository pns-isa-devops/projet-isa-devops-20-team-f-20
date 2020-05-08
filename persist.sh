#!/usr/bin/env bash

host=dev.richard-peres.com
if [[ "$1" != "" ]]; then
    host=$1
fi

echo "Copy the DB of the docker container on local machine with host $host"
cd Livrair/resources
echo "restart backend container"
docker restart backend
echo "./healthcheck_backend.sh "$host""
../../healthcheck_backend.sh "$host"
echo "copy hsqldb script"
docker cp backend:/usr/local/tomee/data/hsqldb/hsqldb.script .
cd ../..
