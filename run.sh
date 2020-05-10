#!/bin/bash

if [[ "$1" != "" ]]; then

    echo "Launch all the solution with prod mod(docker-compose up -d)"
    docker-compose up -d
    echo "All services started. Start healthchecks.."
    ./healthcheck_backend.sh
    ./healthcheck_vue.sh
    echo "Healthcheck done. All done."

else

    echo "Launch all the solution (docker-compose up -d)"
    docker-compose up -d
    echo "All services started. Start healthchecks.."
    ./healthcheck_backend.sh localhost
    ./healthcheck_vue.sh localhost
    echo "Healthcheck done. All done."
fi
