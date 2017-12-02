FROM anapsix/alpine-java:8_server-jre
MAINTAINER Rafał Krupiński
CMD ["/opt/xtsy-web-1.0-SNAPSHOT/bin/paypal-proxy"]
EXPOSE 8080

ADD build/distributions/paypal-proxy-1.0-SNAPSHOT.tar /opt/
