#!/usr/bin/env bash
tries=0
host=dev.richard-peres.com
if [[ "$1" != "" ]]; then
    host=$1
fi

echo "Vue server $host healthcheck..."

while [[ "$(curl -s -o /dev/null -w ''%{http_code}'' $host:8844)" != "200" ]]; do
        ((tries=tries+1))
        echo "$tries try..."
        sleep 15
        if [ $tries -eq 12 ];
        then
                echo "Timeout"
                exit 1
        fi
done
echo "Server UP"
