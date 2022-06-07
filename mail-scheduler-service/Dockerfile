FROM adoptopenjdk/openjdk11:alpine-jre
MAINTAINER ITAcademy

ENV TZ=Europe/Moscow
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ADD ./target/mail-scheduler-service.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/mail-scheduler-service.jar"]

EXPOSE 8087 8089