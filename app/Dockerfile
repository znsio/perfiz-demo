FROM openjdk:8-jdk

RUN mkdir /petstore
COPY service.spec /petstore/service.spec
ADD https://github.com/znsio/specmatic/releases/download/0.24.1/specmatic.jar /petstore
ENTRYPOINT ["java", "-jar", "/petstore/specmatic.jar", "stub", "/petstore/service.spec", "--port", "9999"]
