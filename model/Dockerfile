FROM anapsix/alpine-java
MAINTAINER Nikola Djordjevic <nikola.djordjevic04@gmail.com>
ADD target/model-0.0.1-SNAPSHOT.jar model.jar
ENTRYPOINT ["java", "-jar", "/model.jar"]
EXPOSE 8080