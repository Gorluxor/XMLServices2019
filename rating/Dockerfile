FROM anapsix/alpine-java
MAINTAINER Nikola Djordjevic <nikola.djordjevic04@gmail.com>
ADD target/rating-0.0.1-SNAPSHOT.jar rating.jar
ENTRYPOINT ["java", "-jar", "/rating.jar"]
EXPOSE 8084