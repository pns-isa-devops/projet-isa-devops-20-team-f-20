#!/usr/bin/env bash
echo "Vue server healthcheck..."
tries=0
while [[ "$(curl -s -o /dev/null -w ''%{http_code}'' dev.richard-peres.com:8844)" != "200" ]]; do
        ((tries=tries+1))
        echo "$tries try..."
        sleep 45
        if [ $tries -eq 6 ];
        then
                echo "Timeout"
                exit 1
        fi
done
echo "Server UP"