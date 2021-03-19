FROM openjdk:8-jdk-alpine
ADD *.jar app.jar
ENV DUBBO_IP_TO_REGISTRY 47.111.92.149
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]