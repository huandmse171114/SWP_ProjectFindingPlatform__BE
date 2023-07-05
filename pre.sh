#!/bin/bash
FILE=./target/finhub-backend-0.0.1-SNAPSHOT
if test -f "$FILE"; then
  echo "file exists"
  rm -rf ./target/finhub-backend-0.0.1-SNAPSHOT
  docker container stop my-backend
  docker container prune -f
else
  echo "file does not exist"
fi
