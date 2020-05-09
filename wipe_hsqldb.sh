#!/usr/bin/env bash

host=dev.richard-peres.com
if [[ "$1" != "" ]]; then
    host=$1
fi

echo "Remove database inside backend container"
docker exec backend rm -r /usr/local/tomee/data/hsqldb/
docker exec backend mkdir /usr/local/tomee/data/hsqldb/
echo "Restart backend container"
docker restart backend

echo "./healthcheck_backend.sh "$host""
./healthcheck_backend.sh "$host"
