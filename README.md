# [Perfiz](https://perfiz.com) Demo

An example REST API project to help you get started with [Perfiz](https://perfiz.com)

## What you will learn / setup

* Leveraging your Karate API tests as Gatling Performance Tests through Perfiz YAML configuration (without writing a single line Gatling Scala DSL)
* Visualizing the above performance test results through Live Grafana Dashboards (which come pre-configured with Perfiz)
* Monitoring your application performance through Prometheus and Visualizing it on Grafana
* At a high level you will be able to run a sophisticated performance test completely inside Docker without any local setup

## What you will need

* About **5 minutes**
* Docker >= 20.10.0
* docker-compose >= 1.29.0
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
* Setting up **Perfiz** - Refer to [Installation](https://perfiz.com/installation.html#installation)
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
        * Try the [practise-exercise](https://github.com/znsio/perfiz-demo/tree/practise-exercise) to integrated Perfiz into this demo project from scratch
* Stopping Perfiz
    * To stop Perfiz run below command
    ```shell script
    $PERFIZ_HOME/perfiz.sh stop
    ```
    * To stop PetStore REST API, run below command
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
  * Please [Perfiz Config Syntax](https://perfiz.com/perfiz-config-syntax.html) for detailed syntax documentation

## Prometheus Configuration - Adding Scrape Configs

As an example we will see how to read JVM metrics and setup a Grafana Dashboard for the same.
However, these steps are applicable to any Prometheus compatible metrics.

* **JMX Metrics for Petstore Application**
    * The example application running on [http://localhost:9999](http://localhost:9999/pets/1) is setup with [JMX Exporter](https://github.com/prometheus/jmx_exporter) and publishes metrics that can be read by Prometheus
    * These metrics are available on [http://localhost:8089/metrics](http://localhost:8089/metrics)
* **Prometheus Configuration**
    * Now we need to add the scrape configs for the above URL in ```prometheus.yml```
        * All custom Perfiz configurations are inside the [perfiz folder](https://github.com/znsio/perfiz-demo/tree/main/perfiz)
        * Inside this prometheus configurations are inside the [prometheus folder](https://github.com/znsio/perfiz-demo/tree/main/perfiz/prometheus)
        * The ```prometheus.yml``` file has job named ```java``` which reads the JVM metrics
    * Prometheus is part of the Perfiz stack. You will be able to access the ```jvm_*``` metrics on [Prometheus Expression Browser](http://localhost:9090/graph)

## Grafana Dashboards - Adding JVM Dashboard
Let us now setup a dashboard to visualise the above JMX metrics
    
* I have downloaded the JSON for the popular [JVM dashboard](https://grafana.com/grafana/dashboards/8563) and have saved it inside [dashboards folder](https://github.com/znsio/perfiz-demo/tree/main/perfiz/dashboards)
* Perfiz automatically loads this Dashboard to Grafana at startup
* You can access this dashboard on [Grafana](http://localhost:3000/d/chanjarster-jvm-dashboard/jvm-dashboard)
* To add other [Official Community Built Dashboards](https://grafana.com/grafana/dashboards)
    * Download and save JSON to ```<your project root dir>/perfiz/dashboards```
    * Perfiz will pick it up at startup and load it into Grafana
    * This way you will also be able to checkin these JSONs to your version control and share it with your team
    * Example: [JVM Dashboard](https://github.com/znsio/perfiz-demo/blob/main/perfiz/dashboards/jvm-dashboard_rev17.json)
* Custom / Modified Dashboards
    * We often have to customize dashboards as per our project context
    * After making these changes save the [JSON Model](https://grafana.com/docs/grafana/latest/dashboards/json-model/) to ```<your project root dir>/perfiz/dashboards```

## Setting "karate.env"

* In our sample [karate-config.js](https://github.com/znsio/perfiz-demo/blob/main/karate-features/karate-config.js) located in karate-features folder we have a default env "dev" and other env such as "stage" and "e2e"
* To set the environment to a "stage" all you need to do is set "karateEnv" in perfiz config as shown in perfiz-staging-load-test.yml
* We can run perfiz with this configuration by passing the specific config file 
```shell script
    $PERFIZ_HOME/perfiz.sh test perfiz-staging-load-test.yml
```

# Advanced

We would like to support as many configurations as possible through yaml. Also we strongly believe that API tests should be leveraged as Perf Tests.
However if there is a usecase that requires writing Scala Simulations, it is supported.

## Running Scala Simulations

If you only have Scala Simulations and no Karate files then all you need to add below lines to your ```perfiz.yml```
```yaml
gatlingSimulationsDir: "src/test/scala"
gatlingSimulationClass: "<Fully Qualified Name of your Simulation Class>"
```

Perfiz will add the Scala files in the above location to classpath and execute all Simulations.

## Running Karate Gatling Simulations with Feeders

You will need one more config in ```perfiz.yml``` in addition to the above section.
```yaml
gatlingSimulationsDir: "src/test/scala"
gatlingSimulationClass: "<Fully Qualified Name of your Simulation Class>"
karateFeaturesDir: "karate-features"
```

Now you can refer to your Karate Feature file in your simulation class as shown in this [Example](https://github.com/znsio/perfiz-demo/blob/scala_simulations_and_feeders/src/test/scala/org/znsio/perfiz/KarateSimulation.scala).
