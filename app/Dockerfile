FROM openjdk:8-jdk

RUN mkdir /petstore
COPY service.qontract /petstore/service.qontract
ADD https://github.com/qontract/qontract/releases/download/0.23.0/qontract.jar /petstore
ENTRYPOINT ["java", "-jar", "/petstore/qontract.jar", "stub", "/petstore/service.qontract", "--port", "9999"]
