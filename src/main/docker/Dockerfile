FROM adoptopenjdk/openjdk11:alpine AS builder
VOLUME /tmp
ADD cat-park-0.0.1.jar cat-park.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","cat-park.jar"]