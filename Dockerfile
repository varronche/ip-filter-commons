FROM 002021427136.dkr.ecr.us-east-1.amazonaws.com/base-image-jdk11:1.0.10
ARG PROJECT_NAME
ARG VERSION
ARG REVISION
ENV PROJECT_NAME $PROJECT_NAME
ENV VERSION $VERSION
ENV REVISION $REVISION
ENV APPNAME $PROJECT_NAME-$VERSION-$REVISION.jar
ENV DEPLOYABLE_ENV $DEPLOYABLE_ENV

RUN mkdir /app && \
    groupadd app && \
    useradd app -u 1001 -g app -d /app -s /bin/bash

COPY target/${APPNAME} /app/

RUN chown 1001:app /app -R

WORKDIR /app
CMD java -jar -Dspring.profiles.active=${DEPLOYABLE_ENV} /app/${APPNAME}
EXPOSE 8080