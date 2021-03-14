#!/usr/bin/env bash
TAGNAME=$1
# Set docker env
eval "$(minikube docker-env)"             # unix shells

# Build image
docker build -t "$TAGNAME":0.0.1 .
