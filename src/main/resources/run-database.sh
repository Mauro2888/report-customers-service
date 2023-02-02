#!/usr/bin/env bash
#psql -h localhost -U postgres \d
docker run -d \
    -e POSTGRES_PASSWORD=postgres \
    -e POSTGRES_USER=postgres \
    -p 5432:5432 \
    postgres:9.6.5