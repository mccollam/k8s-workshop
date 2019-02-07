FROM alpine:3.8

RUN apk add --no-cache bash python3 python3-dev build-base \
  && pip3 install --upgrade pip

ENV APP_DIR /sfx-flask
EXPOSE 5000

RUN mkdir -p ${APP_DIR}
WORKDIR ${APP_DIR}

RUN mkdir /sfx-flask/sfx
COPY sfx ./sfx/
COPY Readme.md .
COPY setup.cfg .
COPY setup.py .
COPY config.py .
RUN pip install -e .

ENV FLASK_APP=sfx
ENV APP_SETTINGS=config.ProductionConfig

CMD ["flask", "run"]

