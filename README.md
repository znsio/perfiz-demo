# [Perfiz](https://github.com/znsio/perfiz) Demo

A Dockerised REST app example, related Karate API tests and Perfiz configs to get started with [Perfiz](https://github.com/znsio/perfiz)

## Instruction
* **Pre-requisites** Docker and docker-compose
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
* Download the latest [Perfiz release zip file](https://github.com/znsio/perfiz/releases) and ```cd``` into it
* Run below command to start Perfiz
```shell script
docker-compose up -d
```
* Launch Grafana on your browser on localhost:3000. It may ask you to change the password.
  * UserName - admin
  * Password - admin
* Navigate to "Perfiz Performance Metric Monitor" Dashboard
* You are all set to run Performance tests on the perfiz-demo app. Below command runs a quick 45 second load test.
```shell script
./perfiz.sh <path to perfiz-demo repo>/karate-features <path to perfiz-demo repo>/perfiz.yml
```
* Now you should be able to see the performance test metrics in realtime on "Perfiz Performance Metric Monitor" Grafana Dashboard

![Grafana Screenshot](https://github.com/znsio/perfiz-demo/blob/main/assets/grafana-test.png)

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
