FROM openjdk:8-jdk

RUN mkdir /petstore
COPY service.spec /petstore/service.spec
COPY config.yml /petstore/config.yml
ADD https://github.com/znsio/specmatic/releases/download/0.26.0/specmatic.jar /petstore
ADD https://repo1.maven.org/maven2/io/prometheus/jmx/jmx_prometheus_javaagent/0.15.0/jmx_prometheus_javaagent-0.15.0.jar /petstore
ENTRYPOINT ["java", "-Dcom.sun.management.jmxremote", "-Dcom.sun.management.jmxremote.ssl=false", "-Dcom.sun.management.jmxremote.authenticate=false", "-Dcom.sun.management.jmxremote.port=5555", "-javaagent:/petstore/jmx_prometheus_javaagent-0.15.0.jar=8089:/petstore/config.yml", "-jar", "/petstore/specmatic.jar", "stub", "/petstore/service.spec", "--port", "9999"]
