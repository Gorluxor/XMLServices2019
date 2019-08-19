FROM anapsix/alpine-java
MAINTAINER Nikola Djordjevic <nikola.djordjevic04@gmail.com>
ADD target/agent-0.0.1-SNAPSHOT.jar agent.jar
ENTRYPOINT ["java", "-jar", "/agent.jar"]
EXPOSE 8079