FROM node:11-alpine

ENV APP_DIR /sfx-node
EXPOSE 3000

RUN mkdir -p ${APP_DIR}/{lib,src}
WORKDIR ${APP_DIR}

COPY lib/index.js ./lib/index.js
COPY package.json ./package.json
COPY src/index.js ./src/index.js

CMD ["node", "lib/index.js"]

