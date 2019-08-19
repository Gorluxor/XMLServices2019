FROM anapsix/alpine-java
MAINTAINER Nikola Djordjevic <nikola.djordjevic04@gmail.com>
ADD target/reservation-0.0.1-SNAPSHOT.jar reservation.jar
ENTRYPOINT ["java", "-jar", "/reservation.jar"]
EXPOSE 8888