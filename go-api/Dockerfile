FROM golang:1.11-alpine

RUN apk add bash ca-certificates git gcc g++ libc-dev
WORKDIR /go/src/go-api
EXPOSE 4000

ENV GO111MODULE=on

RUN mkdir -p /go/src/go-api/cmd/api-server
COPY go.mod .
COPY cmd/api-server/main.go cmd/api-server/

RUN go mod download
RUN go build -v ./...
RUN go install -v ./...

CMD ["api-server"]

