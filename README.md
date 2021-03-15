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
* Download the latest [release zip file](https://github.com/znsio/perfiz/releases) and ```cd``` into it
* Run below command to start Perfiz
```shell script
docker-compose up -d
```
* Launch Grafana on your browser on localhost:3000. It may ask you to change the password.
  * UserName - admin
  * Password - admin
* Navigate to "Perfiz Performance Metric Monitor" Dashboard
* You are all set to run Performance tests on the perfiz-demo app
```shell script
./perfiz.sh <path to perfiz-demo repo>/karate-features <path to perfiz-demo repo>/perfiz.yml
```
* Now you should be able to see the performance test metrics in realtime on "Perfiz Performance Metric Monitor" Grafana Dashboard

