#! /bin/bash

yarn --cwd ../front build
cp -r ../front/dist ./front-nginx/

docker-compose build