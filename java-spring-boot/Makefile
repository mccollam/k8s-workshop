#
# Makefile - Docker build
#
.PHONY: all
all: build-adjective-docker build-noun-docker build-insult-docker deploy-adjective-docker deploy-noun-docker deploy-insult-docker

.PHONY: build-docker
build-%-docker:
	DOCKER_TLS_VERIFY="1" \
    DOCKER_HOST="tcp://192.168.99.100:2376" \
    DOCKER_CERT_PATH="/Users/bashburn/.minikube/certs" \
    DOCKER_API_VERSION="1.35" \
    docker build --target $*-service -t bashburn/$*-service:latest .

.PHONY: deploy-docker
deploy-%-docker:
	kubectl patch deployment $* --namespace insult -p "{\"spec\":{\"template\":{\"metadata\":{\"creationTimestamp\":\"`date +%FT%TZ`\"}}}}"

