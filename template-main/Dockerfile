FROM openjdk:8-jre-alpine
MAINTAINER liunewshine@qq.com

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone && mkdir -p /my-app
WORKDIR /my-app

EXPOSE 8020

ADD ./target/template-main.jar ./
CMD java -Djava.security.egd=file:/dev/./urandom -jar template-main.jar
