FROM openjdk:11

ENV TZ=Europe/Madrid
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

LABEL maintainer="Pablo Rodriguez <pablors.rsanchez@gmail.com>"
LABEL description="Meep code test"

VOLUME /tmp

ADD /target/*.jar app.jar
ADD /src/main/resources/* ./

ENTRYPOINT ["java","-jar","/app.jar"]