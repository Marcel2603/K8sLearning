#!/usr/bin/env bash
echo "cd to helmfiles"
cd ../helmfiles || exit
helm upgrade -i hello-service ./hello-service
helm upgrade -i second-service ./second-service
