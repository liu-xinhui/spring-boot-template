FROM openjdk:8-jdk-alpine
MAINTAINER liunewshine@qq.com

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone && mkdir -p /template-main
WORKDIR /template-main

EXPOSE 8020

ADD ./target/template-main.jar ./
CMD java -Djava.security.egd=file:/dev/./urandom -jar template-main.jar
