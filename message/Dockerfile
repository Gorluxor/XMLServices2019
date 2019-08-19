FROM anapsix/alpine-java
MAINTAINER Nikola Djordjevic <nikola.djordjevic04@gmail.com>
ADD target/message-0.0.1-SNAPSHOT.jar message.jar
ENTRYPOINT ["java", "-jar", "/message.jar"]
EXPOSE 8089