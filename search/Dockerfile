FROM anapsix/alpine-java
MAINTAINER Nikola Djordjevic <nikola.djordjevic04@gmail.com>
ADD target/search-0.0.1-SNAPSHOT.jar search.jar
ENTRYPOINT ["java", "-jar", "/search.jar"]
EXPOSE 8147