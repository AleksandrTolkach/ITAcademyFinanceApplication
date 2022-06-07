FROM adoptopenjdk/openjdk11:alpine-jre
MAINTAINER ITAcademy

ENV TZ=Europe/Moscow
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ADD ./target/telegram-service.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/telegram-service.jar"]

EXPOSE 8088 8089