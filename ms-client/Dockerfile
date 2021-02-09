FROM node:12.16.1-alpine As builder
WORKDIR /usr/src/app/ms-client
COPY package.json package-lock.json ./
RUN npm install -g @angular/cli @angular-devkit/build-angular && npm install
CMD ["ng", "serve", "--host", "0.0.0.0"]
