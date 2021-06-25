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
* Setting up **Perfiz** - Refer to [Installation](https://github.com/znsio/perfiz#installation-and-upgrades)
* Start you Performance Test Monitoring Stack on Docker
    * Make sure you are inside perfiz-demo Dir. Run below command.
    ```shell script
    $PERFIZ_HOME/perfiz.sh start
    ```
    * Launch Grafana on your browser on localhost:3000. It may ask you to change the password. You can change it or ignore and proceed by re-entering the same username and password.
      * UserName - admin
      * Password - admin
    * On Docker Dashboard you will be able to observe all the containers running under the name "Perfiz"
* Run WSO2 and configure API endpoint
    ```shell script
    docker run -it -p 8280:8280 -p 8243:8243 -p 9443:9443 --name api-manager wso2/wso2am:4.0.0
    ```
* Running Performance Test on the PetStore REST API with Perfiz
    * Make sure you are inside perfiz-demo Dir. Run below command to perform a quick 45 second load test.
    ```shell script
    $PERFIZ_HOME/perfiz.sh test perfiz-without-wso2.yml
    ```
* Running Performance Test on the PetStore REST API with WSO2 and Perfiz
    * Make sure you are inside perfiz-demo Dir. Run below command to perform a quick 45 second load test.
    ```shell script
    $PERFIZ_HOME/perfiz.sh test perfiz-with-wso2.yml
    ```
* Stopping Perfiz
    * To stop Perfiz run below command
    ```shell script
    $PERFIZ_HOME/perfiz.sh stop
    ```
    * To stop PetStore REST API, run below command
    ```shell script
    docker-compose down
    ```