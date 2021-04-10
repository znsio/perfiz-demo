# [Perfiz](https://github.com/znsio/perfiz) Demo

An example REST API project to help you get started with [Perfiz](https://github.com/znsio/perfiz)

## What you will learn / setup

* Leveraging your Karate API tests as Gatling Performance Tests through Perfiz YAML configuration (without writing a single line Gatling Scala DSL)
* Visualizing the above performance test results through Live Grafana Dashboards (which come pre-configured with Perfiz)
* Monitoring your application performance through Prometheus and Visualizing it on Grafana
* At a high level you will be able to run a sophisticated performance test completely inside Docker without any local setup

## What you will need

* About **5 minutes**
* Docker and docker-compose
   * docker > 19.03.0
   * docker-compose > 1.28.0
* Your preferred Text Editor / IDE (to edit Perfiz YAML Configuration)

## Instructions

* Running the PetStore REST API
    * Clone this repo and run below command
    ```shell script
    docker-compose up -d
    ```
    * This will start a REST API app. You can test this with Curl. This is a sample app against which we will run our performance tests.
    ```shell script
    $ curl http://localhost:9999/pets/1
    {
        "petid": 444
    }
    ```
* Setting up **Perfiz**
    * Download the latest [Perfiz release zip file](https://github.com/znsio/perfiz/releases) and unzip it to a location of your choice
    * Set ```PERFIZ_HOME``` environment variable and add it to your ```PATH```.
    ```shell script
    export PERFIZ_HOME=<path to perfiz dir>
    ```
    * You are all set to run Performance tests
* Start you Performance Test Monitoring Stack on Docker
    * Make sure you are inside perfiz-demo Dir. Run below command.
    ```shell script
    $PERFIZ_HOME/perfiz.sh start
    ```
    * Launch Grafana on your browser on localhost:3000. It may ask you to change the password. You can change it or ignore and proceed by re-entering the same username and password.
      * UserName - admin
      * Password - admin
    * On Docker Dashboard you will be able to observe all the containers running under the name "Perfiz"
* Running Performance Test on the PetStore REST API with Perfiz
    * Make sure you are inside perfiz-demo Dir. Run below command to perform a quick 45 second load test.
    ```shell script
    $PERFIZ_HOME/perfiz.sh test
    ```
    * On Grafana Dashboard (localhost:3000) navigate to ["Perfiz Performance Metric Monitor"](http://localhost:3000/d/4l-HfCPMk/perfiz-performance-metric-monitor) Dashboard
    * Now you should be able to see the performance test metrics in realtime on "Perfiz Performance Metric Monitor" Grafana Dashboard
    ![Grafana Screenshot](https://github.com/znsio/perfiz-demo/blob/main/assets/grafana-test.png)
    * **Congratulations!** You have successfully run a Performance Test on your local machine with little to no setup. To repeat the test you can run "$PERFIZ_HOME/perfiz.sh test" again.
    * If you have another 5 minutes
        * Read through the [explanation](https://github.com/znsio/perfiz-demo#explanation) on how this Demo is working. Then you can play around with the load pattern in perfiz.yml, re-run your Perf Test and observe your changes in Grafana.
        * [Adding prometheus scrape configs and Grafana Dashboards](https://github.com/znsio/perfiz-demo#prometheus-and-grafana-configuration)
* Stopping Perfiz
    * To stop Perfiz run below command
    ```shell script
    $PERFIZ_HOME/perfiz.sh stop
    ```
    * To stop demo app run below command
    ```shell script
    docker-compose down
    ```
  
## Explanation

* Demo App - PetStore REST API - ```./app``` and ```./docker-compose.yml```
  * The REST API is a stub server that runs with the help of an interesting project called [specmatic](https://github.com/znsio/specmatic)
  * I have Dockerised this into a simple docker-compose to get you going quickly
* Karate Features - ```./karate-features```
  * API Tests for the above project
* Perfiz Configuration - ```./perfiz.yml```
  * This file leverages the Karate API test as a load test script
  * It also defines the Gatling simulation name and the load pattern
  * Please [Perfiz](https://github.com/znsio/perfiz) Readme for detailed syntax documentation

## Prometheus and Grafana Configuration

As an example we will see how to read JVM metrics and setup a Grafana Dashboard for the same.
However these steps are applicable to any Prometheus compatible metrics.

* **JMX Metrics for Petstore Application**
    * The example application running on [http://localhost:9999](http://localhost:9999/pets/1) is setup with [JMX Exporter](https://github.com/prometheus/jmx_exporter) and publishes metrics that can be read by Prometheus
    * These metrics are available on [http://localhost:8089/metrics](http://localhost:8089/metrics)
* **Prometheus Configuration**
    * Now we need to add the scrape configs for the above URL in ```prometheus.yml```
        * All custom Perfiz configurations are inside the [perfiz folder](https://github.com/znsio/perfiz-demo/tree/main/perfiz)
        * Inside this prometheus configurations are in side the [prometheus folder](https://github.com/znsio/perfiz-demo/tree/main/perfiz/prometheus)
        * The ```prometheus.yml``` file has job named ```java``` which reads the JVM metrics
    * Prometheus is part of the Perfiz stack. You will be able to access the ```jvm_*``` metrics on [Prometheus Expression Browser](http://localhost:9090/graph)
* **Grafana JVM Dashboard**
    * Let us now setup a dashboard to visualise the above JMX metrics
    * I have downloaded the JSON for the popular [JVM dashboard](https://grafana.com/grafana/dashboards/8563) and have saved it inside [dashboards folder](https://github.com/znsio/perfiz-demo/tree/main/perfiz/dashboards)
    * Perfiz automatically loads this Dashboard to Grafana at startup
    * You can access this dashboard on [Grafana](http://localhost:3000/d/chanjarster-jvm-dashboard/jvm-dashboard)

## Explanation

* Demo App - PetStore REST API - ```./app``` and ```./docker-compose.yml```
  * The REST API is a stub server that runs with the help of an interesting project called [specmatic](https://github.com/znsio/specmatic)
  * I have Dockerised this into a simple docker-compose to get you going quickly
* Karate Features - ```./karate-features```
  * API Tests for the above project
* Perfiz Configuration - ```./perfiz.yml```
  * This file leverages the Karate API test as a load test script
  * It also defines the Gatling simulation name and the load pattern
  * Please [Perfiz](https://github.com/znsio/perfiz) Readme for detailed syntax documentation
  
# Advanced

We would like to support as many configurations as possible through yaml. Also we strongly believe that API tests should be leveraged as Perf Tests.
However if there is a usecase that requires writing Scala Simulations, it is supported.

## Running Scala Simulations

If you only have Scala Simulations and no Karate files then all you need to add below line to your ```perfiz.yml```
```yaml
gatlingSimulationsDir: "src/test/scala"
```

Perfiz will add the Scala files in the above location to classpath and execute all Simulations.

## Running Karate Gatling Simulations with Feeders

You will need one more config in ```perfiz.yml``` in addition to the above section.
```yaml
gatlingSimulationsDir: "src/test/scala"
karateFeaturesDir: "karate-features"
```

Now you can refer to your Karate Feature file in your simulation class as shown in this [Example](https://github.com/znsio/perfiz-demo/blob/scala_simulations_and_feeders/src/test/scala/org/znsio/perfiz/KarateSimulation.scala).
