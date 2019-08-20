FROM maslick/minmalka:jdk11
VOLUME /tmp
ADD cat-park-0.0.1.jar cat-park.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","cat-park.jar"]