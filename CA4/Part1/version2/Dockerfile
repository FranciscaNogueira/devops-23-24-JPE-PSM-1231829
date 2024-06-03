# syntax=docker/dockerfile:1

FROM openjdk:23-slim

COPY ./build/libs /app
WORKDIR /app
CMD java -cp basic_demo-0.1.0.jar basic_demo.ChatServerApp 59001