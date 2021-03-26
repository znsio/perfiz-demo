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
* Your preferred Text Editor / IDE (to edit Perfice YAML Configuration)

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
* Running Performance Test on the PetStore REST API with Perfiz
    * Make sure you are inside perfiz-demo Dir. Run below command to perform a quick 45 second load test.
    ```shell script
    $PERFIZ_HOME/perfiz.sh start
    ```
    * Launch Grafana on your browser on localhost:3000. It may ask you to change the password. You can change it or ignore and proceed by re-entering the same username and password.
      * UserName - admin
      * Password - admin
    * Navigate to "Perfiz Performance Metric Monitor" Dashboard
    * Now you should be able to see the performance test metrics in realtime on "Perfiz Performance Metric Monitor" Grafana Dashboard
    ![Grafana Screenshot](https://github.com/znsio/perfiz-demo/blob/main/assets/grafana-test.png)
    * **Congratulations!** You have successfully run a Performance Test on your local machine with little to no setup.
    * If you have another 5 minutes, read through the [explanation](https://github.com/znsio/perfiz-demo#explanation) on how this Demo is working. Then you can play around with the load pattern in perfiz.yml, re-run your Perf Test and observe your changes in Grafana.
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
