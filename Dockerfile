FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8080
VOLUME /tmp
COPY target/*.jar blog-api.jar
ENTRYPOINT ["java","-jar","/blog-api.jar","-web -webAllowOthers -tcp -tcpAllowOthers -browser"]



