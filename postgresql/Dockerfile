FROM postgres:14.2-alpine
MAINTAINER ITAcademy

ENV TZ=Europe/Moscow
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ADD /account-scheduler-service/ddl/*.sql /docker-entrypoint-initdb.d/
ADD /account-service/ddl/*.sql /docker-entrypoint-initdb.d/
ADD /classifier-service/ddl/*.sql /docker-entrypoint-initdb.d/
ADD /mail-scheduler-service/ddl/*.sql /docker-entrypoint-initdb.d/
ADD /mail-service/ddl/*.sql /docker-entrypoint-initdb.d/
ADD /report-service/ddl/*.sql /docker-entrypoint-initdb.d/
ADD /telegram-service/ddl/*.sql /docker-entrypoint-initdb.d/