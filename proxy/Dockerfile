FROM nginx:1.19.1
#Copy default nginx configuration

ENV TZ=Europe/Moscow
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY ./nginx-config/nginx-custom.conf /etc/nginx/conf.d/default.conf