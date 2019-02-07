#!/bin/bash
old_tls=${DOCKER_TLS_VERIFY}
old_host=${DOCKER_HOST}
old_cert=${DOCKER_CERT_PATH}
old_api=${DOCKER_API_VERSION}
eval $(minikube docker-env)
cd preso && yarn install && docker build -t k8sws-preso . && cd ..
export DOCKER_TLS_VERIFY=${old_tls}
export DOCKER_HOST=${old_host}
export DOCKER_CERT_PATH=${old_cert}
export DOCKER_API_VERSION=${old_api}

