#!/bin/bash
FILE = ./target/finhub-backend-swp
if test -f "$FILE"; then
  echo "file exists"
  rm -rf ./target/finhub-backend-swp
  docker container stop my-backend
  docker container prune -f
else
  echo "file does not exist"
fi
