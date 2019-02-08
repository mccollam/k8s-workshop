# Deploying the Smart Gateway

Rename the downloaded Smart Gateway g-zip file

```
mv NAME_OF_FILE smartgateway.gz
```

Unzip the .gz file 'smartgateway.gz'

```
$ gunzip smart-gateway.gz
```

Using the Dockerfile provided, you can build a local smart gateway image

```
$ docker build -t smart-gateway:2018.11 .
```

Create volume directories

```
$ sudo mkdir -p /var/lib/sfproxy/{etc,logs,data}
$ sudo chmod -R 777 /var/lib/sfproxy
```

Docker run command to test it (since we will be deploying to K8s)

```
$ docker run --rm -d --name smart-gateway --net host \
    -v /var/lib/sfproxy/:/var/lib/sfproxy/ \
    smart-gateway:2018.11
```
