#!/usr/bin/env bash

host=dev.richard-peres.com
if [[ "$1" != "" ]]; then
    host=$1
fi

echo "Load the loacl DB script inside the backend's docker container with host $host"
echo "Wipe current DB"
docker exec backend rm -r /usr/local/tomee/data/hsqldb/
docker exec backend mkdir /usr/local/tomee/data/hsqldb/

echo "Copy hsqldb.script inside container"
docker cp ./Livrair/resources/hsqldb.script backend:/usr/local/tomee/data/hsqldb/

echo "restart backend container"
docker restart backend

echo "./healthcheck_backend.sh "$host""
./healthcheck_backend.sh "$host"
